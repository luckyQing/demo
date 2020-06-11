package com.liyulin.design.patterns.factory.simple;

/**
 * 简单工厂模式
 *
 * @author liyulin
 * @date 2020-06-11
 */
public class ShapeFactory {

	private static final int CIRCLE = 1;
	private static final int RECTANGLE = 1;
	private static final int SQUARE = 1;

	public static Shape createShape(int shapeType) {
		if (shapeType == CIRCLE) {
			return new Circle();
		} else if (shapeType == RECTANGLE) {
			return new Rectangle();
		} else if (shapeType == SQUARE) {
			return new Square();
		}
		return null;
	}

}