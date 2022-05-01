package project;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class Activity3 {
    AndroidDriver<MobileElement> driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("deviceId","9d1a803c");
        caps.setCapability("platformName","android");
        caps.setCapability("automationName","UiAutomator2");
        caps.setCapability("appPackage","com.google.android.keep");
        caps.setCapability("appActivity",".activities.BrowseActivity");
        caps.setCapability("noReset",true);

        URL serverUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver<>(serverUrl,caps);
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void addGoogleBonusNotes() throws IOException {

        String title = "Testing Bonus Title";
        String notes = "Testing Bonus Notes";

        driver.findElementById("new_note_button").click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("editor_list_view")));
        driver.findElementById("editable_title").sendKeys(title);
        driver.findElementById("edit_note_text").sendKeys(notes);
        driver.findElementByAccessibilityId("Reminder").click();
        driver.findElement(MobileBy.AndroidUIAutomator("text(\"Later today\")")).click();

        driver.findElementByAccessibilityId("Navigate up").click();

        String text = driver.findElementById("index_note_title").getText();
        String note = driver.findElementById("index_note_text_description").getText();
        String reminder = driver.findElementById("reminder_chip_text").getText();

        takeScreenshot();
        Assert.assertEquals(notes,note);
        Assert.assertEquals(title,text);
        Assert.assertTrue(true,reminder);
    }

    public void takeScreenshot() throws IOException, IOException {
        File scrShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenShotName = new File("src/test/resources/screenshot"+ Calendar.getInstance().getTimeInMillis()+".jpg");
        FileUtils.copyFile(scrShot, screenShotName);
        String filePath = "../" + screenShotName;
        String path = "<img src='" + filePath + "'/>";
        Reporter.log(path);
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}