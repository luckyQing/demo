package com.liyulin.design.patterns.factory.factorymethod;

public class Square implements IShape {

	@Override
	public void draw() {
		System.out.println("Inside Square::draw() method.");
	}
	
}