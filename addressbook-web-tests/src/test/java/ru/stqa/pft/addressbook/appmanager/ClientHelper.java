package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ClientData;

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

    public void selectClient() {
        click(By.name("selected[]"));
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
        navigationHelper.goToHomePage();

    }

    public boolean isThereAClient() {
        return isElementPresent(By.name("selected[]"));
    }
}
