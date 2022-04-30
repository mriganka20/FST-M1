package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = "src/test/java/features",
            glue = {"stepDefinitions"},
            tags = "@activity1, @activity2, @activity3, @activity4, @activity5",
            plugin = {"html: test-reports"},
            monochrome = true
    )

    public class ActivitiesRunner {
        //empty
    }

