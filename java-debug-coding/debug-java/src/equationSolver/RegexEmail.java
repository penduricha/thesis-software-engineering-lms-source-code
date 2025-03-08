package equationSolver;

import java.util.regex.Pattern;

public class RegexEmail {
    public static void main(String[] args) {
        String email = "exam123ple@example.com.8&^*&vn";
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        boolean isValid = Pattern.matches(emailRegex, email);
        System.out.println("Email hợp lệ: " + isValid);
        //System.out.println(sumAB(1,2));
    }
}

