

insert into bank_question_java_core (question_java_core_id, code_run_to_output, code_sample, content_question) values
(1, '', 'public double calculate_Perimeter_Of_Rectangle(double width, double height) {\n\n}\n', 'Viết hàm tính chu vi hình chữ nhật bằng Java.'),
(2, '', 'public double calculate_Area_Of_Rectangle(double width, double height) {\n\n}\n', 'Viết hàm tính diện tích hình chữ nhật bằng Java.'),
(3, '', 'public int calculate_Sum_Series_Of_Numbers(int n) {\n\n}\n', 'Viết chương trình in ra tổng 1+3+5 …. +n nếu n là số lẻ, 2+4+6+ …. n nếu n là số chẵn.'),
(4, '', 'public int isPrime(int n) {\n\n}\n', 'Kiểm tra n có phải số nguyên tố hay không.'),
(5, '', 'public int[] interchangeSort(int[] array) {\n\n}\n', 'Viết hàm sắp xếp mảng InterchangeSort.'),
(6, '', 'public int find_Element_Array(int[] array, int element_Find) {\n\n}\n', 'Tìm kiếm phần tử trong mảng.'),
(7, '', 'public int fibonacciSum(int n) {\n\n}\n', 'Tính tổng fibonacci.'),
(8, '', 'public double[] calculate_Quadratic_Education(double a, double b, double c) {\n\n}\n', 'Giải phương trình bậc 2 sử dụng mảng trả về tập nghiệm. (Nếu a = 0 sẽ không tính là phương trình bậc 2.)'),
(9, '', 'public int checkDateBeforeNow(int year, int month, int day) {\n\n}\n', 'Sử dụng LocalDate để kiểm tra ngày 1/12/2022 có trước ngày hiện tại hay không. Trả về -1 nếu có ngoại lệ về ngày tháng.'),
(10, '', 'public String getPatternLocalDate(int year, int month, int day) {\n\n}\n', 'Sử dụng DateTimeFormatter trả về định dạng chuỗi ngày tháng năm là dd/MM/yyyy.'),
(11, '', 'public int validateEmail(String email) {\n\n}\n', 'Sử dụng hàm kiểm tra biểu thức chính quy kiểm tra chuỗi đó tuân thủ theo cú pháp email hay không. Cho cú pháp biểu thức chính quy: "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"'),
(12, '', 'public double getCovertedDoubleFromInt(int n) {\n\n}\n', 'Sử dụng hàm chuyển đổi kiểu dữ liệu từ int thành double.'),
(13, '', 'public double getMedian(double[] array) {\n\n}\n', 'Tìm số trung vị trong mảng. Điều kiện để tính số trung vị phải sắp xếp mảng tăng dần. Số trung vị là:\n- Nếu tổng phần tử trong mảng là lẻ, số trung vị là phần tử ở giữa trong mảng.\n- Nếu tổng phần tử trong mảng là chẵn, số trung vị là trung bình của ((tổng phần tử mảng/2 +1) + (tổng phần tử mảng/2 -1))/2.\nTrả về 0.0 nếu mảng rỗng.'),
(14, '', 'public int getYearFromLocalDate(LocalDate date) {\n\n}\n', 'Lấy năm từ hàm LocalDate cho trước. Bắt ngoại lệ ngày tháng, trả về năm 1900.'),
(15, '', 'public int compare_2_Strings(String str1, String str2) {\n\n}\n', 'Sử dụng hàm so sánh 2 chuỗi bỏ qua chữ thường chữ hoa.\nVí dụ: Chuỗi “Hello” và “hello” kết quả ra 1, chuỗi “Hello” và “helllo” kết quả ra 0.'),
(16, '', 'public String getMonthFromNumber(int numberOfMonth) {\n\n}\n', 'Dùng Switch-case chuyển đổi số thành tên tháng.\nTrả về chuỗi "Invalid month" nếu tháng không từ 1 đến 12.\n1 -> January, 13 -> Invalid month.'),
(17, '', 'public static long permutation(int b, int a) {\n\n}\n', 'Tính chỉnh hợp chập k của n.\nChỉnh hợp chập k của n: n!/(n-k)!\nTrả về 0 nếu k > n, k hoặc n đều âm.');