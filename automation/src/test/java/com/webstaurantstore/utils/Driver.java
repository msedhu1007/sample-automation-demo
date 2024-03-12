package com.webstaurantstore.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Driver {

    WebDriver driver;

    public Driver(WebDriver driver) {

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    public static void maximize(){
        getDriver().manage().window().maximize();
    }

    protected void waitForElement(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement target = wait.until(ExpectedConditions.visibilityOf(element));
    }

}
