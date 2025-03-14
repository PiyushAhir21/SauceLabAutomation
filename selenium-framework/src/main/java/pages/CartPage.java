package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	private WebDriver driver;

	// Static elements with @FindBy
	@FindBy(css = ".cart_item")
	private List<WebElement> cartItems;

	@FindBy(id = "checkout")
	private WebElement checkoutButton;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Remove a product by name (dynamic element handling)
	public void removeProduct(String productName) {
		String removeButtonXPath = String.format("//div[text()='%s']/ancestor::div[@class='cart_item']//button",
				productName);
		WebElement removeButton = driver.findElement(By.xpath(removeButtonXPath));
		removeButton.click();

		// Wait for the item to be removed from the DOM
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(removeButton));
	}

	// Get the number of items in the cart
	public int getCartItemCount() {
		return cartItems.size();
	}

	// Navigate to checkout page
	public CheckoutPage proceedToCheckout() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
}