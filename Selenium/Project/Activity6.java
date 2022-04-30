package LiveProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Activity6 {
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

    @Test (priority = 1)
    public void verifyMyAccountPage() {
        String linkMyAccountXpath = "//a[contains(text(),'My Account')]";
        String pageTitle;

        //Clicking on the My Account link from the Menu
        driver.findElement(By.xpath(linkMyAccountXpath)).click();
        //Capturing the Page Title
        pageTitle = driver.getTitle();
        //Validating the Page title with the expected value
        Assert.assertEquals(pageTitle, "My Account – Alchemy LMS");
    }

    @Test (priority = 2)
    public void login() {
        String linkLoginXpath = "//a[contains(text(),'Login')]";
        String txtUsernameXpath = "//input[@id='user_login']";
        String txtPasswordXpath = "//input[@id='user_pass']";
        String btnLoginXpath = "//input[@id='wp-submit']";
        String linkEditProfileXpath = "//a[@class='ld-profile-edit-link']";

        //Clicking on the Login button the My Account page
        driver.findElement(By.xpath(linkLoginXpath)).click();
        //Enter the Username in the textbox
        driver.findElement(By.xpath(txtUsernameXpath)).sendKeys("root");
        //Enter the Password in the textbox
        driver.findElement(By.xpath(txtPasswordXpath)).sendKeys("pa$$w0rd");
        //Clicking on the Login button
        driver.findElement(By.xpath(btnLoginXpath)).click();
        //Validating the presence of Edit Profile link in after login
        Assert.assertTrue(driver.findElement(By.xpath(linkEditProfileXpath)).isDisplayed());
    }

    @AfterTest
    public void tearDown() {
        //Close the browser
        driver.quit();
    }
}
