package LiveProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Activity7 {
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
    public void validateAllCourses() {
        String linkAllCoursesXpath = "//a[contains(text(),'All Courses')]";
        String tblAllCoursesXpath = "//div[contains(@class,'ld-course-list-items row')]";

        //Clicking on the All Courses link from Menu items
        driver.findElement(By.xpath(linkAllCoursesXpath)).click();
        //Capturing the number of Child elements from the All Courses Table Row Div
        int rowCount = Integer.parseInt(driver.findElement(By.xpath(tblAllCoursesXpath)).getAttribute("childElementCount"));
        //Printing the Total NUmber of Courses from the Page
        System.out.println("The Total Number of Courses in the All Courses Page are: " + rowCount);
    }

    @AfterTest
    public void tearDown() {
        //Close the browser
        driver.quit();
    }
}
