package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkoutfirstpage {
    WebDriver driver;

    @FindBy(id = "first-name")
    WebElement firstName;

    @FindBy(id = "last-name")
    WebElement lastName;

    @FindBy(id = "postal-code")
    WebElement postalCode;

    @FindBy(id = "continue")
    WebElement continueBtn;

    public Checkoutfirstpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterCustomerDetails(String fName, String lName, String zip) {
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        postalCode.sendKeys(zip);
    }

    public void clickContinue() {
        continueBtn.click();
    }
}





