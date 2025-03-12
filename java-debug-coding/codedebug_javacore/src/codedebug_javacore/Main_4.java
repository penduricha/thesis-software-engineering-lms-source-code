package codedebug_javacore;

import java.util.Arrays;

public class Main_4 {
	/*
	 * Bài tập 4. Sắp xếp mảng InterchangeSort Tên hàm là: public int[]
	 * interchangeSort(int[] array) { }
	 */
	public static int[] interchangeSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] > array[j]) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		return array;
	}

	public static void main(String[] args) {
		int[] arr = { 64, 34, 25, 12, 22, 11, 90 };
		System.out.println(Arrays.toString(interchangeSort(arr)));
	}
}
