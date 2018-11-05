package com.liyulin.algorithm.map;

/**
 * Floyd算法
 * 
 * <p>
 * 1、用于求解任意两点间的最短距离，时间复杂度为O(n^3)。<br>
 * 2、使用范围：通常可以在任何图中使用，包括有向图、带负权边的图。
 *
 * @author liyulin
 * @version 1.0 2013-10-16 下午11:03:57
 */
public class Floyd1 {

	private static final int INF = Integer.MAX_VALUE;// 无穷大
	private int[][] matrix = null;// 路径矩阵

	public Floyd1(int[][] matrix) {
		this.matrix = matrix;
	}

	/**
	 * 用Floyd2算法求最短路径
	 * 
	 * @return int[][]
	 */
	public int[][] getShortPath() {
		int size = matrix.length;
		
		/**
		 * 如果matrix[i][k] + matrix[k][j] < distance[i][j]，
		 * 则distance[i][j] = matrix[i][k] + matrix[k][j];
		 */
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					if (matrix[k][j] != INF && 
							matrix[i][k] != INF	&& 
							matrix[i][k] + matrix[k][j] < matrix[i][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
					}
				}
			}
		}
		return matrix;
	}
	
	/**
	 * 打印所有两点之间的最短路径
	 */
	public void showShortDistance() {
		this.getShortPath();// 求最短路径
		
		int size = matrix.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(matrix[i][j] + "  ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int[][] path = { { 0, 5, 1, 2, INF }, { 5, 0, 10, INF, 2 },
				{ 2, 3, 0, 2, 8 }, { 2, INF, 6, 0, 4 }, { INF, 2, 4, 4, 0 } };
		Floyd1 Floyd2Test = new Floyd1(path);
		Floyd2Test.showShortDistance();
	}

}

