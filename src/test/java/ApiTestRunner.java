import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/", glue = { "stepDefinition" }, plugin = {
		"html:target/cucumber-reports/API/cucumber-pretty", "json:target/cucumber-reports/API/CucumberTestReport.json" })
public class ApiTestRunner {

}
