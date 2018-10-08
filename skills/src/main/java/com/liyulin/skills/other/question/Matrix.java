package com.liyulin.skills.other.question;

/**
 * 打印下面矩阵： 1 1 1 1 1 1 1 1 2 2 2 2 2 1 1 2 3 3 3 2 1 1 2 3 4 3 2 1 1 2 3 3 3 2 1
 * 1 2 2 2 2 2 1 1 1 1 1 1 1 1
 * 
 * @author liyulin
 * @version 2013-10-16 下午5:38:23
 */
public class Matrix {

	/**
	 * 求指定大小的“回”字矩阵
	 * 
	 * @param n 矩阵的大小
	 * @return int[][]
	 */
	public static int[][] getMatrix(int n) {
		int[][] arr = new int[n][n];
		int size = arr.length;
		// 上半部分
		for (int i = 0; i <= size / 2; i++) {// 纵向
			for (int j = 0; j < size; j++) {// 橫向
				if (j >= i && i + j + 1 <= size) {
					arr[i][j] = i + 1;
				} else if (j < i) {
					arr[i][j] = j + 1;
				} else {
					arr[i][j] = arr[i][size - j - 1];
				}
			}
		}
		// 下半部分
		for (int i = (size / 2) + 1; i < size; i++) {
			for (int j = 0; j < size; j++) {
				arr[i][j] = arr[size - i - 1][j];
			}
		}

		return arr;
	}

	public static void main(String[] args) {
		int[][] array = Matrix.getMatrix(7);
		for (int i = 0, size = array.length; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(array[i][j] + "  ");
			}
			System.out.println();
		}
	}

}
