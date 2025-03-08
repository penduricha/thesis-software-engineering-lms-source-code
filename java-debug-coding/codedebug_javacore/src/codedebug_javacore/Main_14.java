package codedebug_javacore;

public class Main_14 {
    /*
    Bài tập 14: So sánh chuỗi không phân biệt hoa thường
    Tên hàm là:
    public int compare_2_Strings(String str1, String str2) { }
    */
    public static int compare_2_Strings(String str1, String str2) {
        return str1.equalsIgnoreCase(str2) ? 1 : 0;
    }

    public static void main(String[] args) {
        System.out.println(compare_2_Strings("Hello", "hello"));
        System.out.println(compare_2_Strings("Hello", "helllo"));
    }
}
