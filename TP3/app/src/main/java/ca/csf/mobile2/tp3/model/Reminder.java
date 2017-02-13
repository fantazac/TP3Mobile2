package ca.csf.mobile2.tp3.model;

public class Reminder
{
    private long utcTime;
    //TODO: mettre en enum
    // 0 = peu important, 1 = important, 2 = très important
    private final int importance;
    private final String description;

    public Reminder(long utcTime, int importance, String description) {
        this.utcTime = utcTime;
        this.importance = importance;
        this.description = description;
    }


    public long getUtcTime() {
        return utcTime;
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
