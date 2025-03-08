package codedebug_javacore;

public class Main_10 {
    /*
    Bài tập 10: Kiểm tra email hợp lệ
    Tên hàm là:
    public int validateEmail(String email) { }
    */
    public static int validateEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$") ? 1 : 0;
    }

    public static void main(String[] args) {
        System.out.println(validateEmail("abcd@gmail.com"));
        System.out.println(validateEmail("abcd@*()&.com"));
        System.out.println(validateEmail("abcd@gmail.com.vn"));
        System.out.println(validateEmail("abcd@gmail.com@.com"));
    }
}
