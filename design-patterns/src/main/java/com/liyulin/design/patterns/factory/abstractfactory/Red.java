package com.liyulin.design.patterns.factory.abstractfactory;

public class Red implements IColor {

	@Override
	public void fill() {
		System.out.println("Inside Red::fill() method.");
	}
	
}