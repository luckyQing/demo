package com.liyulin.design.patterns.factory.factorymethod;

public class SquareFactory implements IShapeFactory {

	@Override
	public IShape createShape() {
		return new Square();
	}

}