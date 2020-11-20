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
        Mockito.when(energyChargesBaseRate.getRates(Slab.First)).thenReturn(4.05);
        Mockito.when(energyChargesBaseRate.getRates(Slab.Second)).thenReturn(4.95);
        Mockito.when(energyChargesBaseRate.getRates(Slab.Third)).thenReturn(6.3);
        Mockito.when(energyChargesBaseRate.getRates(Slab.Fourth)).thenReturn(6.5);
    }

    @Test
    public void shouldSetEnergyChargesForFirstSlabWhenTotalUnitsAre40() {
        double charges = calculator.calculateEnergyCharges(40).getFirstSlabCharges();
        Assert.assertEquals(162.0, charges, 0.0);
    }

    @Test
    public void shouldReturnEnergyChargesForSecondSlabWhenItIsBeyond150Units() {
        double charges = calculator.calculateEnergyCharges(155).getSecondSlabCharges();
        Assert.assertEquals(495.0, charges, 0.0);
    }

    @Test
    public void shouldSetEnergyChargesForThirdSlabWhenUnitsBeyond150() {
        double charges = calculator.calculateEnergyCharges(155).getThirdSlabCharges();
        Assert.assertEquals(31.5, charges, 0.0);
    }

    @Test
    public void shouldSetEnergyChargesForLastSlabWhenUnitsBeyond300() {
        double charges = calculator.calculateEnergyCharges(461).getLastSlabCharges();
        Assert.assertEquals(1046.5, charges, 0.0);
    }

    @Test
    public void shouldReturnTotalEnergyCharges()
    {
        double charges = calculator.calculateEnergyCharges(463).getTotalEnergyCharges();
        Assert.assertEquals(2702.0, charges, 0.0);
    }
}
