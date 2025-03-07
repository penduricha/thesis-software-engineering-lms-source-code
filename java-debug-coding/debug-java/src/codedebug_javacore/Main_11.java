package codedebug_javacore;

public class Main_11 {
    /*
    Bài tập 11: Chuyển đổi int thành double
    Tên hàm là:
    public double getCovertedDoubleFromInt(int n); 
    */
    public static double getCovertedDoubleFromInt(double n) {
        return n;
    }

    public static void main(String[] args) {
        System.out.println(getCovertedDoubleFromInt(1)); // 1.0
        System.out.println(getCovertedDoubleFromInt(2)); // 2.0
        System.out.println(getCovertedDoubleFromInt(1.0 / 2.0)); // 0.5
    }
}
