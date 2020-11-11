package com.freshCode.utilityChargesCalculator.service;

import org.junit.*;

public class SlabCalculatorTest {
    
    @Test
    public void shouldReturnFirstSlabWhenUnitsUnder50()
    {
        Slab slab = SlabCalculator.calculate(40);
        Assert.assertEquals(Slab.First, slab);
    }

    @Test
    public void shouldReturnSecondSlabWhenUnitsAbove50()
    {
        Slab slab = SlabCalculator.calculate(51);
        Assert.assertEquals(Slab.Second, slab);
    }

    @Test
    public void shouldReturnThirdSlabWhenUnitsAbove150()
    {
        Slab slab = SlabCalculator.calculate(151);
        Assert.assertEquals(Slab.Third, slab);
    }

    @Test
    public void shouldReturnFourthSlabWhenUnitsAbove300()
    {
        Slab slab = SlabCalculator.calculate(301);
        Assert.assertEquals(Slab.Fourth, slab);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ShouldThrowExceptionWhenLessThanZero()
    {
        SlabCalculator.calculate(-1);
    }

}
