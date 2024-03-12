package com.webstaurantstore.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class BasePage {

    public File f1;
    public FileInputStream file;


    public void waitForElement(long time, By element) {
        WebDriverWait newWait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time));
        newWait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }


    public WebElement waitForElementWithPollingInterval(long time, By element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time));
        wait.pollingEvery(Duration.ofSeconds(5));
        wait.ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public List<WebElement> getElements(By elements) {
        List<WebElement> allElements = Driver.getDriver().findElements(elements);
        return allElements;
    }

    public WebElement getElement(By element) {
        return Driver.getDriver().findElement(element);
    }

    public boolean getPage(By element) {
        try {
            return Driver.getDriver().findElement(element) != null;
        } catch (Exception e) {
            //ignore
        }
        return false;
    }


    public String getText(By element) {
        return Driver.getDriver().findElement(element).getText();
    }

    public Properties loadPropertiesFile() throws IOException {
        Properties prop = new Properties();
        f1 = new File(System.getProperty("user.dir") + "/src/test/resources/application.properties");
        file = new FileInputStream(f1);
        prop.load(file);
        return prop;
    }

    public void setText(By object, String value) {
        Driver.getDriver().findElement(object).sendKeys(value);
    }

    public void click(By object) {
        Driver.getDriver().findElement(object).click();
    }

    public boolean isEnabled(By object) {
        return Driver.getDriver().findElement(object).isEnabled();
    }

    public static void waitForPageRefresh(String currentPageSource, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(Driver.getDriver().findElement(By.tagName("html")), currentPageSource)));
    }

}
