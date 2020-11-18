package com.freshCode.utilityChargesCalculator.api;

import com.freshCode.utilityChargesCalculator.model.EnergyCharges;
import com.freshCode.utilityChargesCalculator.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/energy")
@RestController
public class EnergyChargesController {

	EnergyChargesCalculator calculator;

	@Autowired
	public EnergyChargesController(EnergyChargesCalculator calculator) {
		this.calculator = calculator;

	}

	//http://localhost:8060/api/v1/energy/energyCharges?unitsConsumed=20
	@GetMapping("/energyCharges")
	public EnergyCharges calculateCharges(@RequestParam("unitsConsumed") Integer unitsConsumed) {
		return calculator.calculateEnergyCharges(unitsConsumed);
	}

}
