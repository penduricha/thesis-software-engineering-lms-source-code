package awswer.nomap.event;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;


public class TestEvent {
    public static void main(String[] args) {
        Random random = new Random();
        //yêu cầu class đơn 6 thuộc tính
        //Event(String eventCode, String eventName, LocalDate eventDate, String location, int participantNumber, double cost)
        //Câu 1
        Event event1 = new Event("E1234",
                "IUH Coder",
                LocalDate.of(2026,3,2),
                "Hcm",
                100,
                100.5
        );
        System.out.println(event1);

        //Câu 2
        Event event2 = new Event();
        System.out.println(event2);

        //Câu 3
        System.out.println(event1.getEventCode());

        //Câu 4
        System.out.println(event1.getEventName());

        //Câu 5
        System.out.println(event1.getLocation());

        //Câu 6
        System.out.println(event1.getCost());

        //Câu 7
        System.out.println(event1.getParticipantNumber());

        //Câu 8
        System.out.println(event1.getEventDate());

        //Câu 9
        Event event9 = new Event("E1234",
                "Squid",
                LocalDate.of(2026,1,20),
                "Hcm",
                100,
                20000
        );
        System.out.println(event9.getCostPerParticipant());

        //Câu 10
        Event event10 = new Event(
                "123e",
                "Squid",
                LocalDate.of(2026,3,2),
                "Hcm",
                100,
                100.5
        );
        System.out.println(event10.getEventCode());

        //Câu 11
        Event event11 = new Event("E1234",
                "Squid",
                LocalDate.of(2026,3,2),
                "Hcm",
                100,
                random.nextDouble() * -1
        );
        System.out.println(event11.getCost());

        //Câu 12
        Event event12 = new Event("E1234",
                "Squid",
                LocalDate.of(2026,3,2),
                "Hcm",
                random.nextInt(1),
                100.25
        );
        System.out.println(event12.getParticipantNumber());

        //Câu 13
        LocalDate endDate = LocalDate.of(2026, 2, 1);
        LocalDate startDate = endDate.minusDays(1);
        // Ngày 31 tháng 1 năm 2026
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        long randomDays = random.nextInt((int) daysBetween);
        LocalDate randomDate = startDate.plusDays(randomDays);
        Event event13 = new Event("E1234",
                "Squid",
                randomDate,
                "Hcm",
                100,
                10
        );
        System.out.println(event13.getEventDate());


    }
}
