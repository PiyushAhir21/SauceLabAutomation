package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		String browser = Config.getBrowser(); // e.g., "chrome", "firefox"
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}