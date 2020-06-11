package com.liyulin.design.patterns.factory.abstractfactory;

public class ColorFactory extends AbstractFactory {
    
	   @Override
	   public IShape getShape(String shapeType){
	      return null;
	   }
	   
	   @Override
	   public IColor getColor(String color) {
	      if(color == null){
	         return null;
	      }        
	      if(color.equalsIgnoreCase("RED")){
	         return new Red();
	      } else if(color.equalsIgnoreCase("GREEN")){
	         return new Green();
	      } else if(color.equalsIgnoreCase("BLUE")){
	         return new Blue();
	      }
	      return null;
	   }
	}