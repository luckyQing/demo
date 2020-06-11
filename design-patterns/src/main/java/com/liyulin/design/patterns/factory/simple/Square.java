package com.liyulin.design.patterns.factory.simple;

public class Square implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Square::draw() method.");
	}
	
}