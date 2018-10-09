package com.liyulin.skills.grammar.keys;

import java.util.EnumMap;
import java.util.Map;

public class EnumDemo {
	
	public enum Color {
		RED, BLACK, WHITE
	}

	public static void main(String[] args) {
		Color color = Color.RED;

		switch (color) {
		case RED:
			System.out.println("RED");
			break;
		case BLACK:
			System.out.println("BLACK");
			break;
		case WHITE:
			System.out.println("WHITE");
			break;
		default:
			System.out.println("NO color");
		}

		Map<Color, Integer> map = new EnumMap<Color, Integer>(Color.class);
		map.put(Color.RED, 111);
		map.put(Color.BLACK, 222);
		map.put(Color.WHITE, 222);
		for (Map.Entry<Color, Integer> entry : map.entrySet()) {
			System.out.println(entry);
		}
	}
	
}
