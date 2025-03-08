package equationSolver;

import java.time.DateTimeException;
import java.time.LocalDate;

public class LocalDateTest {
    public static void main(String[] args) {
        try{
            LocalDate day = LocalDate.of(1000,10,10);
            System.out.println(day);
            System.out.println(day.getYear());
        }catch(DateTimeException d)
        {
            System.out.println(d);
            System.out.println(0);
        }

    }
}
