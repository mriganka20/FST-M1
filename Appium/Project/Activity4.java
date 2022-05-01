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
import java.util.List;

public class Activity4 {
    AndroidDriver<MobileElement> driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("deviceId","9d1a803c");
        caps.setCapability("platformName","android");
        caps.setCapability("automationName","UiAutomator2");
        caps.setCapability("appPackage","com.android.chrome");
        caps.setCapability("appActivity","com.google.android.apps.chrome.Main");
        caps.setCapability("noReset",true);

        URL serverUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver<>(serverUrl,caps);
        wait = new WebDriverWait(driver, 20);

        driver.get("https://www.training-support.net/selenium");
    }

    @Test
    public void toDo() throws InterruptedException, IOException {

        System.out.println("Its ok");
        String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";
        driver.findElement(MobileBy.AndroidUIAutomator(UiScrollable + ".scrollForward(7).scrollIntoView(text(\"To-Do List\"))")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AndroidUIAutomator("text(\"To-Do List\")")));

        String[] tasks = {"Add tasks to list", "Get number of tasks", "Clear the list"};

        Thread.sleep(2000);
        for (String task : tasks) {
            driver.findElementByClassName("android.widget.EditText").sendKeys(task);
            driver.findElement(MobileBy.AndroidUIAutomator("text(\"Add Task\")")).click();
        }

        List<MobileElement> addedTasks = driver.findElements(By.xpath("//android.view.View[contains(@resource-id,'tasksList')]//android.view.View"));
        System.out.println("No of Tasks : " +addedTasks.size());

        Thread.sleep(2000);
        driver.findElement(MobileBy.AndroidUIAutomator("text(\"Add tasks to list\")")).click();
        driver.findElement(MobileBy.AndroidUIAutomator("text(\"Get number of tasks\")")).click();
        driver.findElement(MobileBy.AndroidUIAutomator("text(\"Clear the list\")")).click();

        driver.findElementByXPath("//android.widget.TextView[contains(@text, 'Clear List')]").click();
        takeScreenshot();

        List<MobileElement> cleartask = driver.findElements(By.xpath("//android.view.View[contains(@resource-id,'tasksList')]//android.view.View"));
        Assert.assertEquals(cleartask.size(),0);
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

