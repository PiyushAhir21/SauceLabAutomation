package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listenersUtility.ListImp;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import utility.BaseTest;

@Listeners(ListImp.class)
public class CartTest extends BaseTest {
    @Test
    public void testRemoveProduct() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("Sauce Labs Bike Light");
        
        CartPage cartPage = inventoryPage.goToCart();
        int initialCount = cartPage.getCartItemCount();
        
        cartPage.removeProduct("Sauce Labs Bike Light");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
            By.xpath("//div[text()='Sauce Labs Bike Light']"))
        );
        
        Assert.assertEquals(cartPage.getCartItemCount(), initialCount - 1);
    }
}