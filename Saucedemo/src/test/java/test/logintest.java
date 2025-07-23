package test;



import org.testng.annotations.Test;
import org.testng.Assert;
import pages.Loginpage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class logintest extends BaseTest {
	@Test
	public void TC_verifyLoginFieldsAreEnabled() {
	    driver.get("https://www.saucedemo.com/");

	    WebElement usernameField = driver.findElement(By.id("user-name"));
	    WebElement passwordField = driver.findElement(By.id("password"));
	    WebElement loginButton   = driver.findElement(By.id("login-button"));

	    Assert.assertTrue(usernameField.isDisplayed());
	    Assert.assertTrue(usernameField.isEnabled());
	    Assert.assertTrue(passwordField.isDisplayed());
	    Assert.assertTrue(passwordField.isEnabled());
	    Assert.assertTrue(loginButton.isDisplayed());
	    Assert.assertTrue(loginButton.isEnabled());
	}


    @Test
    public void testValidLogin() {
        Loginpage login = new Loginpage(driver);
        login.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test
    public void testInvalidLogin() {
    	Loginpage  login = new Loginpage (driver);
        login.login("wrong_user", "wrong_pass");
        WebElement error = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assert.assertTrue(error.isDisplayed());
    }
    @Test
    public void TC_01VerifyLogin() {
        // Locate username and password fields
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));

        // Define input values
        String usernameInput = "standard_user";
        String passwordInput = "secret_sauce";

        // Clear and enter text
        usernameField.clear();
        usernameField.sendKeys(usernameInput);

        passwordField.clear();
        passwordField.sendKeys(passwordInput);

        // Verify values using getAttribute
        String actualUsername = usernameField.getAttribute("value");
        String actualPassword = passwordField.getAttribute("value");

        Assert.assertNotEquals(actualUsername, usernameInput, "Username input mismatch");
        Assert.assertEquals(actualPassword, passwordInput, "Password input mismatch");
    }
    @Test
    public void TC_02verifyLogin() {
        // Locate username and password fields
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));

        // Define input values
        String usernameInput = "standard_user";
        String passwordInput = "secret_sauce";

        // Clear and enter text
        usernameField.clear();
        usernameField.sendKeys(usernameInput);

        passwordField.clear();
        passwordField.sendKeys(passwordInput);

        // Verify values using getAttribute
        String actualUsername = usernameField.getAttribute("value");
        String actualPassword = passwordField.getAttribute("value");

        Assert.assertEquals(actualUsername, usernameInput, "Username input mismatch");
        Assert.assertNotEquals(actualPassword, passwordInput, "Password input mismatch");
    }
    @Test
    public void TC_03verifyLogin() {
        // Locate username and password fields
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));

        // Define input values
        String usernameInput = "standard_user";
        String passwordInput = "secret_sauce";

        // Clear and enter text
        usernameField.clear();
        usernameField.sendKeys(usernameInput);

        passwordField.clear();
        passwordField.sendKeys(passwordInput);

        // Verify values using getAttribute
        String actualUsername = usernameField.getAttribute("value");
        String actualPassword = passwordField.getAttribute("value");

        Assert.assertEquals(actualUsername, usernameInput, "Username input mismatch");
        Assert.assertEquals(actualPassword, passwordInput, "Password input mismatch");
    }
    @Test
    public void TC_04verifyUsernameAndPasswordInput() {
        // Locate username and password fields
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));

        // Define input values
        String usernameInput = "standard_user";
        String passwordInput = "secret_sauce";

        // Clear and enter text
        usernameField.clear();
        usernameField.sendKeys(usernameInput);

        passwordField.clear();
        passwordField.sendKeys(passwordInput);

        // Verify values using getAttribute
        String actualUsername = usernameField.getAttribute("value");
        String actualPassword = passwordField.getAttribute("value");

        Assert.assertNotEquals(actualUsername, usernameInput, "Username input mismatch");
        Assert.assertNotEquals(actualPassword, passwordInput, "Password input mismatch");
    }
    @Test
    public void TC_loginPopupWait() throws InterruptedException {
        // Step 1: Navigate to login page
        driver.get("https://www.saucedemo.com/");

        // Step 2: Trigger the alert or modal
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));

        username.sendKeys("wrong_user");
        password.sendKeys("wrong_pass");
        loginBtn.click();

        // Step 3: Wait for popup
        Thread.sleep(3000); // Wait 3 seconds before continuing
    }





}



