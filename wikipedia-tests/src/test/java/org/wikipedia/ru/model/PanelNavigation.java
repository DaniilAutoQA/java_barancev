package org.wikipedia.ru.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.wikipedia.ru.appmanager.ApplicationManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PanelNavigation extends ApplicationManager {
    WebDriver driver;
    WebDriverWait wait;

    public PanelNavigation(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void goToMenuItem(String linktext){
       driver.findElement(By.linkText(linktext)).click();
    }


    public List<String> menu = Arrays.asList("Ссылки сюда", "Связанные правки", "Служебные страницы","Постоянная ссылка","Сведения о странице");
    public List<String> getMenuItem(){
        List<String> menu = new ArrayList<String>();
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='p-tb']//../li/a")));
        for (WebElement element : elements){
            menu.add(element.getText());
        }
        return menu;
    }
}
