package codedebug_javacore;

public class Main_15 {
    /*
    Bài tập 15: Chuyển số thành tháng
    Tên hàm là:
    public String getMonthFromNumber(int numberOfMonth) { }
    */
    public static String getMonthFromNumber(int numberOfMonth) {
        return switch (numberOfMonth) {
            case 1 -> "January";
            case 12 -> "December";
            default -> "Invalid month";
        };
    }

    public static void main(String[] args) {
        System.out.println(getMonthFromNumber(1));
        System.out.println(getMonthFromNumber(12));
        System.out.println(getMonthFromNumber(13));
    }
}
