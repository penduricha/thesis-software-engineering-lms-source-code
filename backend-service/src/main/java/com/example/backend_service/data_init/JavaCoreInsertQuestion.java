package com.example.backend_service.data_init;

public class JavaCoreInsertQuestion {
    public static String getStringSQLInsert() {
        return "INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (1, 'public static void main(String[] args) {\n" +
                "    System.out.println(calculate_Perimeter_Of_Rectangle(0, 0));\n" +
                "\tSystem.out.println(calculate_Perimeter_Of_Rectangle(0, 23));\n" +
                "\tSystem.out.println(calculate_Perimeter_Of_Rectangle(23, 0));\n" +
                "\tSystem.out.println(calculate_Perimeter_Of_Rectangle(1, -12));\n" +
                "\tSystem.out.println(calculate_Perimeter_Of_Rectangle(-1, 12));\n" +
                "\tSystem.out.println(calculate_Perimeter_Of_Rectangle(4.5, 15.5));\n" +
                "\tSystem.out.println(calculate_Perimeter_Of_Rectangle(5, 15));\n" +
                "}', 'public static double calculate_Perimeter_Of_Rectangle(double width, double height) {\n" +
                "}', 'Viết hàm tính chu vi hình chữ nhật bằng Java.');\n" +
                "INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (2, 'public static void main(String[] args) {\n" +
                "        System.out.println(calculate_Area_Of_Rectangle(0, 0));\n" +
                "\t\tSystem.out.println(calculate_Area_Of_Rectangle(0, 23));\n" +
                "\t\tSystem.out.println(calculate_Area_Of_Rectangle(23, 0));\n" +
                "\t\tSystem.out.println(calculate_Area_Of_Rectangle(1, -12));\n" +
                "\t\tSystem.out.println(calculate_Area_Of_Rectangle(-1, 12));\n" +
                "\t\tSystem.out.println(calculate_Area_Of_Rectangle(2.5, 2.5));\n" +
                "\t\tSystem.out.println(calculate_Area_Of_Rectangle(2, 2));\n" +
                "}', 'public static double calculate_Area_Of_Rectangle(double width, double height) {\n" +
                "}', 'Viết hàm tính diện tích hình chữ nhật bằng Java.');\n" +
                "INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (3, 'public static void main(String[] args) {\n" +
                "        System.out.println(calculate_Sum_Series_Of_Numbers(0));\n" +
                "\t\tSystem.out.println(calculate_Sum_Series_Of_Numbers(-2));\n" +
                "\t\tSystem.out.println(calculate_Sum_Series_Of_Numbers(5));\n" +
                "\t\tSystem.out.println(calculate_Sum_Series_Of_Numbers(6));\n" +
                "}', 'public static int calculate_Sum_Series_Of_Numbers(int n) {\n" +
                "}', 'Viết chương trình in ra tổng 1+3+5+7 …. +n nếu n là số lẻ, 2+4+6+ …. n nếu n là số chẵn.');\n" +
                "INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (4, 'public static void main(String[] args) {\n" +
                "        System.out.println(isPrime(-2));\n" +
                "        System.out.println(isPrime(2));\n" +
                "        System.out.println(isPrime(4));\n" +
                "}', 'public static int isPrime(int n) {\n" +
                "}', 'Kiểm tra n có phải số nguyên tố hay không.');\n" +
                "INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (5, 'public static void main(String[] args) {\n" +
                "        int[] arr = { 64, 34, 25, 12, 22, 11, 90 };\n" +
                "\t\tSystem.out.println(Arrays.toString(interchangeSort(arr)));\n" +
                "}', 'public static int[] interchangeSort(int[] array) {\n" +
                "}', 'Viết hàm sắp xếp mảng InterchangeSort.');\n" +
                "INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (6, 'public static void main(String[] args) {\n" +
                "        int[] arr = {64, 34, 25, 12, 22, 11, 90};\n" +
                "        System.out.println(find_Element_Array(arr, 22));\n" +
                "        System.out.println(find_Element_Array(arr, 21));\n" +
                "        System.out.println(find_Element_Array(new int[]{}, 21));\n" +
                "}', 'public static int find_Element_Array(int[] array, int element_Find) {\n" +
                "}', 'Tìm kiếm phần tử trong mảng.');\n" +
                "INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (7, 'public static void main(String[] args) {\n" +
                "        System.out.println(fibonacciSum(-2)); \n" +
                "\t\tSystem.out.println(fibonacciSum(0)); \n" +
                "\t\tSystem.out.println(fibonacciSum(1)); \n" +
                "\t\tSystem.out.println(fibonacciSum(3)); \n" +
                "\t\tSystem.out.println(fibonacciSum(4)); \n" +
                "\t\tSystem.out.println(fibonacciSum(5)); \n" +
                "}', 'public static int fibonacciSum(int n) {\n" +
                "}', 'Tính tổng fibonacci. Dãy Fibonacci là dãy vô hạn các số tự nhiên bắt đầu bằng hai phần tử 0 hoặc 1 và 1, các phần tử sau đó được thiết lập theo quy tắc mỗi phần tử luôn bằng tổng hai phần tử trước nó.');\n" +
                "INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (8, 'public static void main(String[] args) {\n" +
                "        System.out.println(Arrays.toString(solveQuadraticEquation(1, -3, 2)));\n" +
                "        System.out.println(Arrays.toString(solveQuadraticEquation(1, 2, 1)));\n" +
                "        System.out.println(Arrays.toString(solveQuadraticEquation(1, 0, -4)));\n" +
                "        System.out.println(Arrays.toString(solveQuadraticEquation(1, -2, 5)));\n" +
                "        System.out.println(Arrays.toString(solveQuadraticEquation(0, 2, -4)));\n" +
                "}', 'public static double[] solveQuadraticEquation(double a, double b, double c) {\n" +
                "}', 'Giải phương trình bậc 2 sử dụng mảng trả về tập nghiệm. (Nếu a = 0 sẽ không tính là phương trình bậc 2.)');\n" +
                "INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (9, 'public static void main(String[] args) {\n" +
                "        System.out.println(checkDateBeforeNow(2022, 12, 1)); // 1\n" +
                "        System.out.println(checkDateBeforeNow(2090, 12, 1)); // 0\n" +
                "        System.out.println(checkDateBeforeNow(1000, 0, 0)); // -1\n" +
                "}', 'public static int checkDateBeforeNow(int year, int month, int day) {\n" +
                "}', 'Sử dụng LocalDate để kiểm tra ngày 1/12/2022 có trước ngày hiện tại hay không. Trả về -1 nếu có ngoại lệ về ngày tháng.');\n" +
                "INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (11, 'public static void main(String[] args) {\n" +
                "        System.out.println(validateEmail(\"abcd@gmail.com\"));\n" +
                "        System.out.println(validateEmail(\"abcd@*()&.com\"));\n" +
                "        System.out.println(validateEmail(\"abcd@gmail.com@.com\"));\n" +
                "}', 'public static int validateEmail(String email) {\n" +
                "}', 'Sử dụng hàm kiểm tra biểu thức chính quy kiểm tra chuỗi đó tuân thủ theo cú pháp email hay không. Cho cú pháp biểu thức chính quy: \"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$\"');\n" +
                "INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (12, 'public static void main(String[] args) {\n" +
                "\tSystem.out.println(getCovertedDoubleFromInt(1));\n" +
                "\tSystem.out.println(getCovertedDoubleFromInt(2));\n" +
                "}', 'public static double getCovertedDoubleFromInt(int n) {\n" +
                "}', 'Sử dụng hàm chuyển đổi kiểu dữ liệu từ int thành double.');\n" +
                "INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (13, 'public static void main(String[] args) {\n" +
                "    System.out.println(getMedian(new double[]{1, 2, 3, 4, 5}));\n" +
                "    System.out.println(getMedian(new double[]{1, 2, 2, 4}));\n" +
                "    System.out.println(getMedian(new double[]{}));\n" +
                "}', 'public static double getMedian(double[] array) {\n" +
                "}', 'Tìm số trung vị trong mảng.\\\\nĐiều kiện để tính số trung vị phải sắp xếp mảng tăng dần. Số trung vị là:\n" +
                "Nếu tổng phần tử trong mảng là lẻ, số trung vị là phần tử ở giữa trong mảng.\n" +
                "Nếu tổng phần tử trong mảng là chẵn, số trung vị là trung bình của ((tổng phần tử mảng/2 +1) + (tổng phần tử mảng/2 -1))/2.\n" +
                "Trả về 0.0 nếu mảng rỗng.');\n" +
                "INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (14, 'public static void main(String[] args) {\n" +
                "        System.out.println(getYearFromLocalDate(LocalDate.of(2000, 2, 2))); // 2000\n" +
                "\t\ttry {\n" +
                "\t\t\tSystem.out.println(getYearFromLocalDate(LocalDate.of(2000, 2, 30))); // 1900\n" +
                "\t\t} catch (Exception e) {\n" +
                "\t\t\tSystem.out.println(1900);\n" +
                "\t\t}\n" +
                "}', 'public static int getYearFromLocalDate(LocalDate date) {\n" +
                "}', 'Lấy năm từ hàm LocalDate cho trước. Bắt ngoại lệ ngày tháng, trả về năm 1900.');\n" +
                "INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (15, 'public static void main(String[] args) {\n" +
                "        System.out.println(compare_2_Strings(\"Hello\", \"hello\"));\n" +
                "        System.out.println(compare_2_Strings(\"Hello\", \"helllo\"));\n" +
                "}', 'public static int compare_2_Strings(String str1, String str2) {\n" +
                "}', 'Sử dụng hàm so sánh 2 chuỗi bỏ qua chữ thường chữ hoa.<br>\n" +
                "Ví dụ: Chuỗi “Hello” và “hello” kết quả ra 1, chuỗi “Hello” và “helllo” kết quả ra 0.');\n" +
                "INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (17, 'public static void main(String[] args) {\n" +
                "    System.out.println(permutation(10, 5));\n" +
                "    System.out.println(permutation(5, 10));\n" +
                "    System.out.println(permutation(-5, 2));\n" +
                "    System.out.println(permutation(-5, -1));\n" +
                "}', 'public static long permutation(int b, int a) {\n" +
                "}', 'Tính chỉnh hợp chập k của n.\n" +
                "Chỉnh hợp chập k của n: n!/(n-k)!\n" +
                "Trả về 0 nếu k > n, k hoặc n đều âm.');\n" +
                "INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (24, 'public static void main(String[] args) {\n" +
                "    System.out.println(hieuHaiSo(5,2));\n" +
                "    System.out.println(hieuHaiSo(2,3));\n" +
                "}', 'public static int hieuHaiSo(int a, int b) {\n" +
                "}', 'Tính hiệu');\n" +
                "INSERT INTO project_thesis_final.bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) VALUES (27, 'public static void main(String[] args) {\n" +
                "\tSystem.out.println(tich(1,2));\n" +
                "    System.out.println(tich(1,2));\n" +
                "}', 'public static int tich(int a, int b) {\n" +
                "}', 'tinh tích');\n";
    }

