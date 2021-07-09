package uIStepDefinitions;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.*;
import uIStepDefinitions.utility.BaseUtil;

public class EnergyChargesCalculatorUISteps {

    BaseUtil baseUtil;
    String url = "http://localhost:8081";

    public EnergyChargesCalculatorUISteps(BaseUtil baseUtil) {
        this.baseUtil = baseUtil;
        baseUtil.Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Given("I have consumed {int} units in current month")
    public void i_have_consumed_units_in_current_month(Integer consumedUnits) {
        baseUtil.Driver.navigate().to(url);
        baseUtil.Driver.manage().window().maximize();
        WebElement unitTextBox = baseUtil.Driver.findElement(By.id("unitsConsumed"));

        unitTextBox.sendKeys(consumedUnits.toString());

    }

    @When("I submit the input units")
    public void i_submit_the_input_units() {
        baseUtil.Driver.findElement(By.id("submit")).click();
    }

    @Then("I should see energy charges for first slab as {double}")
    public void i_should_see_energy_charges_for_first_slab_as(Double expectedCharges) {
        WebElement chargesElement = baseUtil.Driver.findElement(By.xpath("/html/body/div/section/div[3]/table/tbody/tr[4]/td[4]"));
        Double actualCharges = Double.parseDouble(chargesElement.getText());
        Assert.assertEquals(expectedCharges, actualCharges, 0.01);
    }

    @Then("I should see energy charges for second slab as {double}")
    public void i_should_see_energy_charges_for_second_slab_as(Double expectedCharges) {
        WebElement chargesElement = baseUtil.Driver.findElement(By.xpath("/html/body/div/section/div[3]/table/tbody/tr[5]/td[4]"));
        Double actualCharges = Double.parseDouble(chargesElement.getText());
        Assert.assertEquals(expectedCharges, actualCharges, 0.01);
    }

    @Then("I should see energy charges for third slab as {double}")
    public void i_should_see_energy_charges_for_third_slab_as(Double expectedCharges) {
        WebElement chargesElement = baseUtil.Driver.findElement(By.xpath("/html/body/div/section/div[3]/table/tbody/tr[6]/td[4]"));
        Double actualCharges = Double.parseDouble(chargesElement.getText());
        Assert.assertEquals(expectedCharges, actualCharges, 0.01);
    }

    @Then("I should see energy charges for last slab as {double}")
    public void i_should_see_energy_charges_for_last_slab_as(Double expectedCharges) {
        WebElement chargesElement = baseUtil.Driver.findElement(By.xpath("/html/body/div/section/div[3]/table/tbody/tr[7]/td[4]"));
        Double actualCharges = Double.parseDouble(chargesElement.getText());
        Assert.assertEquals(expectedCharges, actualCharges, 0.01);
    }

    @Then("I should see total energy charges as {double}")
    public void i_should_see_total_energy_charges_as(Double expectedCharges) {
        WebElement chargesElement = baseUtil.Driver.findElement(By.xpath("/html/body/div/section/div[2]/table/tbody/tr[1]/td"));
        Double actualCharges = Double.parseDouble(chargesElement.getText());
        Assert.assertEquals(expectedCharges, actualCharges, 0.01);
    }
}
