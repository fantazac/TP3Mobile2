package ca.csf.mobile2.tp3.model;

public class Reminder
{

    private long id;
    private long utcTime;
    //TODO: mettre en enum
    // 0 = peu important, 1 = important, 2 = très important
    private final int importance;
    private final String description;

    public Reminder(long id, long utcTime, int importance, String description) {
        this.id = id;
        this.utcTime = utcTime;
        this.importance = importance;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
