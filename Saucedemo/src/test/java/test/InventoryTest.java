package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.Loginpage;
import java.util.List;

public class InventoryTest extends BaseTest  {

    @Test(priority = 1)
    public void testInventoryPageLoads() {
        Loginpage login = new Loginpage(driver);
        login.login("standard_user", "secret_sauce");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory"), "Inventory page did not load");
    }

    @Test(priority = 2)
    public void testAllProductsAreVisible() {
        Loginpage login = new Loginpage(driver);
        login.login("standard_user", "secret_sauce");

        List<WebElement> items = driver.findElements(By.className("inventory_item"));
        Assert.assertEquals(items.size(), 6, "Expected 6 products to be displayed");
        for (WebElement item : items) {
            Assert.assertTrue(item.isDisplayed(), "Product not visible: " + item.getText());
        }
    }

    @Test(priority = 3)
    public void testProductDetailsAreVisible() {
        Loginpage login = new Loginpage(driver);
        login.login("standard_user", "secret_sauce");

        List<WebElement> names = driver.findElements(By.className("inventory_item_name"));
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));

        Assert.assertFalse(names.isEmpty(), "Product names not found");
        Assert.assertFalse(prices.isEmpty(), "Product prices not found");

        for (int i = 0; i < names.size(); i++) {
            Assert.assertTrue(names.get(i).isDisplayed(), "Product name not visible");
            Assert.assertTrue(prices.get(i).isDisplayed(), "Product price not visible");
        }
    }

    @Test(priority = 4)
    public void testAddToCartButtonFunctionality() {
        Loginpage login = new Loginpage(driver);
        login.login("standard_user", "secret_sauce");

        WebElement addBtn = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        Assert.assertTrue(addBtn.isDisplayed());
        Assert.assertEquals(addBtn.getText(), "Add to cart");

        addBtn.click();
        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-backpack"));
        Assert.assertEquals(removeBtn.getText(), "Remove");
    }

    @Test(priority = 5)
    public void testProductSortDropdown()throws InterruptedException {
        Loginpage login = new Loginpage(driver);
        login.login("standard_user", "secret_sauce");

        WebElement dropdown = driver.findElement(By.className("product_sort_container"));
        Assert.assertTrue(dropdown.isDisplayed(), "Sort dropdown not visible");

        dropdown.click();
        List<WebElement> options = driver.findElements(By.tagName("option"));
        Assert.assertEquals(options.size(), 4, "Dropdown should contain 4 options");

        for (WebElement option : options) {
            Assert.assertTrue(option.isDisplayed(), "Dropdown option not visible: " + option.getText());
        }
        Thread.sleep(3000);
    }
    @Test
    public void TC_productCard()throws InterruptedException {
    	 Loginpage login = new Loginpage(driver);
         login.login("standard_user", "secret_sauce");

        // Verify inventory page loaded
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "Inventory page did not load");

        // Verify product cards are visible
        List<WebElement> products = driver.findElements(By.className("inventory_item"));
        Assert.assertEquals(products.size(), 6, "Expected 6 product cards");

        for (WebElement product : products) {
            WebElement name = product.findElement(By.className("inventory_item_name"));
            WebElement price = product.findElement(By.className("inventory_item_price"));
            WebElement addBtn = product.findElement(By.tagName("button"));

            Assert.assertTrue(name.isDisplayed(), "Product name not visible");
            Assert.assertTrue(price.isDisplayed(), "Product price not visible");
            Assert.assertTrue(addBtn.isDisplayed(), "Add to Cart button not visible");
            Assert.assertEquals(addBtn.getText(), "Add to cart", 
                "Button should say 'Add to cart' before clicking");
        }
        Thread.sleep(3000);
        }

}




