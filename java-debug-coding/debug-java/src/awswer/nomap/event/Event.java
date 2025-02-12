package awswer.nomap.event;

import java.time.LocalDate;

public class Event {
    private String eventCode;
    private String eventName;
    private LocalDate eventDate;
    private String location;
    private int participantNumber;
    private double cost;

    @Override
    public String toString() {
        return "Event{" +
                "eventCode='" + eventCode + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventDate=" + eventDate +
                ", location='" + location + '\'' +
                ", participantNumber=" + participantNumber +
                ", cost=" + cost +
                '}';
    }

    public Event() {
        this.eventCode = null;
        this.eventName = null;
        this.eventDate = LocalDate.of(2026,2,1);
        this.location = null;
        this.participantNumber = 0;
        this.cost = 0;
    }

    // Constructor
    public Event(String eventCode, String eventName, LocalDate eventDate, String location, int participantNumber, double cost) {
        this.eventCode = eventCode;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
        this.participantNumber = participantNumber;
        this.cost = cost;
    }

    public boolean isValidEventCode(String eventCode) {
        if (eventCode.length() < 4 || eventCode.charAt(0) != 'E') {
            return false;
        }
        for (int i = 1; i < eventCode.length(); i++) {
            if (!Character.isDigit(eventCode.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Getters and Setters
    public String getEventCode() {
        if(isValidEventCode(eventCode))
            return eventCode;
        else
            return null;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getEventDate() {
        if (eventDate.isBefore(LocalDate.of(2026,2,1)))
            return LocalDate.of(2026,2,1);
        else
            return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getParticipantNumber() {
        return Math.max(participantNumber, 0);
    }

    public void setParticipantNumber(int participantNumber) {
        this.participantNumber = participantNumber;
    }

    public double getCost() {
        if(cost <=0)
            return 0;
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCostPerParticipant() {
        return ((cost + (cost * 10)/100) / participantNumber);
    }
}