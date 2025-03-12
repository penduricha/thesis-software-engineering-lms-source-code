
package codedebug_javacore;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main_13 {
	/*
	 * Bài tập 13: Lấy năm từ LocalDate Tên hàm là: public int
	 * getYearFromLocalDate(LocalDate date) { }
	 */
	public static int getYearFromLocalDate(LocalDate date) {
		try {
			return date.getYear();
		} catch (DateTimeParseException | IllegalArgumentException e) {
			return 1900;
		}
	}

	public static void main(String[] args) {
		System.out.println(getYearFromLocalDate(LocalDate.of(2000, 2, 2))); // 2000
		try {
			System.out.println(getYearFromLocalDate(LocalDate.of(2000, 2, 30))); // 1900
		} catch (Exception e) {
			System.out.println(1900);
		}
	}
}
