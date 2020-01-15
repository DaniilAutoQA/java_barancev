package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ClientData;

public class ClientHelper extends HelperBase {

    public ClientHelper(WebDriver driver){
        super(driver);
    }

    public void submitClientCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void initClientCreation() {
        click(By.linkText("add new"));
    }
    public void fillClientForm(ClientData clientData) {
        type(By.name("firstname"),clientData.getFirstname());
        type(By.name("middlename"), clientData.getMiddlename());
        type(By.name("lastname"), clientData.getLastname());
        type(By.name("nickname"),clientData.getNickname());
        type(By.name("title"), clientData.getTitle());
        type(By.name("company"), clientData.getCompany());
        type(By.name("address"), clientData.getAddress());
        type(By.name("home"), clientData.getTelhome());
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
}
