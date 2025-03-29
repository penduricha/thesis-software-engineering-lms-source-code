INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (1, 'public static void main(String[] args) {
    System.out.println(calculate_Perimeter_Of_Rectangle(0, 0));
	System.out.println(calculate_Perimeter_Of_Rectangle(0, 23));
	System.out.println(calculate_Perimeter_Of_Rectangle(23, 0));
	System.out.println(calculate_Perimeter_Of_Rectangle(1, -12));
	System.out.println(calculate_Perimeter_Of_Rectangle(-1, 12));
	System.out.println(calculate_Perimeter_Of_Rectangle(4.5, 15.5));
	System.out.println(calculate_Perimeter_Of_Rectangle(5, 15));
}', 'public static double calculate_Perimeter_Of_Rectangle(double width, double height) {
}', 'Viết hàm tính chu vi hình chữ nhật bằng Java.');
INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (2, 'public static void main(String[] args) {
        System.out.println(calculate_Area_Of_Rectangle(0, 0));
		System.out.println(calculate_Area_Of_Rectangle(0, 23));
		System.out.println(calculate_Area_Of_Rectangle(23, 0));
		System.out.println(calculate_Area_Of_Rectangle(1, -12));
		System.out.println(calculate_Area_Of_Rectangle(-1, 12));
		System.out.println(calculate_Area_Of_Rectangle(2.5, 2.5));
		System.out.println(calculate_Area_Of_Rectangle(2, 2));
}', 'public static double calculate_Area_Of_Rectangle(double width, double height) {
}', 'Viết hàm tính diện tích hình chữ nhật bằng Java.');
INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (3, 'public static void main(String[] args) {
        System.out.println(calculate_Sum_Series_Of_Numbers(0));
		System.out.println(calculate_Sum_Series_Of_Numbers(-2));
		System.out.println(calculate_Sum_Series_Of_Numbers(5));
		System.out.println(calculate_Sum_Series_Of_Numbers(6));
}', 'public static int calculate_Sum_Series_Of_Numbers(int n) {
}', 'Viết chương trình in ra tổng 1+3+5+7 …. +n nếu n là số lẻ, 2+4+6+ …. n nếu n là số chẵn.');
INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (4, 'public static void main(String[] args) {
        System.out.println(isPrime(-2));
        System.out.println(isPrime(2));
        System.out.println(isPrime(4));
}', 'public static int isPrime(int n) {
}', 'Kiểm tra n có phải số nguyên tố hay không.');
INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (5, 'public static void main(String[] args) {
        int[] arr = { 64, 34, 25, 12, 22, 11, 90 };
		System.out.println(Arrays.toString(interchangeSort(arr)));
}', 'public static int[] interchangeSort(int[] array) {
}', 'Viết hàm sắp xếp mảng InterchangeSort.');
INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (6, 'public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println(find_Element_Array(arr, 22));
        System.out.println(find_Element_Array(arr, 21));
        System.out.println(find_Element_Array(new int[]{}, 21));
}', 'public static int find_Element_Array(int[] array, int element_Find) {
}', 'Tìm kiếm phần tử trong mảng.');
INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (7, 'public static void main(String[] args) {
        System.out.println(fibonacciSum(-2)); 
		System.out.println(fibonacciSum(0)); 
		System.out.println(fibonacciSum(1)); 
		System.out.println(fibonacciSum(3)); 
		System.out.println(fibonacciSum(4)); 
		System.out.println(fibonacciSum(5)); 
}', 'public static int fibonacciSum(int n) {
}', 'Tính tổng fibonacci. Dãy Fibonacci là dãy vô hạn các số tự nhiên bắt đầu bằng hai phần tử 0 hoặc 1 và 1, các phần tử sau đó được thiết lập theo quy tắc mỗi phần tử luôn bằng tổng hai phần tử trước nó.');
INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (8, 'public static void main(String[] args) {
        System.out.println(Arrays.toString(solveQuadraticEquation(1, -3, 2)));
        System.out.println(Arrays.toString(solveQuadraticEquation(1, 2, 1)));
        System.out.println(Arrays.toString(solveQuadraticEquation(1, 0, -4)));
        System.out.println(Arrays.toString(solveQuadraticEquation(1, -2, 5)));
        System.out.println(Arrays.toString(solveQuadraticEquation(0, 2, -4)));
}', 'public static double[] solveQuadraticEquation(double a, double b, double c) {
}', 'Giải phương trình bậc 2 sử dụng mảng trả về tập nghiệm. (Nếu a = 0 sẽ không tính là phương trình bậc 2.)');
INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (9, 'public static void main(String[] args) {
        System.out.println(checkDateBeforeNow(2022, 12, 1)); // 1
        System.out.println(checkDateBeforeNow(2090, 12, 1)); // 0
        System.out.println(checkDateBeforeNow(1000, 0, 0)); // -1
}', 'public static int checkDateBeforeNow(int year, int month, int day) {
}', 'Sử dụng LocalDate để kiểm tra ngày 1/12/2022 có trước ngày hiện tại hay không. Trả về -1 nếu có ngoại lệ về ngày tháng.');
INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (11, 'public static void main(String[] args) {
        System.out.println(validateEmail("abcd@gmail.com"));
        System.out.println(validateEmail("abcd@*()&.com"));
        System.out.println(validateEmail("abcd@gmail.com@.com"));
}', 'public static int validateEmail(String email) {
}', 'Sử dụng hàm kiểm tra biểu thức chính quy kiểm tra chuỗi đó tuân thủ theo cú pháp email hay không. Cho cú pháp biểu thức chính quy: "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"');
INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (12, 'public static void main(String[] args) {
	System.out.println(getCovertedDoubleFromInt(1));
	System.out.println(getCovertedDoubleFromInt(2));
}', 'public static double getCovertedDoubleFromInt(int n) {
}', 'Sử dụng hàm chuyển đổi kiểu dữ liệu từ int thành double.');
INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (13, 'public static void main(String[] args) {
    System.out.println(getMedian(new double[]{1, 2, 3, 4, 5}));
    System.out.println(getMedian(new double[]{1, 2, 2, 4}));
    System.out.println(getMedian(new double[]{}));
}', 'public static double getMedian(double[] array) {
}', 'Tìm số trung vị trong mảng.\\nĐiều kiện để tính số trung vị phải sắp xếp mảng tăng dần. Số trung vị là:
Nếu tổng phần tử trong mảng là lẻ, số trung vị là phần tử ở giữa trong mảng.
Nếu tổng phần tử trong mảng là chẵn, số trung vị là trung bình của ((tổng phần tử mảng/2 +1) + (tổng phần tử mảng/2 -1))/2.
Trả về 0.0 nếu mảng rỗng.');
INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (14, 'public static void main(String[] args) {
        System.out.println(getYearFromLocalDate(LocalDate.of(2000, 2, 2))); // 2000
		try {
			System.out.println(getYearFromLocalDate(LocalDate.of(2000, 2, 30))); // 1900
		} catch (Exception e) {
			System.out.println(1900);
		}
}', 'public static int getYearFromLocalDate(LocalDate date) {
}', 'Lấy năm từ hàm LocalDate cho trước. Bắt ngoại lệ ngày tháng, trả về năm 1900.');
INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (15, 'public static void main(String[] args) {
        System.out.println(compare_2_Strings("Hello", "hello"));
        System.out.println(compare_2_Strings("Hello", "helllo"));
}', 'public static int compare_2_Strings(String str1, String str2) {
}', 'Sử dụng hàm so sánh 2 chuỗi bỏ qua chữ thường chữ hoa.<br>
Ví dụ: Chuỗi “Hello” và “hello” kết quả ra 1, chuỗi “Hello” và “helllo” kết quả ra 0.');
INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (17, 'public static void main(String[] args) {
    System.out.println(permutation(10, 5));
    System.out.println(permutation(5, 10));
    System.out.println(permutation(-5, 2));
    System.out.println(permutation(-5, -1));
}', 'public static long permutation(int b, int a) {
}', 'Tính chỉnh hợp chập k của n.
Chỉnh hợp chập k của n: n!/(n-k)!
Trả về 0 nếu k > n, k hoặc n đều âm.');
INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (24, 'public static void main(String[] args) {
    System.out.println(hieuHaiSo(5,2));
    System.out.println(hieuHaiSo(2,3));
}', 'public static int hieuHaiSo(int a, int b) {
}', 'Tính hiệu');
INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (27, 'public static void main(String[] args) {
	System.out.println(tich(1,2));
    System.out.println(tich(1,2));
}', 'public static int tich(int a, int b) {
}', 'tinh tích');
