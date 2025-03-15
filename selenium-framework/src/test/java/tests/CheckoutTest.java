package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listenersUtility.ListImp;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;
import utility.BaseTest;

@Listeners(ListImp.class)
public class CheckoutTest extends BaseTest {

	@Test
	public void testCheckoutFlow() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("standard_user", "secret_sauce");

		InventoryPage inventoryPage = new InventoryPage(driver);
		inventoryPage.addProductToCart("Sauce Labs Backpack");

		CartPage cartPage = inventoryPage.goToCart();
		CheckoutPage checkoutPage = cartPage.proceedToCheckout();
		checkoutPage.enterDetails("John", "Doe", "12345");
		checkoutPage.finishCheckout();

		Assert.assertTrue(checkoutPage.getConfirmationMessage().contains("Thank you"));
	}
}