package com.liyulin.design.patterns.factory.abstractfactory;

public class Circle implements IShape {

	@Override
	public void draw() {
		System.out.println("Inside Circle::draw() method.");
	}
	
}