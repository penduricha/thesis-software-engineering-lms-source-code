package codedebug_javacore;

public class Main_5 {
    /*
    Bài tập 5. Tìm kiếm phần tử trong mảng
    Tên hàm là:
    public int find_Element_Array(int[] array, int element_Find) { }
    */
    public static int find_Element_Array(int[] array, int element_Find) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element_Find) return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println(find_Element_Array(arr, 22));
        System.out.println(find_Element_Array(arr, 21));
        System.out.println(find_Element_Array(new int[]{}, 21));
    }
}
