package debug_java;

public class GetCodeJava {
    public String getCodeClass_Simple() {
        return "import java.time.LocalDate;\n" +
                "\n" +
                "public class Event {\n" +
                "    private String eventCode;\n" +
                "    private String eventName;\n" +
                "    private LocalDate eventDate;\n" +
                "    private String location;\n" +
                "    private int participantNumber;\n" +
                "    private double cost;\n" +
                "\n" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        return \"Event{\" +\n" +
                "                \"eventCode='\" + eventCode + '\\'' +\n" +
                "                \", eventName='\" + eventName + '\\'' +\n" +
                "                \", eventDate=\" + eventDate +\n" +
                "                \", location='\" + location + '\\'' +\n" +
                "                \", participantNumber=\" + participantNumber +\n" +
                "                \", cost=\" + cost +\n" +
                "                '}';\n" +
                "    }\n" +
                "\n" +
                "    public Event() {\n" +
                "        this.eventCode = null;\n" +
                "        this.eventName = null;\n" +
                "        this.eventDate = LocalDate.of(2026,2,1);\n" +
                "        this.location = null;\n" +
                "        this.participantNumber = 0;\n" +
                "        this.cost = 0;\n" +
                "    }\n" +
                "\n" +
                "    // Constructor\n" +
                "    public Event(String eventCode, String eventName, LocalDate eventDate, String location, int participantNumber, double cost) {\n" +
                "        this.eventCode = eventCode;\n" +
                "        this.eventName = eventName;\n" +
                "        this.eventDate = eventDate;\n" +
                "        this.location = location;\n" +
                "        this.participantNumber = participantNumber;\n" +
                "        this.cost = cost;\n" +
                "    }\n" +
                "\n" +
                "    public boolean isValidEventCode(String eventCode) {\n" +
                "        if (eventCode.length() < 4 || eventCode.charAt(0) != 'E') {\n" +
                "            return false;\n" +
                "        }\n" +
                "        for (int i = 1; i < eventCode.length(); i++) {\n" +
                "            if (!Character.isDigit(eventCode.charAt(i))) {\n" +
                "                return false;\n" +
                "            }\n" +
                "        }\n" +
                "        return true;\n" +
                "    }\n" +
                "\n" +
                "    // Getters and Setters\n" +
                "    public String getEventCode() {\n" +
                "        if(isValidEventCode(eventCode))\n" +
                "            return eventCode;\n" +
                "        else\n" +
                "            return null;\n" +
                "    }\n" +
                "\n" +
                "    public void setEventCode(String eventCode) {\n" +
                "        this.eventCode = eventCode;\n" +
                "    }\n" +
                "\n" +
                "    public String getEventName() {\n" +
                "        return eventName;\n" +
                "    }\n" +
                "\n" +
                "    public void setEventName(String eventName) {\n" +
                "        this.eventName = eventName;\n" +
                "    }\n" +
                "\n" +
                "    public LocalDate getEventDate() {\n" +
                "        if (eventDate.isBefore(LocalDate.of(2026,2,1)))\n" +
                "            return LocalDate.of(2026,2,1);\n" +
                "        else\n" +
                "            return eventDate;\n" +
                "    }\n" +
                "\n" +
                "    public void setEventDate(LocalDate eventDate) {\n" +
                "        this.eventDate = eventDate;\n" +
                "    }\n" +
                "\n" +
                "    public String getLocation() {\n" +
                "        return location;\n" +
                "    }\n" +
                "\n" +
                "    public void setLocation(String location) {\n" +
                "        this.location = location;\n" +
                "    }\n" +
                "\n" +
                "    public int getParticipantNumber() {\n" +
                "        return Math.max(participantNumber, 0);\n" +
                "    }\n" +
                "\n" +
                "    public void setParticipantNumber(int participantNumber) {\n" +
                "        this.participantNumber = participantNumber;\n" +
                "    }\n" +
                "\n" +
                "    public double getCost() {\n" +
                "        if(cost <=0)\n" +
                "            return 0;\n" +
                "        return cost;\n" +
                "    }\n" +
                "\n" +
                "    public void setCost(double cost) {\n" +
                "        this.cost = cost;\n" +
                "    }\n" +
                "\n" +
                "    public double getCostPerParticipant() {\n" +
                "        return ((cost + (cost * 10)/100) / participantNumber);\n" +
                "    }\n" +
                "}";
    }

