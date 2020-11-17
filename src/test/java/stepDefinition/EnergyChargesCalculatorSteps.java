package stepDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freshCode.utilityChargesCalculator.api.*;

import io.cucumber.java.en.*;
import org.junit.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class EnergyChargesCalculatorSteps {

	EnergyChargesController chargesController;

	ResponseEntity<String> response;
	String baseUrl = "http://localhost:8060/api/v1/energy/energyCharges";

	@Given("I have consumed {int} units in current month")
	public void i_have_consumed_units_in_current_month(Integer consumedUnits) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		// URI uri = new URI(baseUrl);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("unitsConsumed",
				consumedUnits.toString());
		response = restTemplate.getForEntity(builder.build().encode().toUri(), String.class);

		Assert.assertNotNull(response);

		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@When("I submit the input units")
	public void i_submit_the_input_units() {

	}

	@Then("I should be able to view total energy charges as {double}")
	public void i_should_be_able_to_view_total_energy_charges_as(double expectedCharges)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());

		assertEquals(expectedCharges, root.asDouble(), 0.0);
	}

}
