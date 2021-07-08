package uIStepDefinitions;

import java.util.concurrent.TimeUnit;

import io.cucumber.java.en.*;
import uIStepDefinitions.utility.BaseUtil;

public class EnergyChargesCalculatorUISteps {


    BaseUtil baseUtil;
	String url = "http://localhost:8080";

	public EnergyChargesCalculatorUISteps(BaseUtil baseUtil) {
		this.baseUtil = baseUtil;
		baseUtil.Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
    
    @Given("I have consumed {int} units in current month")
    public void i_have_consumed_units_in_current_month(Integer int1) {
        baseUtil.Driver.navigate().to(url);
		baseUtil.Driver.manage().window().maximize();
    }

    @When("I submit the input units")
    public void i_submit_the_input_units() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("I should see energy charges for first slab as {double}")
    public void i_should_see_energy_charges_for_first_slab_as(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("I should see energy charges for second slab as {double}")
    public void i_should_see_energy_charges_for_second_slab_as(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("I should see energy charges for third slab as {double}")
    public void i_should_see_energy_charges_for_third_slab_as(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("I should see energy charges for last slab as {double}")
    public void i_should_see_energy_charges_for_last_slab_as(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("I should see total energy charges as {double}")
    public void i_should_see_total_energy_charges_as(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
