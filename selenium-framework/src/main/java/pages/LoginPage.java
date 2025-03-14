package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "user-name")
	private WebElement usernameField;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(id = "login-button")
	private WebElement loginButton;

	@FindBy(css = "[data-test='error']")
	private WebElement errorMessage;

	public void login(String username, String password) {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();
	}

	public String getErrorMessage() {
		return errorMessage.getText();
	}
}