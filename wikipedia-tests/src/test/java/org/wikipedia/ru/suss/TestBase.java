package org.wikipedia.ru.suss;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase {


    private WebDriver driver;
    private WebDriverWait wait;
    private Actions action;
    private static final Logger logger = LogManager.getLogger(TestBase.class);
    public static Date date = new Date();
    static DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        //ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        //   driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        baseUrl = "http://185.102.122.168:7003/suss2";
//            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testLogs(){
        driver = new ChromeDriver();
        driver.get("https://ya.ru");
        logger.error("пиздато");
        logger.info("info");
    }

    @Test
    public void testInspectionMaterials(){
        driver = new ChromeDriver();
    }

    @Test
    public void testCriminalCase(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        Capabilities capabilities = ((HasCapabilities) driver).getCapabilities();
        wait = new WebDriverWait (driver, 10);
        driver.get("http://185.102.122.168:7003/suss2/main/criminal-cases/list");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        action = new Actions(driver);

        //driver.findElement(By.xpath("//div[@id='app']/div/div/main/div/div[2]/div/div/div[2]/a/span")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Новое дело')]")).click();
        //vue-treeselect vue-treeselect--multi vue-treeselect--searchable vue-treeselect--open-below
        //WebElement treeSelect = driver.findElement(By.xpath("//label[contains(text(), 'Финансовая организация*')]"));
        // By.xpath("//div[@class='vue-treeselect_menu']")
        // ------------------
        // WebElement menu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class = 'vue-treeselect vue-treeselect--multi vue-treeselect--searchable vue-treeselect--open-below']")));
        // action.moveToElement(menu).click().build().perform();
        //----------------------
        // выбор ФО
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class = 'vue-treeselect vue-treeselect--multi vue-treeselect--searchable vue-treeselect--open-below']"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class = 'vue-treeselect__label-container']"))).click();
        //----------------------


        //    driver.findElement(By.xpath("//div[@class = 'vue-treeselect vue-treeselect--multi vue-treeselect--searchable vue-treeselect--open-below']")).click();
        // WebElement treeSelect = driver.findElement(By.xpath("//div[@class='vue-treeselect_menu']"));

        // Select select = new Select(treeSelect);
        //   select.selectByVisibleText("Тестовое значение");

        // формирование списка Категорий дел
        List <WebElement> listWebElementCaseCategory = new ArrayList<WebElement>();
        WebElement categoryDeals = driver.findElement(By.xpath("//label[contains(text(),'Категория дела*')]"));
        action.moveToElement(categoryDeals).click().build().perform();
        List <WebElement> lists = driver.findElements(By.xpath("//div[@class = 'vue-treeselect__list-item vue-treeselect__indent-level-0']"));
        for(WebElement list: lists ){
            listWebElementCaseCategory.add(list);

        }
        logger.info(listWebElementCaseCategory.get(2).getText());
        //----------------------
        //выбор категории
        action.moveToElement(listWebElementCaseCategory.get(3)).click().build().perform();
        //----------------------

        //формировнаие и выбор категории круга лиц
        WebElement categoryFace = driver.findElement(By.xpath("//label[contains(text(),'Категория круга лиц*')]"));
        action.moveToElement(categoryFace).click().build().perform();
        List <WebElement> listWebElementParticipantCategory = new ArrayList<WebElement>();
        List <WebElement> listCategoryFace = driver.findElements(By.xpath("//div[@class = 'vue-treeselect__list-item vue-treeselect__indent-level-0']"));
        for (WebElement element: listCategoryFace){
            listWebElementParticipantCategory.add(element);
        }
        action.moveToElement(listWebElementParticipantCategory.get(1)).click().build().perform();
        //---------------------------------------------------------------------------------------------
        //ВВод номера дела
        WebElement numberOfDeal = driver.findElement(By.xpath("//label[contains(text(),'Номер УД')]"));
        action.moveToElement(numberOfDeal).click().sendKeys("123").build().perform();
        //---------------------------------------------------------------------------------------------
        //Правоохранительный орган* - формировнаие и выбор law-enforcement-agency
        WebElement fildLawEnforcementAgency = driver.findElement(By.xpath("//label[contains(text(),'Правоохранительный орган*')]"));
        action.moveToElement(fildLawEnforcementAgency).click().build().perform();
        // Решил отдельно не создавать List<WebElement> listFildLawEnforcementAgency = new ArrayList<WebElement>();
        List<WebElement> elementsfildLawEnforcementAgency = driver.findElements(By.xpath("//div[@class = 'vue-treeselect__list-item vue-treeselect__indent-level-0']"));
        action.moveToElement(elementsfildLawEnforcementAgency.get(1)).click().build().perform();
        //---------------------------------------------------------------------------------------------
        //Испонитель* (performer) - формировнаие и выбор
        WebElement performer = driver.findElement(By.xpath("//label[contains(text(),'Исполнитель*')]"));
        action.moveToElement(performer).click().build().perform();
        List<WebElement> listPerformer = driver.findElements(By.xpath("//div[@class = 'vue-treeselect__list-item vue-treeselect__indent-level-0']"));
        action.moveToElement(listPerformer.get(1)).click().build().perform();
        // ---------------------------------------------------------------------------------------------

        //Дата возбуждения дела* - выбор
        WebElement createDate = driver.findElement(By.xpath("//label[contains(text(),'Дата возбуждения дела*')]"));
        action.click(createDate).build().perform();
        WebElement chooseDate = driver.findElement(By.xpath("//button[@class = 'v-btn v-date-picker-table__current v-btn--rounded v-btn--outlined theme--light primary--text']"));
        action.moveToElement(chooseDate).click().sendKeys(df.format(date.getTime())).build().perform();
        // ---------------------------------------------------------------------------------------------

        //Следователь* (detective) - формировнаие и выбор
        WebElement detective = driver.findElement(By.xpath("//label[contains(text(),'Следователь*')]"));
        action.moveToElement(detective).click().build().perform();
        List<WebElement> listDetective = driver.findElements(By.xpath("//div[@class = 'vue-treeselect__list-item vue-treeselect__indent-level-0']"));
        action.moveToElement(listDetective.get(0)).click().build().perform();
        // ---------------------------------------------------------------------------------------------

        //Фабула* - формировнаие и выбор

        WebElement fabula = driver.findElement(By.xpath("//label[contains(text(),'Фабула*')]"));
        action.moveToElement(fabula).click().sendKeys("0202").build().perform();
        // ---------------------------------------------------------------------------------------------

        // Статьи- формировнаие и выбор article
        List<WebElement> listArticle = new ArrayList<WebElement>();
        List<WebElement> listChapterArticle = new ArrayList<WebElement>();
        WebElement setArticle = driver.findElement(By.xpath("//div[contains(text(), 'Статьи')]/..//span[contains(text(), 'Добавить')]"));
        setArticle.click();
        WebElement chooseArticle = driver.findElement(By.xpath("//label[.='Статья*']"));
        action.moveToElement(chooseArticle).click().build().perform();
        List<WebElement> articles = driver.findElements(By.xpath("//div[@class = 'vue-treeselect__list-item vue-treeselect__indent-level-0']"));
        List<WebElement> chapters = driver.findElements(By.xpath("//div[@class = 'vue-treeselect__list-item vue-treeselect__indent-level-1']"));
        for (WebElement article: articles ){
            listArticle.add(article);
        }
        for (WebElement chapter: chapters){
            listChapterArticle.add(chapter);
        }
        action.moveToElement(listChapterArticle.get(0)).click().build().perform();
        WebElement button = driver.findElement(By.xpath("//button[@class='v-btn v-btn--depressed theme--light v-size--small primary']/span[contains(text(), 'Сохранить')]"));
        driver.findElement(By.xpath("//div[@class='v-input__slot']/label[.='Покушение']")).click();  // чек Покушение.
        action.click(button).build().perform();





        // ---------------------------------------------------------------------------------------------

        // Куратор- формировнаие и выбор  handler      ---доступен только если выбран оределенный исполнитель
        //   WebElement handler = driver.findElement(By.xpath("//label[contains(text(), 'Куратор')]"));
        // ---------------------------------------------------------------------------------------------

        // Размер ущерба (руб.) - формировнаие и выбор costOfDamage
        WebElement costOfDamage = driver.findElement(By.xpath("//label[contains(text(), 'Размер ущерба')]"));
        action.moveToElement(costOfDamage).click().sendKeys("1230000").build().perform();
        // ---------------------------------------------------------------------------------------------

        // Дата контроля- формировнаие и выбор controlDate
        //  WebElement controlDate = driver.findElement(By.xpath("//label[contains(text(), 'Дата контроля')]"));
        //  action.moveToElement(controlDate).click().sendKeys("11.11.0202").build().perform();
        // ---------------------------------------------------------------------------------------------

        // основание для возбуждения дела- формировнаие и выбор cause of action
        WebElement causeOfAction = driver.findElement(By.xpath("//label[contains(text(), 'Основание для возбуждения дела')]"));
        action.moveToElement(causeOfAction).click().build().perform();
        List<WebElement> listCauseOfAction = driver.findElements(By.xpath("//div[@class = 'vue-treeselect__list-item vue-treeselect__indent-level-0']"));
        logger.info(listCauseOfAction.size());
        action.moveToElement(listCauseOfAction.get(1)).click().build().perform();
        // ---------------------------------------------------------------------------------------------

        // Примечание- формировнаие и выбор  note
        WebElement note = driver.findElement(By.xpath("//label[contains(text(), 'Примечание')]"));
        action.moveToElement(note).click().sendKeys("ввели текст в поле примечание").build().perform();
        // ---------------------------------------------------------------------------------------------

        // Особый контроль- формировнаие и выбор Чекбокс specialControl
        driver.findElement(By.xpath("//div[@class= 'v-input__slot']/label[.='Особый контроль']")).click();
        // ---------------------------------------------------------------------------------------------


