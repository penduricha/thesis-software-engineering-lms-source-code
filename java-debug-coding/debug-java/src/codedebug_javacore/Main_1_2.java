package codedebug_javacore;

public class Main_1_2 {
	/*
	 * Bài tập 2. Viết hàm tính diện tích hình chữ nhật bằng Java. Tên hàm là:
	 * public double calculate_Area_Of_Rectangle(double width,double height) { }
	 */
	public static double calculate_Area_Of_Rectangle(double width, double height) {
		if (width == 0 || height == 0) {
			return 0;
		}
		if (width < 0 || height < 0) {
			return -1;
		}
		return width * height;
	}

	public static void main(String[] args) {
		System.out.println(calculate_Area_Of_Rectangle(0, 0));
		System.out.println(calculate_Area_Of_Rectangle(0, 23));
		System.out.println(calculate_Area_Of_Rectangle(23, 0));
		System.out.println(calculate_Area_Of_Rectangle(1, -12));
		System.out.println(calculate_Area_Of_Rectangle(-1, 12));
		System.out.println(calculate_Area_Of_Rectangle(2.5, 2.5));
		System.out.println(calculate_Area_Of_Rectangle(2, 2));
	}
}
