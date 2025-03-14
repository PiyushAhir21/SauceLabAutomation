package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.InventoryPage;
import pages.LoginPage;
import utility.BaseTest;

public class InventoryTest extends BaseTest {
    @Test
    public void testProductSorting() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.selectSortOption("Price (low to high)");
        
        List<Double> prices = inventoryPage.getProductPrices();
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        
        Assert.assertEquals(prices, sortedPrices, "Products not sorted by price!");
    }

    @Test
    public void testAddToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        
        Assert.assertTrue(driver.findElement(By.id("remove-sauce-labs-backpack")).isDisplayed());
    }
}
