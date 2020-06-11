package com.liyulin.design.patterns.factory.factorymethod;

public class RectangleFactory implements IShapeFactory {

	@Override
	public IShape createShape() {
		return new Rectangle();
	}

}