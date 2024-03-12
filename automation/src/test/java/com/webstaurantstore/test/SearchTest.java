package com.webstaurantstore.test;

import com.webstaurantstore.pageObjects.HomePage;
import com.webstaurantstore.pageObjects.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SearchTest extends TestBase {

    String searchItem = "stainless steel";
    private static final Logger logger = LogManager.getLogger(SearchTest.class);

    @Test
    public void searchTest() {
        logger.info("Starting Search tests...");
        HomePage homePage = new HomePage();
        homePage.searchItem(searchItem);
        Assert.assertTrue(homePage.isAllCategoriesValid(searchItem), "Verify all the categories have the search word");
        Assert.assertTrue(homePage.navigateToNextPages(searchItem), "Verify all the search results have the search item");
        logger.info("Ending Tests");
    }
}
