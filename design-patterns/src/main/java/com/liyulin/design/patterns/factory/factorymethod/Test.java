package com.liyulin.design.patterns.factory.factorymethod;

/**
 * 工厂方法模式：将类的实例化推迟到子类中
 *
 * @author liyulin
 * @date 2020-06-11
 */
public class Test {

	public static void main(String[] args) {
		IShapeFactory circleFactory = new CircleFactory();
		circleFactory.createShape().draw();

		IShapeFactory rectangleFactory = new RectangleFactory();
		rectangleFactory.createShape().draw();

		IShapeFactory squareFactory = new SquareFactory();
		squareFactory.createShape().draw();
	}

}