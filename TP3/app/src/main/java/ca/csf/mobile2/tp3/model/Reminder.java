package ca.csf.mobile2.tp3.model;

public class Reminder
{
    private final int year;
    private final int month;
    private final int date;
    private final int day;

    private final int hour;
    private final int minute;

    //TODO: mettre en enum
    // 0 = peu important, 1 = important, 2 = très important
    private final int importance;
    private final String description;

    public Reminder(int year, int month, int date, int day, int hour, int minute, int importance, String description) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.importance = importance;
        this.description = description;
    }


    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getImportance() {
        return importance;
    }

    public String getDescription() {
        return description;
    }

    public interface ReminderListener {
        void onReminderChanged(Reminder eventSource);
    }
}
