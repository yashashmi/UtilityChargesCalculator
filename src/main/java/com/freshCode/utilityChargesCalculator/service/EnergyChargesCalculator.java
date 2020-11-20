package com.freshCode.utilityChargesCalculator.service;

import com.freshCode.utilityChargesCalculator.model.EnergyCharges;
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

	public EnergyCharges calculateEnergyCharges(int unitsConsumed) {
		EnergyCharges charges = new EnergyCharges();
		UnitsBifurcationCalculator bifurcationCalculator = new UnitsBifurcationCalculator();
		bifurcationCalculator.calculate(unitsConsumed);

		charges.setFirstSlabCharges(
				bifurcationCalculator.getFirstSlabUnits() * energyChargesBaseRate.getRates(Slab.First));

		charges.setSecondSlabCharges(
				bifurcationCalculator.getSecondSlabUnits() * energyChargesBaseRate.getRates(Slab.Second));

		charges.setThirdSlabCharges(
				bifurcationCalculator.getThirdSlabUnits() * energyChargesBaseRate.getRates(Slab.Third));

		charges.setLastSlabCharges(
				bifurcationCalculator.getLastSlabUnits() * energyChargesBaseRate.getRates(Slab.Fourth));

		calculateTotalEnergyCharges(charges);
		return charges;
	}

	private void calculateTotalEnergyCharges(EnergyCharges charges) {
		charges.setTotalEnergyCharges(charges.getFirstSlabCharges() + charges.getSecondSlabCharges()
				+ charges.getThirdSlabCharges() + charges.getLastSlabCharges());
	}

}
