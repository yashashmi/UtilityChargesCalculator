package com.freshCode.utilityChargesCalculator.service;

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
        Mockito.when(energyChargesBaseRate.getRates(Slab.First)).thenReturn(4.05);
        double charges = calculator.calculateEnergyCharges(40).getTotalEnergyCharges();
        Assert.assertEquals(162.0, charges, 0.0);
    }

    @Test
    public void shouldReturnEnergyChargesFor50UnitsOfConsumption() {
        Mockito.when(energyChargesBaseRate.getRates(Slab.First)).thenReturn(4.05);
        double charges = calculator.calculateEnergyCharges(50).getTotalEnergyCharges();
        Assert.assertEquals(202.5, charges, 0.0);
    }

    @Test
    public void shouldReturnEnergyChargesFor100UnitsOfConsumption() {
        Mockito.when(energyChargesBaseRate.getRates(Slab.First)).thenReturn(4.05);
        Mockito.when(energyChargesBaseRate.getRates(Slab.Second)).thenReturn(4.95);

        double charges = calculator.calculateEnergyCharges(100).getTotalEnergyCharges();
        Assert.assertEquals(450, charges, 0.0);
    }

    // @Test
    // public void shouldReturnEnergyChargesForSecondSlabWhenItIsBeyond150Units() {
    //     Mockito.when(energyChargesBaseRate.getRates(Slab.First)).thenReturn(4.05);
    //     Mockito.when(energyChargesBaseRate.getRates(Slab.Second)).thenReturn(4.95);

    //     double charges = calculator.calculateEnergyCharges(155).getSecondSlabCharges();
    //     Assert.assertEquals(495.0, charges, 0.0);
    // }
}
