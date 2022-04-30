package LiveProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Activity9 {
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
    }

    @Test (priority = 1)
    public void myAccount() {
        String linkMyAccountXpath = "//a[contains(text(),'My Account')]";
        String pageTitle;

        //Clicking on the My Account link from the Menu
        driver.findElement(By.xpath(linkMyAccountXpath)).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test (priority = 2)
    public void login() {
        String linkLoginXpath = "//a[contains(text(),'Login')]";
        String txtUsernameXpath = "//input[@id='user_login']";
        String txtPasswordXpath = "//input[@id='user_pass']";
        String btnLoginXpath = "//input[@id='wp-submit']";

        //Clicking on the Login button the My Account page
        driver.findElement(By.xpath(linkLoginXpath)).click();
        //Enter the Username in the textbox
        driver.findElement(By.xpath(txtUsernameXpath)).sendKeys("root");
        //Enter the Password in the textbox
        driver.findElement(By.xpath(txtPasswordXpath)).sendKeys("pa$$w0rd");
        //Clicking on the Login button
        driver.findElement(By.xpath(btnLoginXpath)).click();
    }

    @Test (priority = 3)
    public void completeSampleLesson(){
        String linkAllCoursesXpath = "//a[contains(text(),'All Courses')]";
        String linkSeeMoreSocialMediaXpath = "//h3[contains(text(),'Social Media Marketing')]/ancestor::div[1]/descendant::a";
        String linkDevelopingStrategyXpath = "//div[contains(text(),'Developing Strategy')]";
        String btnMarkCompleteXpath = "//input[contains(text(), 'Mark Complete')]";
        String developingStrategyPageTitle;

        //Clicking on the All Courses link from the Menu
        driver.findElement(By.xpath(linkAllCoursesXpath)).click();
        //Clicking the Social Media Marketing Course
        driver.findElement(By.xpath(linkSeeMoreSocialMediaXpath)).click();
        //Clicking the Course Content Developing Strategy link
        driver.findElement(By.xpath(linkDevelopingStrategyXpath)).click();
        //Capturing the Page Title
        developingStrategyPageTitle = driver.getTitle();
        //Validating the Page title against the expected value
        Assert.assertEquals(developingStrategyPageTitle, "Developing Strategy – Alchemy LMS");
        //Clicking on the Mark Complete button if it is displayed in the page
        if(driver.findElement(By.xpath(btnMarkCompleteXpath)).isDisplayed()){
            driver.findElement(By.xpath(btnMarkCompleteXpath)).click();
        }
    }

    @AfterTest
    public void tearDown() {
        //Close the browser
        driver.quit();
    }
}
