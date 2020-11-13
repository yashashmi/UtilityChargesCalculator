package com.freshCode.utilityChargesCalculator.service;

import com.freshCode.utilityChargesCalculator.repository.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class EnergyChargesCalculator {
	private EnergyChargesBaseRateProvider energyChargesBaseRate;

	@Autowired
	public EnergyChargesCalculator(@Qualifier("InMemory") EnergyChargesBaseRateProvider energyChargesBaseRate) {
		this.energyChargesBaseRate = energyChargesBaseRate;
	}

	public double calculateCharges(int i) {
		return i*energyChargesBaseRate.getRates(Slab.First);
		
	}

}
