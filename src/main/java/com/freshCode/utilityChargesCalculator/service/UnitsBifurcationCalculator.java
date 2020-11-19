package com.freshCode.utilityChargesCalculator.service;

public class UnitsBifurcationCalculator {
    private int firstSlabUnits;
    private int secondSlabUnits;
    private int thirdSlabUnits;
    private int lastSlabUnits;

    public void calculate(int i) {
        if (i <= 50) {
            firstSlabUnits = i;
            
        }
        if (i > 50 && i <= 150) {
            firstSlabUnits = 50;
            secondSlabUnits = i - 50;
        }
        if (i > 150 && i < 300) {
            firstSlabUnits = 50;
            secondSlabUnits = 100;
            thirdSlabUnits = i - 150;
        }
        if (i > 300) {
            firstSlabUnits = 50;
            secondSlabUnits = 100;
            thirdSlabUnits = 150;
            lastSlabUnits = i - 300;
        }

    }

    public int getFirstSlabUnits() {
        return this.firstSlabUnits;
    }

    public int getSecondSlabUnits() {
        return this.secondSlabUnits;
    }

    public int getThirdSlabUnits() {
        return this.thirdSlabUnits;
    }

    public int getLastSlabUnits() {
        return this.lastSlabUnits;
    }

}
