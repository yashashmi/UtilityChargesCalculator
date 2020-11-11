package com.freshCode.utilityChargesCalculator.repository;

import com.freshCode.utilityChargesCalculator.service.Slab;

public interface EnergyChargesBaseRateProvider {

    double getRates(Slab slab);
}