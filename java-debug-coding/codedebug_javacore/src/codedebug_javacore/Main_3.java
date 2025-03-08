package codedebug_javacore;

public class Main_3 {
    /*
    Bài tập 3. Kiểm tra số nguyên tố
    Tên hàm là:
    public int isPrime(int n) { }
    */
    public static int isPrime(double d) {
        if (d < 2 || d % 1 != 0) return -1;
        for (int i = 2; i <= Math.sqrt(d); i++) {
            if (d % i == 0) return 0;
        }
        return 1;
    }

    public static void main(String[] args) {
        System.out.println(isPrime(-2));
        System.out.println(isPrime(1.5));
        System.out.println(isPrime(2));
        System.out.println(isPrime(4));
    }
}
