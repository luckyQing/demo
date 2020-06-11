package com.liyulin.design.patterns.factory.factorymethod;

public class CircleFactory implements IShapeFactory {

	@Override
	public IShape createShape() {
		return new Circle();
	}

}