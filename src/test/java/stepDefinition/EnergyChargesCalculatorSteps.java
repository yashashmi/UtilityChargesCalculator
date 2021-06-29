package stepDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URISyntaxException;

import com.freshCode.utilityChargesCalculator.model.EnergyCharges;

import org.junit.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EnergyChargesCalculatorSteps {

	private String baseUrl;
	public EnergyChargesCalculatorSteps() {
		baseUrl = ConfigurationProvider.getInstance().getUrl();
	}

	ResponseEntity<EnergyCharges> response;
	private RestTemplate restTemplate = new RestTemplate();

	private UriComponentsBuilder builder;
	EnergyCharges energyCharges;

	@Given("I have consumed {int} units in current month")
	public void i_have_consumed_units_in_current_month(Integer consumedUnits) throws URISyntaxException {
		builder = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("unitsConsumed", consumedUnits.toString());
	}

	@When("I submit the input units")
	public void i_submit_the_input_units() {
		response = restTemplate.getForEntity(builder.build().encode().toUri(), EnergyCharges.class);
		Assert.assertNotNull(response);

		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Then("I should see total energy charges as {double}")
	public void i_should_see_total_energy_charges_as(Double expectedCharges) {
		EnergyCharges energyCharges = response.getBody();
		assertNotNull(energyCharges, "Didn't get a proper body response");

		assertEquals(expectedCharges, energyCharges.getTotalEnergyCharges(), 0.0);
	}

	@Then("I should see energy charges for first slab as {double}")
	public void i_should_see_energy_charges_for_first_slab_as(Double expectedCharges) {

		EnergyCharges energyCharges = response.getBody();

		assertNotNull(energyCharges, "Didn't get a proper body response");

		assertEquals(expectedCharges, energyCharges.getFirstSlabCharges(), 0.0);
	}

	@Then("I should see energy charges for second slab as {double}")
	public void i_should_see_energy_charges_for_second_slab_as(Double expectedCharges) {
		EnergyCharges energyCharges = response.getBody();

		assertNotNull(energyCharges, "Didn't get a proper body response");

		assertEquals(expectedCharges, energyCharges.getSecondSlabCharges(), 0.0);
	}

	@Then("I should see energy charges for third slab as {double}")
	public void i_should_see_energy_charges_for_third_slab_as(double expectedCharges) {
		EnergyCharges energyCharges = response.getBody();

		assertNotNull(energyCharges, "Didn't get a proper body response");

		assertEquals(expectedCharges, energyCharges.getThirdSlabCharges(), 0.0);
	}

	@Then("I should see energy charges for last slab as {double}")
	public void i_should_see_energy_charges_for_last_slab_as(Double expectedCharges) {
		EnergyCharges energyCharges = response.getBody();

		assertNotNull(energyCharges, "Didn't get a proper body response");

		assertEquals(expectedCharges, energyCharges.getLastSlabCharges(), 0.0);
	}
}