package test;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.*;

public class cartinteractiontest extends BaseTest {

    @Test
    public void testCartAndContinueShoppingButtons() {
        // Login and add item to cart
        Loginpage loginPage = new Loginpage(driver);
        loginPage.login("standard_user", "secret_sauce");

        Inventorypage inventoryPage = new Inventorypage(driver);
        inventoryPage.addItemToCartByIndex(0);

        // Click cart icon
        driver.findElement(By.className("shopping_cart_link")).click();
        CartPage cartPage = new CartPage(driver);

        // Assert item is added
        Assert.assertTrue(cartPage.getCartItemCount() > 0, "Cart should contain item");

        // Click "Continue Shopping"
        cartPage.clickContinueShopping();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Should return to inventory page");
    }

    @Test
    public void testRemoveItemFromCart() {
        // Login and add item to cart
        Loginpage loginPage = new Loginpage(driver);
        loginPage.login("standard_user", "secret_sauce");

        Inventorypage inventoryPage = new Inventorypage(driver);
        inventoryPage.addItemToCartByIndex(0);

        // Navigate to cart
        driver.findElement(By.className("shopping_cart_link")).click();
        CartPage cartPage = new CartPage(driver);

        // Remove item
        cartPage.removeItemByIndex(0);

        // Verify cart is empty
        Assert.assertEquals(cartPage.getCartItemCount(), 0, "Cart should be empty after removal");
    }

    @Test
    public void testCheckoutButtonNavigatesToStepOne() {
        // Login and add item
        Loginpage loginPage = new Loginpage(driver);
        loginPage.login("standard_user", "secret_sauce");

        Inventorypage inventoryPage = new Inventorypage(driver);
        inventoryPage.addItemToCartByIndex(0);

        // Go to cart and click checkout
        driver.findElement(By.className("shopping_cart_link")).click();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        // Verify navigation to Step One page
        WebElement formHeader = driver.findElement(By.className("title"));
        Assert.assertTrue(formHeader.getText().contains("Checkout: Your Information"));
    }
}


