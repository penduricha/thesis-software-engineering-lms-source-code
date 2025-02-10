package my.module;



import java.util.ArrayList;
import java.time.LocalDate;


public class YourMainClass {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Hello, World!");

        LocalDate today = LocalDate.now();

        System.out.println(list);
        System.out.println("Today's date: " + today);
    }
}