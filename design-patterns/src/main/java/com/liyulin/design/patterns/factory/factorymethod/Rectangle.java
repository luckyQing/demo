package com.liyulin.design.patterns.factory.factorymethod;

public class Rectangle implements IShape {

	@Override
	public void draw() {
		System.out.println("Inside Rectangle::draw() method.");
	}
	
}