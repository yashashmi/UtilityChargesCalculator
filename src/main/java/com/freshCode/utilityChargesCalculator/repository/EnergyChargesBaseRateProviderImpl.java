package com.freshCode.utilityChargesCalculator.repository;

import com.freshCode.utilityChargesCalculator.service.Slab;

import org.springframework.stereotype.Repository;

@Repository("InMemory")
public class EnergyChargesBaseRateProviderImpl implements EnergyChargesBaseRateProvider{

    @Override
    public double getRates(Slab slab) {
        // Hard coded string for testing purposes
        return 4.05;
    }

}