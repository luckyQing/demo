package com.liyulin.skills.grammar.array;

/***
 * 利用数组属性length遍历非矩阵数组
 */
public class ArrayDemo {

	public static void main(String[] args) {
		// 定义五个一维数组
		int array1[] = { 1 };
		int array2[] = { 1, 2 };
		int array3[] = { 1, 2, 3 };
		int array4[] = { 1, 2, 3, 4 };
		int array5[] = { 1, 2, 3, 4, 5 };
		// 定义一个非对称二维数组
		int[][] array = { array1, array2, array3, array4, array5 };
		/***
		 * 利用数组length属性遍历非对称二维数组
		 */
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.println(array[i][i] + "   ");
			}
			System.out.println();
		}

		/***
		 * 以下用for-each遍历
		 */
		for (int data : array[0]) {
			System.out.print(data + " ");
		}
		System.out.println("");
		for (int data : array[1]) {
			System.out.print(data + " ");
		}
		System.out.println("");
		for (int data : array[2]) {
			System.out.print(data + " ");
		}
		System.out.println("");
		for (int data : array[3]) {
			System.out.print(data + " ");
		}
		System.out.println("");
		for (int data : array[4]) {
			System.out.print(data + " ");
		}
		System.out.println("\n数组的大小：" + array1.length);
	}

}
