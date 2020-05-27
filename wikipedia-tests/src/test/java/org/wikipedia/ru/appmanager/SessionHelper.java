package org.wikipedia.ru.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SessionHelper extends ApplicationManager {
    WebDriver driver;
    WebDriverWait wait;
    public SessionHelper(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getValidationText(){
        String text = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='userloginForm']/form/div"))).getText();;
        return text;
    }
    public String getLinkText(String text){
        text = driver.findElement(By.linkText(text)).getText();;
        return text;
    }



    public void login(String username, String password) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Войти"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Войти"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("wpName1"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("wpName1"))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("wpName1"))).sendKeys(username);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("wpPassword1"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("wpPassword1"))).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("wpPassword1"))).sendKeys(password);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("wpLoginAttempt"))).click();

    }
}
