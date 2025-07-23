package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkoutsteppage {
    WebDriver driver;

    @FindBy(className = "summary_subtotal_label")
    WebElement itemTotal;

    @FindBy(className = "summary_tax_label")
    WebElement tax;

    @FindBy(className = "summary_total_label")
    WebElement total;

    @FindBy(id = "finish")
    WebElement finishBtn;

    public Checkoutsteppage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getItemTotal() {
        return itemTotal.getText();
    }

    public String getTax() {
        return tax.getText();
    }

    public String getTotal() {
        return total.getText();
    }

    public void clickFinish() {
        finishBtn.click();
    }
}



