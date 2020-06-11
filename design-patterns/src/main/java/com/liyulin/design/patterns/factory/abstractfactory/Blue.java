package com.liyulin.design.patterns.factory.abstractfactory;

public class Blue implements IColor {

	@Override
	public void fill() {
		System.out.println("Inside Blue::fill() method.");
	}
	
}