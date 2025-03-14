package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.InventoryPage;
import pages.LoginPage;
import utility.BaseTest;

public class ProductTest extends BaseTest {
    @Test
    public void testPriceSorting() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.selectSortOption("Price (low to high)");
        
        List<Double> prices = inventoryPage.getProductPrices();
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        
        Assert.assertEquals(prices, sortedPrices, "Products not sorted by price");
    }
}