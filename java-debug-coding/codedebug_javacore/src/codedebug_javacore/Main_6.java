package codedebug_javacore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Main_6 {
	/*
	 * Bài tập 6: Tính tổng Fibonacci Tên hàm là: public int fibonacciSum(int n) { }
	 */
	public static int fibonacciSum(int n) {
		if (n < 0)
			return -1;
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;

		int a = 0, b = 1, sum = 1; // Bắt đầu từ F(0) = 0, F(1) = 1
		for (int i = 2; i <= n; i++) { // Bắt đầu từ F(2)
			int next = a + b;
			sum += next;
			a = b;
			b = next;
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(fibonacciSum(-2)); 
		System.out.println(fibonacciSum(0)); 
		System.out.println(fibonacciSum(1)); 
		System.out.println(fibonacciSum(3)); 
		System.out.println(fibonacciSum(4)); 
		System.out.println(fibonacciSum(5)); 
	}
}