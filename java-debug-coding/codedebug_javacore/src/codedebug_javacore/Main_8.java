package codedebug_javacore;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main_8 {
    /*
    Bài tập 8: Kiểm tra ngày trước hiện tại
    Tên hàm là:
    public int checkDateBeforeNow(int year, int month, int day) { }
    */
    public static int checkDateBeforeNow(int year, int month, int day) {
        // Kiểm tra xem tháng có hợp lệ không (1-12)
        if (month < 1 || month > 12) return -1;

        try {
            LocalDate date = LocalDate.of(year, month, day);
            return date.isBefore(LocalDate.now()) ? 1 : 0;
        } catch (DateTimeParseException | IllegalArgumentException e) {
            return -1; // Nếu có lỗi ngày tháng, trả về -1
        }
    }

    public static void main(String[] args) {
        System.out.println(checkDateBeforeNow(2022, 12, 1)); // 1
        System.out.println(checkDateBeforeNow(2090, 12, 1)); // 0
        System.out.println(checkDateBeforeNow(1000, 0, 0)); // -1
    }
}
