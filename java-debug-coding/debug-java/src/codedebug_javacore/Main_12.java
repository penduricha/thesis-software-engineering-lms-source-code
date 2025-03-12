package codedebug_javacore;

import java.util.Arrays;

public class Main_12 {
    /*
    Bài tập 12: Tìm số trung vị trong mảng
    Tên hàm là:
    public double getMedian(double[] array) { }
    */
    public static double getMedian(double[] array) {
        if (array.length == 0) return 0.0;
        Arrays.sort(array);
        int mid = array.length / 2;
        return array.length % 2 == 0 ? (array[mid - 1] + array[mid]) / 2.0 : array[mid];
    }

    public static void main(String[] args) {
        System.out.println(getMedian(new double[]{1, 2, 3, 4, 5}));
        System.out.println(getMedian(new double[]{1, 2, 2, 4}));
        System.out.println(getMedian(new double[]{}));
    }
}
