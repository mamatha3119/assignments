package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Confirmationpage {
    WebDriver driver;

    @FindBy(className = "complete-header")
    WebElement confirmationMessage;

    @FindBy(id = "back-to-products")
    WebElement backToProductsBtn;

    public Confirmationpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

    public void goBackToInventory() {
        backToProductsBtn.click();
    }
}



