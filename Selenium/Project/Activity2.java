package LiveProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class Activity2 {
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
    public void verifyPageHeading(){
        String txtHeadingXpath = "//h1[contains(@class,'-title')]";

        //Capture the Page Heading
        String txtHeading = driver.findElement(By.xpath(txtHeadingXpath)).getText();
        //Validate the captured text against the expected value
        Assert.assertEquals(txtHeading, "Learn from Industry Experts");
    }

    @AfterTest
    public void tearDown() {
        //Close the browser
        driver.quit();
    }
}
