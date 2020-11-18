package com.freshCode.utilityChargesCalculator.service;

import com.freshCode.utilityChargesCalculator.model.EnergyCharges;
import com.freshCode.utilityChargesCalculator.repository.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class EnergyChargesCalculator {
	private EnergyChargesBaseRateProvider energyChargesBaseRate;

	private EnergyCharges charges;

	@Autowired
	public EnergyChargesCalculator(@Qualifier("InMemory") EnergyChargesBaseRateProvider energyChargesBaseRate) {
		this.energyChargesBaseRate = energyChargesBaseRate;
		
	}

	public EnergyCharges calculateEnergyCharges(int unitsConsumed) {
		this.charges = new EnergyCharges();
		if (unitsConsumed - 50 > 0) {

			charges.setFirstSlabCharges(50 * energyChargesBaseRate.getRates(Slab.First));
			unitsConsumed -= 50;
			charges.setSecondSlabCharges(unitsConsumed * energyChargesBaseRate.getRates(Slab.Second));
		} else {
			charges.setFirstSlabCharges(unitsConsumed * energyChargesBaseRate.getRates(Slab.First));
		}

		calculateTotalEnergyCharges();
		return charges;
	}

	private void calculateTotalEnergyCharges() {
		charges.setTotalEnergyCharges(charges.getFirstSlabCharges() + charges.getSecondSlabCharges()
				+ charges.getThirdSlabCharges() + charges.getLastSlabCharges());
	}

}
