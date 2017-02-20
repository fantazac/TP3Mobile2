package ca.csf.mobile2.tp3.model;

public class Reminder {

    private int id;
    private final long utcTime;
    //TODO: mettre en enum
    // 0 = peu important, 1 = important, 2 = très important
    private final int importance;
    private final String description;

    public Reminder(int id, String description, int importance, long utcTime) {
        this.id = id;
        this.utcTime = utcTime;
        this.importance = importance;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
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
}
