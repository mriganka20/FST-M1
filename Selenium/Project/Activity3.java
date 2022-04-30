package LiveProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Activity3 {
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
    public void verifyTitleFirstInfo(){
        String txtTitleXpath = "//h3[contains(@class,'-title')]";

        //Capture the Title of the First Info
        String txtTitle = driver.findElement(By.xpath(txtTitleXpath)).getText();
        //Validate the captured text against the expected value
        Assert.assertEquals(txtTitle, "Actionable Training");
    }

    @AfterTest
    public void tearDown() {
        //Close the browser
        driver.quit();
    }
}
