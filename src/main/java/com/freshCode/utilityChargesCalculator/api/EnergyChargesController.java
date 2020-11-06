package com.freshCode.utilityChargesCalculator.api;

import com.freshCode.utilityChargesCalculator.service.*;
import org.springframework.beans.factory.annotation.Autowired;

public class EnergyChargesController {

	EnergyChargesCalculator calculator;

	@Autowired
	public EnergyChargesController(EnergyChargesCalculator calculator) {
		this.calculator = calculator;

	}

	public double calculateCharges(Integer consumedUnits) {
		return calculator.calculateCharges(consumedUnits);
	}

}
