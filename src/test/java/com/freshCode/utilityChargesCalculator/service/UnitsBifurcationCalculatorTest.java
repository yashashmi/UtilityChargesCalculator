package com.freshCode.utilityChargesCalculator.service;

import org.junit.*;

public class UnitsBifurcationCalculatorTest {

    private static UnitsBifurcationCalculator calc;

    @BeforeClass
    public static void beforeClass() {
        calc = new UnitsBifurcationCalculator();
    }

    @Test
    public void shouldReturnFirstSlabUnits() {

        calc.calculate(40);
        int expectedUnits = calc.getFirstSlabUnits();

        Assert.assertEquals(40, expectedUnits);
    }

    @Test
    public void shouldReturn50UnitsInFirstSlabWhenTotalUnitsMoreThan50() {
        calc.calculate(65);
        int expectedFirstSlabUnits = calc.getFirstSlabUnits();

        Assert.assertEquals(50, expectedFirstSlabUnits);

    }

    @Test
    public void ShouldAssignUnitsInFirstSlabWhenTotalUnitsBetwhen150And300() {
        calc.calculate(165);
        int expectedUnitsInSecondSlab = calc.getFirstSlabUnits();

        Assert.assertEquals(50, expectedUnitsInSecondSlab);
    }

    @Test
    public void shouldReturnRemainingUnitsInSecondSlabWhenTotalUnitsMoreThan50() {
        calc.calculate(65);
        int expectedUnitsInSecondSlab = calc.getSecondSlabUnits();

        Assert.assertEquals(15, expectedUnitsInSecondSlab);

    }

    @Test
    public void shouldAssign100UnitsInSecondSlotWhenTotalUnitsMoreThan150() {
        calc.calculate(170);
        int expectedUnits = calc.getSecondSlabUnits();

        Assert.assertEquals(100, expectedUnits);
    }

    @Test
    public void shouldAssignUnitsInThirdSlotWhenTotalUnitsMoreThan150() {
        calc.calculate(170);
        int expectedUnits = calc.getThirdSlabUnits();

        Assert.assertEquals(20, expectedUnits);
    }

    @Test
    public void shouldAssign150UnitsInThirdSlotWhenTotalUnitsMoreThan300() {
        calc.calculate(330);
        int expectedUnits = calc.getThirdSlabUnits();

        Assert.assertEquals(150, expectedUnits);
    }

    @Test
    public void shouldAssignAllTheLeftOverUnitsInLastSlabWhenTotalUnitsMoreThan300() {
        calc.calculate(465);
        int expectedUnits = calc.getLastSlabUnits();

        Assert.assertEquals(165, expectedUnits);
    }

    @Test
    public void shouldAssign100UnitsInSecondSlotWhenTotalUnitsMoreThan300() {
        calc.calculate(330);
        int expectedUnits = calc.getSecondSlabUnits();

        Assert.assertEquals(100, expectedUnits);
    }

    @Test
    public void shouldAssign50UnitsInFirstSlotWhenTotalUnitsMoreThan300() {
        calc.calculate(330);
        int expectedUnits = calc.getFirstSlabUnits();

        Assert.assertEquals(50, expectedUnits);
    }

}
