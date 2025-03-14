package pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {
    private WebDriver driver;

    // Page Elements using @FindBy
    @FindBy(xpath = "//span[@class='title' and text()='Products']")
    private WebElement pageTitle;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<WebElement> productNames;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    private List<WebElement> productPrices;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Check if inventory page is loaded
    public boolean isInventoryPageLoaded() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(pageTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Add product to cart by name (dynamic element handling)
    public void addProductToCart(String productName) {
        String xpath = String.format(
            "//div[text()='%s']/ancestor::div[@class='inventory_item']//button",
            productName
        );
        driver.findElement(By.xpath(xpath)).click();
    }

    // Get list of product names
    public List<String> getProductNames() {
        return productNames.stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());
    }

    // Get list of product prices (as doubles)
    public List<Double> getProductPrices() {
        return productPrices.stream()
            .map(e -> Double.parseDouble(e.getText().replace("$", "")))
            .collect(Collectors.toList());
    }

    // Sort products by visible text (e.g., "Price (low to high)")
    public void selectSortOption(String option) {
        new Select(sortDropdown).selectByVisibleText(option);
        
        // Wait for sorting to complete
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(productNames)));
    }

    // Navigate to cart page
    public CartPage goToCart() {
        cartIcon.click();
        return new CartPage(driver);
    }
}