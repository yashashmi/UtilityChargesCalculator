package com.freshCode.utilityChargesCalculator.repository;

import com.freshCode.utilityChargesCalculator.service.Slab;

import org.springframework.stereotype.Repository;

@Repository("InMemory")
public class EnergyChargesBaseRateProviderImpl implements EnergyChargesBaseRateProvider {

    @Override
    public double getRates(Slab slab) {
        if (slab == Slab.First) {
            return 4.05;
        } else if (slab == Slab.Second) {
            return 4.95;
        } else if (slab == Slab.Third) {
            return 6.3;
        }
        return 6.5;
    }

}