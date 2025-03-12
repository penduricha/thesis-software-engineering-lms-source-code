package equationSolver;

import java.util.Scanner;

public class Combination {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập b: ");
        int b = scanner.nextInt();

        System.out.print("Nhập a: ");
        int a = scanner.nextInt();

        if (a > b || a < 0) {
            System.out.println("Giá trị a phải nhỏ hơn hoặc bằng b và cả a, b phải không âm.");
        } else {
            long result = permutation(b, a);
            System.out.println("Chỉnh hợp chập " + a + " của " + b + " là: " + result);
        }

        scanner.close();
    }

    public static long permutation(int b, int a) {
        long result = 1;
        for (int i = 0; i < a; i++) {
            result *= (b - i);
        }
        return result;
    }
}
