package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.InventoryPage;
import pages.LoginPage;
import utility.BaseTest;

public class LoginTest extends BaseTest {
	@Test(dataProvider = "loginData")
	public void testLogin(String username, String password, boolean isValid) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		if (isValid) {
			Assert.assertTrue(new InventoryPage(driver).isInventoryPageLoaded(), "Valid login failed");
		} else {
			Assert.assertTrue(loginPage.getErrorMessage().contains("Epic sadface"),
					"Invalid login error message missing");
		}
	}

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() {
		return new Object[][] {
				// Valid Users (Username, Password, Expected Login Success)
				{ "standard_user", "secret_sauce", true }, { "problem_user", "secret_sauce", true },
				{ "performance_glitch_user", "secret_sauce", true },

				// Invalid Users
				{ "locked_out_user", "secret_sauce", false }, // Valid credentials but locked account
				{ "invalid_user", "invalid_password", false }, // Invalid username/password
				{ "", "secret_sauce", false }, // Empty username
				{ "standard_user", "", false }, // Empty password
				{ "", "", false } // Empty fields
		};
	}
}