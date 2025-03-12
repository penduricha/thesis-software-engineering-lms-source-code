package awswer.input;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String str = "ben10 ben";
        List<Object> resultList = new ArrayList<>();

        // Tách chuỗi thành mảng
        String[] parts = str.trim().split(" ");

        for (String part : parts) {
            if (isInteger(part)) {
                resultList.add(Integer.parseInt(part));
            } else if (isDouble(part)) {
                resultList.add(Double.parseDouble(part));
            } else if (isString(part)) {
                resultList.add(part); // Thêm phần tử vào danh sách như chuỗi
            }
        }

        // In kết quả
        System.out.println(resultList);
    }

    // Kiểm tra xem chuỗi có phải là số nguyên không
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Kiểm tra xem chuỗi có phải là số thực không
    private static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Kiểm tra xem chuỗi có phải là chuỗi không (không phải số)
    private static boolean isString(String str) {
        return !isInteger(str) && !isDouble(str);
    }
}