    public static String getStringSQLInsertTestCase() {
        return "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (331, '0 0', 'No rectangle', '0.0', 1);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (332, '0 23', 'No rectangle', '0.0', 1);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (333, '23 0', 'No rectangle', '0.0', 1);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (334, '1 -12', 'Negative numbers not allowed', '-1.0', 1);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (335, '-1 12', 'Negative numbers not allowed', '-1.0', 1);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (336, '4.5 15.5', 'Phải đặt biến số thực', '40.0', 1);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (337, '5 15', '', '40.0', 1);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (338, '0 0', 'No rectangle', '0.0', 2);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (339, '0 23', 'No rectangle', '0.0', 2);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (340, '23 0', 'No rectangle', '0.0', 2);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (341, '1 -12', 'Negative numbers not allowed', '-1.0', 2);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (342, '-1 12', 'Negative numbers not allowed', '-1.0', 2);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (343, '2.5 2.5', 'Phải đặt biến số thực', '6.25', 2);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (344, '2 2', '', '4.0', 2);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (415, '0', 'n can’t be 0', '-1', 3);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (416, '-2', 'n can’t be lower than 1', '-1', 3);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (417, '5', '', '9', 3);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (418, '6', '', '12', 3);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (419, '-2', '', '-1', 4);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (420, '2', 'Is Prime', '1', 4);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (421, '4', 'Is not Prime', '0', 4);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (424, '[64 34 25 12 22 11 90] 22', '', '1', 6);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (425, '[64 34 25 12 22 11 90] 21', '', '0', 6);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (426, '[]', '', '0', 6);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (427, '1 -3 2', 'Có 2 nghiệm phân biệt', '[2.0 1.0]', 8);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (428, '1 2 1', 'Có 1 nghiệm kép', '[-1.0]', 8);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (429, '1 0 -4', 'Có 2 nghiệm phân biệt', '[2.0 -2.0]', 8);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (430, '1 -2 5', 'Không có nghiệm', '[]', 8);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (431, '0 2 -4', 'Ko là pt bậc 2', '[0.0 0.0]', 8);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (432, '-2', 'Không âm', '-1', 7);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (433, '0', '', '0', 7);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (434, '1', '', '1', 7);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (435, '3', '', '2', 7);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (436, '4', '', '4', 7);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (437, '5', '', '7', 7);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (438, '2022 12 1', '', '1', 9);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (439, '2090 12 1', '', '0', 9);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (440, '1000 0 0', 'Ngoại lệ ngày tháng năm', '-1', 9);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (447, '[1 2 3 4 5]', '', '3.0', 13);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (448, '[1 2 2 4]', '', '2.0', 13);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (449, '[]', '', '0.0', 13);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (450, 'LocalDate.of(2000,2,2)', '', '2000', 14);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (451, 'LocalDate.of(2000,2,30)', '', '1900', 14);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (452, '5 2', null, '3', 24);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (453, '2 -3', null, '-1', 24);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (456, '10 5', '', '30240', 17);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (457, '5 10', '', '0', 17);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (458, '-5 2', '', '0', 17);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (459, '-6', '', '0', 17);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (460, '-7', '', '0', 17);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (463, 'Hello, hello', '', '1', 15);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (464, 'Hello helllo', '', '0', 15);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (465, '1', '', '1.0', 12);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (466, '2', '', '2.0', 12);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (467, '64 34 25 12 22 11 90', 'Pass', '[11, 12, 22, 25, 34, 64, 90]', 5);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (471, '1 2', null, '2', 27);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (472, '3 4', '', '12', 27);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (473, 'abcd@gmail.com', '', '1', 11);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (474, 'abcd@*()&.com', '', '0', 11);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (475, 'abcd@gmail.com.vn', '', '1', 11);\n" +
                "INSERT INTO project_thesis_final.bank_test_case_java_core (bank_test_case_id, input_test, note, output_expect, question_java_core_id) VALUES (476, 'abcd@gmail.com@.com', '', '0', 11);\n";
    }
}
