package LiveProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Activity5 {
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
    public void verifyMyAccountPage() {
        String linkMyAccountXpath = "//a[contains(text(),'My Account')]";
        String pageTitle;

        //Clicking on the My Account link from the Menu
        driver.findElement(By.xpath(linkMyAccountXpath)).click();
        //Capture the page title
        pageTitle = driver.getTitle();
        //Validate the page title against the expected value
        Assert.assertEquals(pageTitle, "My Account – Alchemy LMS");
    }

    @AfterTest
    public void tearDown() {
        //Close the browser
        driver.quit();
    }
}
