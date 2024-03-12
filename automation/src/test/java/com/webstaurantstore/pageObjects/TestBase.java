package com.webstaurantstore.pageObjects;

import com.webstaurantstore.utils.BasePage;
import com.webstaurantstore.utils.Driver;
import com.webstaurantstore.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.Properties;

public class TestBase {

    public static Properties prop;

    @BeforeTest
    public void launchBrowser() {
        try {
            BasePage testBase = new BasePage();
            prop = testBase.loadPropertiesFile();
            WebDriver driver = DriverManager.createInstance(prop.getProperty("browser"), prop.getProperty("appURL"));
            Driver.setWebDriver(driver);
            Driver.maximize();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterTest(alwaysRun = true)
    public void endTest() {
        Driver.getDriver().quit();
    }
}