    public String getCode_Main() {
        return "import java.time.LocalDate;\n" +
                "import java.util.Random;\n" +
                "\n" +
                "public class TestEvent {\n" +
                "    public static void main(String[] args) {\n" +
                "        Random random = new Random();\n" +
                "        //yêu cầu class đơn 6 thuộc tính\n" +
                "        //Event(String eventCode, String eventName, LocalDate eventDate, String location, int participantNumber, double cost)\n" +
                "        //Câu 1\n" +
                "        Event event1 = new Event(\"E1234\",\n" +
                "                \"IUH Coder\",\n" +
                "                LocalDate.of(2026,3,2),\n" +
                "                \"Hcm\",\n" +
                "                100,\n" +
                "                100.5\n" +
                "        );\n" +
                "        System.out.println(event1);\n" +
                "\n" +
                "        //Câu 2\n" +
                "        Event event2 = new Event();\n" +
                "        System.out.println(event2);\n" +
                "\n" +
                "        //Câu 3\n" +
                "        System.out.println(event1.getEventCode());\n" +
                "\n" +
                "        //Câu 4\n" +
                "        System.out.println(event1.getEventName());\n" +
                "\n" +
                "        //Câu 5\n" +
                "        System.out.println(event1.getLocation());\n" +
                "\n" +
                "        //Câu 6\n" +
                "        System.out.println(event1.getCost());\n" +
                "\n" +
                "        //Câu 7\n" +
                "        System.out.println(event1.getParticipantNumber());\n" +
                "\n" +
                "        //Câu 8\n" +
                "        System.out.println(event1.getEventDate());\n" +
                "\n" +
                "        //Câu 9\n" +
                "        Event event9 = new Event(\"E1234\",\n" +
                "                \"Squid\",\n" +
                "                LocalDate.of(2026,1,20),\n" +
                "                \"Hcm\",\n" +
                "                100,\n" +
                "                20000\n" +
                "        );\n" +
                "        System.out.println(event9.getCostPerParticipant());\n" +
                "\n" +
                "        //Câu 10\n" +
                "        Event event10 = new Event(\n" +
                "                \"123e\",\n" +
                "                \"Squid\",\n" +
                "                LocalDate.of(2026,3,2),\n" +
                "                \"Hcm\",\n" +
                "                100,\n" +
                "                100.5\n" +
                "        );\n" +
                "        System.out.println(event10.getEventCode());\n" +
                "\n" +
                "        //Câu 11\n" +
                "        Event event11 = new Event(\"E1234\",\n" +
                "                \"Squid\",\n" +
                "                LocalDate.of(2026,3,2),\n" +
                "                \"Hcm\",\n" +
                "                100,\n" +
                "                random.nextDouble() * -1\n" +
                "        );\n" +
                "        System.out.println(event11.getCost());\n" +
                "\n" +
                "        //Câu 12\n" +
                "        Event event12 = new Event(\"E1234\",\n" +
                "                \"Squid\",\n" +
                "                LocalDate.of(2026,3,2),\n" +
                "                \"Hcm\",\n" +
                "                random.nextInt(1),\n" +
                "                100.25\n" +
                "        );\n" +
                "        System.out.println(event12.getParticipantNumber());\n" +
                "\n" +
                "        //Câu 13\n" +
                "        Event event13 = new Event(\"E1234\",\n" +
                "                \"Squid\",\n" +
                "                LocalDate.of(2026,1,20),\n" +
                "                \"Hcm\",\n" +
                "                100,\n" +
                "                10\n" +
                "        );\n" +
                "        System.out.println(event13.getEventDate());\n" +
                "\n" +
                "\n" +
                "    }\n" +
                "}";
    }
}
