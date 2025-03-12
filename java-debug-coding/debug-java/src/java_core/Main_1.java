package java_core;

public class Main_1 {
    /*
    Bài tập 1. Viết hàm tính chu vi hình chữ nhật bằng Java.
    Tên hàm là:
    public double calculate_Perimeter_Of_Rectangle(double width,double height) { }

     */
    public static double calculate_Perimeter_Of_Rectangle(double width,double height) {
        if(width == 0 || height == 0) {
            return 0;
        }
        if(width < 0 || height < 0) {
            return -1;
        }
        return (width + height) * 2;
    }

    public static void main(String[] args) {
       System.out.println(calculate_Perimeter_Of_Rectangle(0,0));
       System.out.println(calculate_Perimeter_Of_Rectangle(0,23));
       System.out.println(calculate_Perimeter_Of_Rectangle(23,0));
       System.out.println(calculate_Perimeter_Of_Rectangle(1,-12));
       System.out.println(calculate_Perimeter_Of_Rectangle(-1,12));
       System.out.println(calculate_Perimeter_Of_Rectangle(4.5,15.5));
       System.out.println(calculate_Perimeter_Of_Rectangle(5,15));
    }
}
