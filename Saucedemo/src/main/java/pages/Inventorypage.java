package pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Inventorypage {
    WebDriver driver;

    @FindBy(className = "inventory_item")
    List<WebElement> items;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;

    public Inventorypage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getItemCount() {
        return items.size();
    }

    public void addFirstItemToCart() {
        items.get(0).findElement(By.className("btn_inventory")).click();
    }

    public void addItemToCartByIndex(int index) {
        if (index < items.size()) {
            items.get(index).findElement(By.className("btn_inventory")).click();
        } else {
            throw new IllegalArgumentException("Invalid item index: " + index);
        }
    }

    public String getCartCount() {
        return cartBadge.getText();
    }
}


