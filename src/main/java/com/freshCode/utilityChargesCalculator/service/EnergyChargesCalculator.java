package com.freshCode.utilityChargesCalculator.service;

import com.freshCode.utilityChargesCalculator.repository.*;

public class EnergyChargesCalculator {
	private EnergyChargesBaseRateProvider energyChargesBaseRate;

	public EnergyChargesCalculator(EnergyChargesBaseRateProvider energyChargesBaseRate) {
		this.energyChargesBaseRate = energyChargesBaseRate;
	}

	public double calculateCharges(int i) {
		return i*energyChargesBaseRate.getRates(Slab.First);
		
	}

}
