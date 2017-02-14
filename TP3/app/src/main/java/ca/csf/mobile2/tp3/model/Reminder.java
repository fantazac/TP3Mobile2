package ca.csf.mobile2.tp3.model;

import java.util.LinkedList;
import java.util.List;

public class Reminder {

    private int id;
    private final long utcTime;
    //TODO: mettre en enum
    // 0 = peu important, 1 = important, 2 = très important
    private final int importance;
    private final String description;

    private List<ReminderChangedListener> reminderChangedListeners;

    public Reminder(int id, String description, int importance, long utcTime) {
        this.id = id;
        this.utcTime = utcTime;
        this.importance = importance;
        this.description = description;

        reminderChangedListeners = new LinkedList<>();
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

    public void addReminderChangedListener(ReminderChangedListener listener) {
        reminderChangedListeners.add(listener);
    }

    public void removeReminderChangedListener(ReminderChangedListener listener) {
        reminderChangedListeners.remove(listener);
    }

    private void notifyReminderChanged() {
        for (ReminderChangedListener listener : reminderChangedListeners) {
            listener.onReminderChanged(this);
        }
    }

    public interface ReminderChangedListener {
        void onReminderChanged(Reminder reminder);
    }
}
