package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver driver;

    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;

    public void init() {
        System.setProperty("webdriver.gecko.driver", "D:\\java video\\Баренцев\\geckodriver-v0.26.0-win32\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\java video\\Баренцев\\chromedriver.exe");
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        groupHelper = new GroupHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/addressbook");
        sessionHelper.login("admin", "secret");
    }



    public void stop() {
        driver.quit();
    }



    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
