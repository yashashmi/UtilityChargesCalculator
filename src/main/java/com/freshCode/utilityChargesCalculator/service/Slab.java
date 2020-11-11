package com.freshCode.utilityChargesCalculator.service;

public enum Slab {
	First(1), Second(2), Third(3), Fourth(4);

	private int value;

	Slab(int intValue) {
		value = intValue;
	}
	public int getValue(){
		return value;
	}

}
