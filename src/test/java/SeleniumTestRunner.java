
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/", glue = { "uIStepDefinitions" }, plugin = {
    "html:target/cucumber-reports/Selenium/cucumber-pretty", "json:target/cucumber-reports/Selenium/CucumberTestReport.json" })
public class SeleniumTestRunner {
   
}
