package stepDefinition;

import com.freshCode.utilityChargesCalculator.api.*;
import com.freshCode.utilityChargesCalculator.repository.EnergyChargesBaseRateProviderImpl;
import com.freshCode.utilityChargesCalculator.service.EnergyChargesCalculator;

import io.cucumber.java.en.*;
import org.junit.*;

public class EnergyChargesCalculatorSteps {

	EnergyChargesController chargesController;
	double charges = 0.0;

	@Given("I have consumed {int} units in current month")
	public void i_have_consumed_units_in_current_month(Integer consumedUnits) {
		EnergyChargesCalculator energyChargesCalculator = new EnergyChargesCalculator(new EnergyChargesBaseRateProviderImpl());
		chargesController = new EnergyChargesController(energyChargesCalculator);
		charges = chargesController.calculateCharges(consumedUnits);
	}

	@When("I submit the input units")
	public void i_submit_the_input_units() {

	}

	@Then("I should be able to view total energy charges as {double}")
	public void i_should_be_able_to_view_total_energy_charges_as(double int1) {
		Assert.assertEquals(int1, charges, 0.0);
	}

}
