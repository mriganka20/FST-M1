package Activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity4 {

        @Test
        public void firstTestCase() {
            System.out.println("I'm in first test case from demoOne Class");
        }

        @Test
        public void secondTestCase() {
            System.out.println("I'm in second test case from demoOne Class");
        }
    }