////  Блок Актиыв...................................................

        //Кнопка добавить
        action.moveToElement(driver.findElement(By.xpath("//div[@class='row no-gutters']//div[.='Активы']"))).click().build().perform();
        action.moveToElement(driver.findElement(By.xpath("//*[@class ='v-data-table data-table-draggable full-width v-data-table--dense v-data-table--fixed-height theme--light']/..//span[.='Добавить']"))).click().build().perform();
        // поиск актива
        WebElement searchField = driver.findElement(By.xpath("//label[contains(text(), 'Наименование актива')]"));
        action.moveToElement(searchField).click().sendKeys("денеж").build().perform();
        List<WebElement> listOfAsset = driver.findElements(By.xpath("//div[@class='vue-treeselect__list-item vue-treeselect__indent-level-0']"));
        action.moveToElement(listOfAsset.get(1)).click().build().perform();
        //submit asset
        driver.findElement(By.xpath("//div[@class='v-card__actions']//span[.=' Сохранить ']")).click();












        driver.findElement(By.xpath("//div[@id='app']/div/div/main/div/div[2]/div/div/div/div/div/div/div/div/form/div/div/div/div/div/div/div")).click();

    }

    @Test
    public void testUntitledTestCase() throws Exception {
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("НСИ"));
        element.click();
        driver.findElement(By.xpath("//div[@id='app']/div/div/main/div/div[2]/div/nav/div/div/div/a[12]/div")).click();
        driver.findElement(By.xpath("//div[@id='app']/div/div/main/div/div[2]/div/div/div[2]/div/button/span")).click();
        logger.info(element.getTagName());
        driver.findElement(By.cssSelector("#input-292")).sendKeys("новый2" );
        driver.findElement(By.xpath("//span[contains(text(), 'Сохранить')]")).click();
        //driver.findElement(By.cssSelector(".v-btn__content")).click();
        // driver.findElement(By.xpath("//div[@id='app']/div/div/main/div/div[2]/div/div/div[3]/div/div/div/div/table/tbody/tr/td")).click();
    }


    @Test
    public void test2() throws Exception {
        driver = new FirefoxDriver();
        driver.get("https://ng-bootstrap.github.io/#/components/alert/examples");
        //  driver.wait(100);
        driver.findElement(By.xpath("//button[contains (text(), 'Change message')]")).click();




    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }





}




