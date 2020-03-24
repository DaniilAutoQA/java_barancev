package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    Properties properties;

    WebDriver driver;

    private ClientHelper clientHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        //путь к исполняемым файлам прописал в переменной среды
        //System.setProperty("webdriver.gecko.driver", "D:\\java video\\Баренцев\\geckodriver-v0.26.0-win32\\geckodriver.exe");
       // System.setProperty("webdriver.chrome.driver", "D:\\java video\\Баренцев\\chromedriver.exe");
       // System.setProperty("webdriver.ie.driver", "D:\\java video\\Баренцев\\IEDriverServer.exe");
        //отключение защищенного режима IE
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        if (browser.equals(BrowserType.FIREFOX)){
            driver = new FirefoxDriver();
        }else if(browser.equals(BrowserType.CHROME)){
            driver = new ChromeDriver();
        }else if (browser.equals(BrowserType.IE)){
            driver = new InternetExplorerDriver(caps);
        }
        //driver.get("http://localhost:8080/addressbook");
        driver.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        clientHelper = new ClientHelper(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //sessionHelper.login("admin", "secret");
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));

    }



    public void stop() {
        driver.quit();
    }



    public GroupHelper group() {
        return groupHelper;
    }

    public ClientHelper getClientHelper() {
        return clientHelper;
    }

    public NavigationHelper goTO() {
        return navigationHelper;
    }
}
