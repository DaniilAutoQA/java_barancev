package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;

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
        type(By.name("work"),clientData.getTelwork());
        type(By.name("mobile"),clientData.getMobile());
        type(By.name("email"),clientData.getEmail());
        type(By.name("email2"),clientData.getEmail2());
        type(By.name("email3"),clientData.getEmail3());
        attach(By.name("photo"),clientData.getPhoto());

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
        List<ClientData> client = new ArrayList<ClientData>();
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
                client.add(new ClientData().withId(id).withFirstname(firstName).withLastname(lastName).withMiddlename("Godzila").withGroupname("[none]"));

               }

        return client;
    }
    public Clients all() {
        Clients client = new Clients();
        List<WebElement> clientsList = driver.findElements(By.xpath("//table/tbody/tr[@name]"));
        //List<WebElement> clientsList = driver.findElements(By.name("entry"));
        for (WebElement element : clientsList) {
            List <WebElement> contacts = element.findElements(By.tagName("td"));
            String lastName = contacts.get(1).getText();
            String firstName = contacts.get(2).getText();
            String allEmails = contacts.get(4).getText();
            String allPhones = contacts.get(5).getText();
            //String [] phones = contacts.get(5).getText().split("\n");  //делим строку на части
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            client.add(new ClientData().withId(id).withFirstname(firstName).withLastname(lastName).withMiddlename("Godzila").withGroupname("[none]").withAllEmails(allEmails).withAllPhones(allPhones));
        }
        return client;
    }

    public ClientData infoFromEditForm(ClientData client){
        initClientModificationByid(client.getId());
        String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");
        String email = driver.findElement(By.name("email")).getAttribute("value");
        String email2 = driver.findElement(By.name("email2")).getAttribute("value");
        String email3 = driver.findElement(By.name("email3")).getAttribute("value");
        return new ClientData().withId(client.getId()).withFirstname(firstname).withLastname(lastname).withTelHome(home).withMobile(mobile).withTelWork(work).withEmail(email).withEmail2(email2).withEmail3(email3);

    }

    private void initClientModificationByid(int id) {

        WebElement checkbox = driver.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

        //or
        //driver.findElement(By.xpath(String.format("//input[@value='$s']/../../td[8]/a", id))).click();
        //driver.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
       //driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }


    private void initClientDetailsByid(int id) {
        driver.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
    }
}
