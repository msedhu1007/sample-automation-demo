package com.webstaurantstore.pageObjects;

import com.webstaurantstore.utils.BasePage;
import com.webstaurantstore.utils.Driver;
import org.openqa.selenium.By;

public class HomePage extends BasePage {


    public By txtSearchField = By.id("searchval");
    public By iconSearch = By.xpath("(//button[@value='Search'])[1]");
    public By categorySections = By.cssSelector("div.ag-category-grid p.font-semibold");
    public By categoryBox = By.id("categoriesBox");
    public By productListing = By.id("product_listing");
    public By searchDescription = By.cssSelector("div.product-box-container span[data-testid='itemDescription']");
    public By pagination = By.id("paging");
    public By pageNumbers = By.xpath("//div[@id='paging']//a");
    public String pageNumbersString = "//div[@id='paging']//a[@aria-label='page %d']";

    public void searchItem(String item) {
        setText(txtSearchField, item.toLowerCase());
        click(iconSearch);
    }

    public boolean isAllCategoriesValid(String item) {
        waitForElement(20, categoryBox);
        return getElements(categorySections).stream().anyMatch(ele -> ele.getText().toLowerCase().contains(item.toLowerCase()));
    }


    public boolean isAllSearchValid(String item) {
        waitForElement(30, productListing);
        return getElements(searchDescription).stream().anyMatch(e -> e.getText().toLowerCase().contains(item.toLowerCase()));
    }

    public boolean navigateToNextPages(String item) {
        int count = 1;
        do {
            count++;
            if (isAllSearchValid(item)) {
                if (getPage(By.xpath(String.format(pageNumbersString, count))) &&
                        getElement(By.xpath(String.format(pageNumbersString, count))).isEnabled()) {
                    getElement(By.xpath(String.format(pageNumbersString, count))).click();
                    waitForPageRefresh(Driver.getDriver().getPageSource(), 20);
                }
            } else {
                return false;
            }

        } while (getPage(By.xpath(String.format(pageNumbersString, count + 1))) &&
                getElement(By.xpath(String.format(pageNumbersString, count + 1))).isEnabled());
        return true;

    }
}
