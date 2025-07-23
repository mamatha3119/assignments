package saucedemo;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;


import java.util.Map;
public class test {
    WebDriver driver;
    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", Map.of(
            "credentials_enable_service", false,
            "profile.password_manager_enabled", false
        ));
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }
    public void loginWithValidCredentials_verifyURLAndInventoryVisibility() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "URL doesn't contain 'inventory'");
        Assert.assertTrue(driver.findElement(By.className("inventory_list")).isDisplayed(), "Inventory list not visible");
    }
    @Test
    public void login_verifyURLPass_butInventoryVisibilityFails() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "URL doesn't contain 'inventory'");
        // Deliberately incorrect class name to make the second assertion fail
        Assert.assertTrue(driver.findElement(By.className("inventory_mistake")).isDisplayed(), "Inventory list not visible");
    }
    @Test
    public void loginWithWrongAssertions_failBothChecks() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Wrong expected URL
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "URL doesn't contain 'dashboard'");
        // Wrong element reference
        Assert.assertTrue(driver.findElement(By.id("non-existing-element")).isDisplayed(), "Element should be visible");
    }


    public class LoginPage {
        WebDriver driver;

        public LoginPage(WebDriver driver) {
            this.driver = driver;
        }

        By username = By.id("user-name");
        By password = By.id("password");
        By loginBtn = By.id("login-button");

        public void login(String user, String pass) {
            driver.findElement(username).sendKeys(user);
            driver.findElement(password).sendKeys(pass);
            driver.findElement(loginBtn).click();
        }
    }
    public class InventoryPage {
        WebDriver driver;

        public InventoryPage(WebDriver driver) {
            this.driver = driver;
        }

        By inventoryList = By.className("inventory_list");

        public boolean isInventoryVisible() {
            return driver.findElement(inventoryList).isDisplayed();
        }
    }
    public void addMultipleItemsToCart() {
        String[] productsToAdd = {"Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt"};

        List<WebElement> products = driver.findElements(By.className("inventory_item"));
        for (String productName : productsToAdd) {
            for (WebElement product : products) {
                String name = product.findElement(By.className("inventory_item_name")).getText();
                if (name.equals(productName)) {
                    product.findElement(By.tagName("button")).click();
                    break;
                }
            }
        }
    }

    @Test(priority = 3, dependsOnMethods = {"addMultipleItemsToCart"})
    public void verifyCartBadgeCount() {
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(cartBadge.getText(), "2", "Cart badge count mismatch");
    }   
    @Test(priority = 4, dependsOnMethods = {"verifyCartBadgeCount"})
    public void navigateToCartAndVerifyItems() {
        driver.findElement(By.className("shopping_cart_link")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"), "Cart page did not load");

        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        Assert.assertEquals(cartItems.size(), 2, "Number of items in cart mismatch");
    }

    @Test
    public void testLoginButtonTextAndColor() {
        // Locate login button
        WebElement loginButton = driver.findElement(By.id("login-button"));
        // Check if button text is "Login"
        String buttonText = loginButton.getAttribute("value");
        Assert.assertEquals(buttonText, "Login", "Login button text is not correct.");
String bgColor = loginButton.getCssValue("background-color");
        System.out.println("Login Button Background Color: " + bgColor);
        // Expected color: rgba(61, 220, 132, 1)
        Assert.assertEquals(bgColor, "rgba(61, 220, 132, 1)", "Login button background color is incorrect.");
    }
    public void checkoutFlowFillInfoAndCompleteOrder() {
        driver.findElement(By.id("checkout")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"), "Checkout Step 1 not loaded");

        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"), "Checkout Step 2 not loaded");

        driver.findElement(By.id("finish")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-complete.html"), "Checkout complete page not loaded");
    }

    @Test(priority = 7, dependsOnMethods = {"checkoutFlowFillInfoAndCompleteOrder"})
    public void verifyOrderCompletionPage() {
        WebElement completeMsg = driver.findElement(By.className("complete-header"));
        Assert.assertEquals(completeMsg.getText(), "THANK YOU FOR YOUR ORDER", "Order confirmation message mismatch");
    }

    @AfterClass
    @Test
    public void endToEndTest()throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        // Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
        // Add product using switch case
        String productToAdd = "backpack";
 switch (productToAdd.toLowerCase()) {
            case "backpack":
                driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
                Thread.sleep(2000);
                break;
            case "bike-light":
                driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
                Thread.sleep(2000);
                break;
            case "bolt-t-shirt":
                driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
                Thread.sleep(2000);
                break;
            default:
                System.out.println("Product not found: " + productToAdd);
                Assert.fail("Invalid product specified");
        }
        // Go to cart
        driver.findElement(By.className("shopping_cart_link")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
        // Checkout
        driver.findElement(By.id("checkout")).click();
        // Fill in checkout info
        driver.findElement(By.id("first-name")).sendKeys("Mamatha");
        driver.findElement(By.id("last-name")).sendKeys("Baddela");
        driver.findElement(By.id("postal-code")).sendKeys("560043");
        Thread.sleep(2000);
driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
        // Finish checkout
        driver.findElement(By.id("finish")).click();
        Thread.sleep(2000);
        // Verify order completion
        WebElement confirmation = driver.findElement(By.className("complete-header"));
        Assert.assertEquals(confirmation.getText(), "Thank you for your order!");
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    