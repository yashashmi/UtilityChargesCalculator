package com.freshCode.utilityChargesCalculator.service;

public class SlabCalculator {

    public static Slab calculate(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Units Can't be less than 0.");
        }
        if (i > 0 && i < 51) {
            return Slab.First;
        } else if (i > 50 && i < 151) {
            return Slab.Second;
        } else if (i > 150 && i < 301) {
            return Slab.Third;
        }
        return Slab.Fourth;

    }

}
