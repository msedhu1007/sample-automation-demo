package com.webstaurantstore.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;

public class DriverManager {

    private static final String HEADLESS = "headless";

    public static WebDriver createInstance(String browserName, String appUrl) throws MalformedURLException {
        final String browserMode = System.getProperty("mode");
        WebDriver driver = null;
        if (browserName.toLowerCase().contains("firefox")) {
            if (browserMode != null && browserMode.equals(HEADLESS)) {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
            } else {
                driver = new FirefoxDriver();
            }

        }
        if (browserName.toLowerCase().contains("chrome")) {
            if (browserMode != null && browserMode.equals(HEADLESS)) {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
            } else {
                driver = new ChromeDriver();
            }
        }
        if (browserName.toLowerCase().contains("edge")) {
            if (browserMode != null && browserMode.equals(HEADLESS)) {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                driver = new EdgeDriver(edgeOptions);
            } else {
                driver = new EdgeDriver();
            }

        }

        if (browserName.toLowerCase().contains("safari")) {
            if (browserMode != null && browserMode.equals(HEADLESS)) {
                driver = new SafariDriver();
            }

        }

        driver.navigate().to(appUrl);
        return driver;
    }
}
