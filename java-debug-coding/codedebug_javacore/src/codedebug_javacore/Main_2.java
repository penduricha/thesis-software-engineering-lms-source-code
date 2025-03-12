package codedebug_javacore;

public class Main_2 {
	/*
	 * Bài tập 2: Tính tổng chuỗi số lẻ hoặc chẵn Tên hàm là: public int
	 * calculate_Sum_Series_Of_Numbers(int n) { }
	 */
	public static int calculate_Sum_Series_Of_Numbers(int n) {
		if (n <= 0 || n % 1 != 0)
			return -1;
		int sum = 0;
		for (int i = (n % 2 == 0 ? 2 : 1); i <= n; i += 2) {
			sum += i;
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(calculate_Sum_Series_Of_Numbers(0));
		System.out.println(calculate_Sum_Series_Of_Numbers(-2));
		System.out.println(calculate_Sum_Series_Of_Numbers((int) 1.5));
		System.out.println(calculate_Sum_Series_Of_Numbers(5));
		System.out.println(calculate_Sum_Series_Of_Numbers(6));
	}
}
