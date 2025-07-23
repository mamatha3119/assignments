package pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.CartPage;


public class CartPage {
    WebDriver driver;

    @FindBy(className = "cart_item")
    List<WebElement> cartItems;

    @FindBy(id = "continue-shopping")
    WebElement continueShoppingBtn;

    @FindBy(id = "checkout")
    WebElement checkoutBtn;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public void clickContinueShopping() {
        continueShoppingBtn.click();
    }

    public void clickCheckout() {
        checkoutBtn.click();
    }

    public void removeFirstItem() {
        WebElement removeBtn = cartItems.get(0).findElement(By.className("cart_button"));
        removeBtn.click();
    }

    public void removeSecondItem() {
        WebElement removeBtn = cartItems.get(1).findElement(By.className("cart_button"));
        removeBtn.click();
    }

    public void removeThirdItem() {
        WebElement removeBtn = cartItems.get(2).findElement(By.className("cart_button"));
        removeBtn.click();
    }

    public String getFirstItemName() {
        return cartItems.get(0).findElement(By.className("inventory_item_name")).getText();
    }

    public String getSecondItemName() {
        return cartItems.get(1).findElement(By.className("inventory_item_name")).getText();
    }

    public String getThirdItemName() {
        return cartItems.get(2).findElement(By.className("inventory_item_name")).getText();
    }

    public void removeItemByIndex(int index) {
        WebElement removeBtn = cartItems.get(index).findElement(By.className("cart_button"));
        removeBtn.click();
    }

}

