package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ClientData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientHelper extends HelperBase {

    public ClientHelper(WebDriver driver){
        super(driver);
    }
    private NavigationHelper navigationHelper = new NavigationHelper(driver);

    public void submitClientCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void initClientCreation() {
        click(By.linkText("add new"));
    }
    public void fillClientForm(ClientData clientData, boolean creation) {
        type(By.name("firstname"),clientData.getFirstname());
        type(By.name("middlename"), clientData.getMiddlename());
        type(By.name("lastname"), clientData.getLastname());
        type(By.name("nickname"),clientData.getNickname());
        type(By.name("title"), clientData.getTitle());
        type(By.name("company"), clientData.getCompany());
        type(By.name("address"), clientData.getAddress());
        type(By.name("home"), clientData.getTelhome());

        if (creation){
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(clientData.getGroupname());
        } else{
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectClient(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();

    }
    public void selectClientByid(int id) {
        driver.findElement(By.cssSelector("input[id= '" + id + "']")).click();
    }

    public void modificationClient() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitClientModification() {
        click(By.name("update"));
    }

    public void deleteClient() {
        click(By.xpath("//input[@value='Delete']"));
    }
    public void createClient(ClientData client, boolean b) {
        initClientCreation();
        fillClientForm(client,b);
        submitClientCreation();
        navigationHelper.homePage();

    }
    public void modifyClient(ClientData client) {
        selectClientByid(client.getId());
        modificationClient();
        fillClientForm((client), false);
        submitClientModification();
        navigationHelper.homePage();
    }

    public boolean isThereAClient() {
        return isElementPresent(By.name("selected[]"));
    }


    public List<ClientData> getClientList() {
        List<ClientData> group = new ArrayList<ClientData>();
        List<WebElement> clientsList = driver.findElements(By.xpath("//table/tbody/tr[@name]"));
        //List<WebElement> elements = driver.findElements(By.name("selected[]"));

      //  for (WebElement elementF: elementsFirstName ) {

          //  String firstName = elementF.getText();
           //String lastName2 = elementF.findElement(By.cssSelector("td\[2\]")).getText();
           // System.out.println(firstName);
            for (WebElement element : clientsList) {
                List <WebElement> contacts = element.findElements(By.tagName("td"));
                String lastName = contacts.get(1).getText();
                String firstName = contacts.get(2).getText();
                int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
                group.add(new ClientData().withId(id).withFirstname(firstName).withLastname(lastName).withMiddlename("Godzila").withGroupname("[none]"));

               }

        return group;
    }
    public Set<ClientData> all() {
        Set<ClientData> group = new HashSet<ClientData>();
        List<WebElement> clientsList = driver.findElements(By.xpath("//table/tbody/tr[@name]"));
        for (WebElement element : clientsList) {
            List <WebElement> contacts = element.findElements(By.tagName("td"));
            String lastName = contacts.get(1).getText();
            String firstName = contacts.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            group.add(new ClientData().withId(id).withFirstname(firstName).withLastname(lastName).withMiddlename("Godzila").withGroupname("[none]"));
        }
        return group;
    }
}
