package com.freshCode.utilityChargesCalculator.service;

import org.junit.*;

public class EnergyChargesCalculatorTest {

    @Test
    public void shouldReturnEnergyChargesForFirstSlabOfUnits() {
        EnergyChargesCalculator calculator = new EnergyChargesCalculator();
        double charges = calculator.calculateCharges(40);
        Assert.assertEquals(162.0, charges, 0.0);
    }
}
