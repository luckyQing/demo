package com.liyulin.algorithm.map;

import java.util.ArrayList;

/**
 * Floyd算法
 * 
 * <p>
 * 1、用于求解任意两点间的最短距离，时间复杂度为O(n^3)。<br>
 * 2、使用范围：通常可以在任何图中使用，包括有向图、带负权边的图。
 *
 * @author liyulin
 * @version 1.0 2013-10-16 下午10:55:36
 */
public class Floyd2 {

	private static final int INF = Integer.MAX_VALUE;// 无穷大
	private int[][] matrix = null;// 路径矩阵
	private int[][] distance = null;// 存储最短距离
	private Object[][] path = null;// 存储最短距离的路径

	public Floyd2(int[][] matrix) {
		this.matrix = matrix;
		int size = matrix.length;
		distance = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.distance[i][j] = matrix[i][j];
			}
		}

		path = new Object[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				path[i][j] = new ArrayList<Integer>();
			}
		}

		// 原始路径初始化
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				((ArrayList<Integer>) path[i][j]).add(i + 1);
				if (i != j) {
					((ArrayList<Integer>) path[i][j]).add(j + 1);
				}
			}
		}
	}

	/**
	 * 用Floyd算法求最短路径
	 * 
	 * @return int[][]
	 */
	@SuppressWarnings("unchecked")
	public int[][] getShortPath() {
		int size = matrix.length;

		/**
		 * 如果matrix[i][k] + matrix[k][j] < distance[i][j]， 则distance[i][j] =
		 * matrix[i][k] + matrix[k][j];
		 */
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int num = 0;
				for (int k = 0; k < size; k++) {
					if (matrix[k][j] != INF && matrix[i][k] != INF && matrix[i][k] + matrix[k][j] < distance[i][j]) {
						num++;
						distance[i][j] = matrix[i][k] + matrix[k][j];
						ArrayList<Integer> list = (ArrayList<Integer>) path[i][j];

						if (num > 1) {
							list.remove(list.size() - 2);
						}

						list.add(list.size() - 1, k + 1);// 更新最短距离的路径
					}
				}
			}
		}
		return distance;
	}

	/**
	 * 打印所有两点之间的最短路径
	 */
	public void showShortDistance() {
		this.getShortPath();// 求最短路径

		int size = distance.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(distance[i][j] + "  ");
			}
			System.out.println();
		}
	}

	/**
	 * 打印最短距离的路径
	 */
	@SuppressWarnings("unchecked")
	public void showPath() {
		int size = path.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print((i + 1) + "->" + (j + 1) + "：");
				ArrayList<Integer> list = (ArrayList<Integer>) path[i][j];
				System.out.println(list.toString());
			}
		}
	}

	public static void main(String[] args) {
		int[][] path = { { 0, 5, 1, 2, INF }, { 5, 0, 10, INF, 2 }, { 2, 3, 0, 2, 8 }, { 2, INF, 6, 0, 4 },
				{ INF, 2, 4, 4, 0 } };
		Floyd2 floydTest = new Floyd2(path);
		floydTest.showShortDistance();
		floydTest.showPath();
	}

}
