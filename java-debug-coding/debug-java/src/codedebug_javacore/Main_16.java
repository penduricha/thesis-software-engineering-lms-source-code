package codedebug_javacore;

public class Main_16 {
    /*
    Bài tập 16: Tính chỉnh hợp chập k của n
    Tên hàm là:
    public int permutation(int n, int k) { }
    */
    public static int permutation(int n, int k) {
        if (k > n || k < 0) {
            return 0;
        }
        int result = 1;
        for (int i = n; i > n - k; i--) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(permutation(10, 5));
        System.out.println(permutation(5, 10));
        System.out.println(permutation(-5, 2));
        System.out.println(permutation(-5, -1));
    }
}
