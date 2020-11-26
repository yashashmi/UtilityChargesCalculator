package com.freshCode.utilityChargesCalculator.api;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
@CrossOrigin(origins = "*")
public class WelcomeController {
    
    @GetMapping("/")
	public String welcome() {
		return "Welcome to Utility App";
	}

}
