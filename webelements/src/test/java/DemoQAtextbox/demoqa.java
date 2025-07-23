package DemoQAtextbox;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import java.time.Duration;


public class demoqa {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/automation-practice-form");
    }

    @Test
    public void fillTextBoxForm() throws InterruptedException {
        // Fill in personal details
        driver.findElement(By.id("firstName")).sendKeys("Alice");
        Thread.sleep(6000);
        driver.findElement(By.id("lastName")).sendKeys("Johnson");
        Thread.sleep(6000);
        driver.findElement(By.id("userEmail")).sendKeys("alice.j@example.com");
        Thread.sleep(6000);
        driver.findElement(By.xpath("//label[text()='Male']")).click();
        Thread.sleep(6000);

        // Select Date of Birth
        driver.findElement(By.id("dateOfBirthInput")).click();
        Thread.sleep(6000);
        driver.findElement(By.xpath("//div[@class='react-datepicker__month']//div[text()='15']")).click();
        Thread.sleep(6000);

        // Fill in contact details
        driver.findElement(By.id("userNumber")).sendKeys("9876543210");
        Thread.sleep(6000);

        // Select subjects
        driver.findElement(By.id("subjectsInput")).sendKeys("English");
        Thread.sleep(6000);
        driver.findElement(By.id("subjectsInput")).sendKeys(Keys.ENTER);
        Thread.sleep(6000);

        // Select hobbies
        driver.findElement(By.xpath("//label[text()='Sports']")).click();
        Thread.sleep(6000);
        driver.findElement(By.xpath("//label[text()='Reading']")).click();
        Thread.sleep(6000);

        // Upload picture
        driver.findElement(By.id("uploadPicture")).sendKeys("C:\\Users\\LabsKraft");
        Thread.sleep(6000);

        // Fill in address
        driver.findElement(By.id("currentAddress")).sendKeys("123 Demo St\nTownsville");
        Thread.sleep(6000);

        // Select State
        WebElement stateInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-3-input")));
        Thread.sleep(6000);
        stateInput.sendKeys("NCR");
        stateInput.sendKeys(Keys.ENTER);

        // Select City
        WebElement cityInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-4-input")));
        Thread.sleep(6000);
        cityInput.sendKeys("Delhi");
        Thread.sleep(6000);
        cityInput.sendKeys(Keys.ENTER);

        // Submit the form
        driver.findElement(By.id("submit")).click();

        // Wait for the submission to complete
        Thread.sleep(2000);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
