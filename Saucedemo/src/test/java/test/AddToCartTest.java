package test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Loginpage;
import pages.Inventorypage;
import org.openqa.selenium.By;

public class AddToCartTest extends BaseTest {
	@Test
	public void TC_addToCartButtonUI() {
	    // Step 1: Login and navigate to inventory page
	    driver.get("https://www.saucedemo.com/");
	    driver.findElement(By.id("user-name")).sendKeys("standard_user");
	    driver.findElement(By.id("password")).sendKeys("secret_sauce");
	    driver.findElement(By.id("login-button")).click();
	    // Step 2: Locate the Add to Cart button for backpack
	    WebElement addBtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
	    // Step 3: Check if button is enabled
	    Assert.assertTrue(addBtn.isEnabled(), "Add to Cart button is not enabled");
	    // Step 4: Get and verify button text
	    String buttonText = addBtn.getText();
	    Assert.assertEquals(buttonText, "Add to cart", "Button text is incorrect");
	    // Step 5: Get and check background color
	    String bgColor = addBtn.getCssValue("background-color");
	    System.out.println("Background color: " + bgColor);
	    // Example expected value (depends on SauceDemo styling)
	    String expectedColor = "rgba(19, 35, 34, 1)"; // Update with actual expected value
	    Assert.assertEquals(bgColor, expectedColor, "Background color does not match expected value");
	}
    @Test
    public void testAddSingleItemToCart() {
        // Step 1: Login
        Loginpage loginPage = new Loginpage(driver);
        loginPage.login("standard_user", "secret_sauce");
        // Step 2: Add first item to cart
        Inventorypage inventoryPage = new Inventorypage(driver);
        inventoryPage.addFirstItemToCart();
        // Step 3: Validate cart badge = 1
        String cartCount = inventoryPage.getCartCount();
        Assert.assertEquals(cartCount, "1", "Cart badge should show 1 item");
    }
    @Test
    public void testAddMultipleItemsToCart() {
        // Step 1: Login
        Loginpage loginPage = new Loginpage(driver);
        loginPage.login("standard_user", "secret_sauce");
        // Step 2: Add first two items to cart
        Inventorypage inventoryPage = new Inventorypage(driver);
        inventoryPage.addItemToCartByIndex(0);
        inventoryPage.addItemToCartByIndex(1);
        // Step 3: Validate cart badge = 2
        String cartCount = inventoryPage.getCartCount();
        Assert.assertEquals(cartCount, "2", "Cart badge should show 2 items");
    }
    @Test
    public void TC_verifyCart() {
    	Loginpage login = new Loginpage(driver);
        login.login("standard_user", "secret_sauce");
        // Step 1: Locate cart icon
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
        // Step 2: Check visibility & enablement
        Assert.assertTrue(cartIcon.isDisplayed(), "Cart icon is not visible");
        Assert.assertTrue(cartIcon.isEnabled(), "Cart icon is not enabled");
        // Step 3: Verify link target
        String href = cartIcon.getAttribute("href");
        Assert.assertTrue(href.contains("cart.html") || href.endsWith("/cart"), "Cart icon does not link to cart page");
        // Step 4: Get and print background color (if styled)
        String color = cartIcon.getCssValue("color"); // or background-color if styled
        System.out.println("Cart icon color: " + color);
        // Step 5: Check if cart badge appears after adding a product
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        WebElement badge = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(badge.getText(), "1", "Cart badge does not show correct count");
    }
}




