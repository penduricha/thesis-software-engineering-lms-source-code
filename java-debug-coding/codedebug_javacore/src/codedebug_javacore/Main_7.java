package codedebug_javacore;

import java.util.Arrays;

public class Main_7 {
    /*
    Bài tập 7: Giải phương trình bậc 2
    Tên hàm là:
    public double[] solveQuadraticEquation(double a, double b, double c) { }
    */
    public static double[] solveQuadraticEquation(double a, double b, double c) {
        if (a == 0) return new double[]{0.0, 0.0};
        double delta = b * b - 4 * a * c;
        if (delta > 0) return new double[]{(-b + Math.sqrt(delta)) / (2 * a), (-b - Math.sqrt(delta)) / (2 * a)};
        if (delta == 0) return new double[]{-b / (2 * a)};
        return new double[]{};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solveQuadraticEquation(1, -3, 2)));
        System.out.println(Arrays.toString(solveQuadraticEquation(1, 2, 1)));
        System.out.println(Arrays.toString(solveQuadraticEquation(1, 0, -4)));
        System.out.println(Arrays.toString(solveQuadraticEquation(1, -2, 5)));
        System.out.println(Arrays.toString(solveQuadraticEquation(0, 2, -4)));
    }
}
