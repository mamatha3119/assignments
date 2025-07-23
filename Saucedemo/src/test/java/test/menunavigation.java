package test;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import pages.*;

public class menunavigation extends BaseTest {

    @Test
    public void testBackToHomeButtonAfterCheckout() {
        Loginpage login = new Loginpage(driver);
        login.login("standard_user", "secret_sauce");

        Inventorypage inventory = new Inventorypage(driver);
        inventory.addItemToCartByIndex(0);

        driver.findElement(By.className("shopping_cart_link")).click();
        new CartPage(driver).clickCheckout();

        Checkoutfirstpage stepOne = new Checkoutfirstpage(driver);
        stepOne.enterCustomerDetails("John", "Doe", "560001");
        stepOne.clickContinue();

        new Checkoutsteppage(driver).clickFinish();

        Confirmationpage confirmation = new Confirmationpage(driver);
        confirmation.goBackToInventory();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Should return to inventory page");
    }

    @Test
    public void testMenuItemsVisibilityAndNavigation() {
        // Login
        Loginpage login = new Loginpage(driver);
        login.login("standard_user", "secret_sauce");

        // Open menu
        driver.findElement(By.id("react-burger-menu-btn")).click();

        // Check visibility of menu items
        WebElement allItems = driver.findElement(By.id("inventory_sidebar_link"));
        WebElement about = driver.findElement(By.id("about_sidebar_link"));
        WebElement logout = driver.findElement(By.id("logout_sidebar_link"));
        WebElement reset = driver.findElement(By.id("reset_sidebar_link"));

        Assert.assertTrue(allItems.isDisplayed());
        Assert.assertTrue(about.isDisplayed());
        Assert.assertTrue(logout.isDisplayed());
        Assert.assertTrue(reset.isDisplayed());
    }

    @Test
    public void testLogoutFromMenu() {
        Loginpage login = new Loginpage(driver);
        login.login("standard_user", "secret_sauce");

        // Open menu and click Logout
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"), "Should return to login screen");
    }
}
