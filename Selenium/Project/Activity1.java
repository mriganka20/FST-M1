package LiveProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class Activity1 {
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
    public void verifyPageTitle(){
        //Capturing Page title
        String pageTitle = driver.getTitle();
        //Validating the page title against the expected value
        Assert.assertEquals(pageTitle, "Alchemy LMS – An LMS Application");
    }

    @AfterTest
    public void tearDown() {
        //Close the browser
        driver.quit();
    }
}
