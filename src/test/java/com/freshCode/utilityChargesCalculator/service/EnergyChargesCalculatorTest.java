package com.freshCode.utilityChargesCalculator.service;

import static org.mockito.ArgumentMatchers.any;

import com.freshCode.utilityChargesCalculator.repository.*;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;

@RunWith(MockitoJUnitRunner.class)
public class EnergyChargesCalculatorTest {

    @Mock
    private EnergyChargesBaseRateProvider energyChargesBaseRate;

    private EnergyChargesCalculator calculator;

    @Before
    public void BeforeClass() {
        calculator = new EnergyChargesCalculator(energyChargesBaseRate);

    }

    @Test
    public void shouldReturnEnergyChargesFor40UnitsOfConsumption() {
        Mockito.when(energyChargesBaseRate.getRates(any(Slab.class))).thenReturn(4.05);
        double charges = calculator.calculateCharges(40);
        Assert.assertEquals(162.0, charges, 0.0);
    }

    @Test
    public void shouldReturnEnergyChargesFor50UnitsOfConsumption() {
        Mockito.when(energyChargesBaseRate.getRates(any(Slab.class))).thenReturn(4.05);
        double charges = calculator.calculateCharges(50);
        Assert.assertEquals(202.5, charges, 0.0);
    }
}
