package LiveProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Activity8 {
    public WebDriver driver;
    public String baseUrl = "https://alchemy.hguy.co/lms";
    public String driverPath = "C:\\geckodriver-v0.30.0-win64\\geckodriver.exe";

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", driverPath);
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();
        //Open browser
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void contactAdmin() {
        String linkContactXpath = "//a[contains(text(),'Contact')]";
        String txtFullNameXpath = "//label[contains(text(),'Full Name')]/following-sibling::input";
        String txtEmailXpath = "//label[contains(text(),'Email')]/following-sibling::input";
        String txtSubjectXpath = "//label[contains(text(),'Subject')]/following-sibling::input";
        String txtCommentOrMessageXpath = "//label[contains(text(),'Comment or Message')]/following-sibling::textarea";
        String btnSendMessageXpath = "//button[contains(text(),'Send Message')]";
        String txtConfirmationMsg = "//div[contains(@class,'wpforms-confirmation')]";

        //Clicking on the Contact link from the Menu
        driver.findElement(By.xpath(linkContactXpath)).click();
        //Enter text into the Full Name textbox
        driver.findElement(By.xpath(txtFullNameXpath)).sendKeys("Test FullName");
        //Enter Email Id into the Email textbox
        driver.findElement(By.xpath(txtEmailXpath)).sendKeys("test@test.com");
        //Enter text into the Subject textbox
        driver.findElement(By.xpath(txtSubjectXpath)).sendKeys("Test Subject");
        //Enter text into the Comment or Message textbox
        driver.findElement(By.xpath(txtCommentOrMessageXpath)).sendKeys("Test Comment or Message");
        //Clicking on the Send Message button
        driver.findElement(By.xpath(btnSendMessageXpath)).click();
        //Capturing the Confirmation Message
        String confirmationMsg = driver.findElement(By.xpath(txtConfirmationMsg)).getText();
        //Printing the Confirmation message displayed on the page
        System.out.println("The following Confirmation message is displayed: \n\"" + confirmationMsg + "\"");
    }

    @AfterTest
    public void tearDown() {
        //Close the browser
        driver.quit();
    }
}
