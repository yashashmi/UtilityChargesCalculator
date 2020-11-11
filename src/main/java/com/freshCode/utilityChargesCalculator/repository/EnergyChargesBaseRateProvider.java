package com.freshCode.utilityChargesCalculator.repository;

public interface EnergyChargesBaseRateProvider {

    double getRates(Slab slab);
}