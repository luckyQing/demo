package com.liyulin.design.patterns.factory.abstractfactory;

public class Green implements IColor {

	@Override
	public void fill() {
		System.out.println("Inside Green::fill() method.");
	}
	
}