package ca.csf.mobile2.tp3.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ReminderList implements Iterable<Reminder> {

    private final List<Reminder> reminders;
    private final List<ReminderAddedListener> reminderAddedListeners;
    private final List<ReminderRemovedListener> reminderRemovedListeners;

    public ReminderList() {
        reminders = new LinkedList<>();
        reminderAddedListeners = new LinkedList<>();
        reminderRemovedListeners = new LinkedList<>();
    }

    public void add(Reminder reminder) {
        reminders.add(reminder);
        notifyReminderAdded(reminder);
    }

    public void remove(Reminder reminder) {
        reminders.remove(reminder);
        notifyReminderRemoved(reminder);
    }

    //For unit tests only
    public int getReminderListCount()
    {
        return reminders.size();
    }

    //For unit tests only
    public int getReminderAddedListenersCount()
    {
        return reminderAddedListeners.size();
    }

    //For unit tests only
    public int getReminderRemovedListenersCount()
    {
        return reminderRemovedListeners.size();
    }

    public void addReminderAddedListener(ReminderAddedListener listener) {
        reminderAddedListeners.add(listener);
    }

    public void removeReminderAddedListener(ReminderAddedListener listener) {
        reminderAddedListeners.remove(listener);
    }

    public void addReminderRemovedListener(ReminderRemovedListener listener) {
        reminderRemovedListeners.add(listener);
    }

    public void removeReminderRemovedListener(ReminderRemovedListener listener) {
        reminderRemovedListeners.remove(listener);
    }

    private void notifyReminderAdded(Reminder reminder) {
        for (ReminderAddedListener listener : reminderAddedListeners) {
            listener.onReminderAdded(reminder);
        }
    }

    private void notifyReminderRemoved(Reminder reminder) {
        for (ReminderRemovedListener listener : reminderRemovedListeners) {
            listener.onReminderRemoved(reminder);
        }
    }

    @Override
    public Iterator<Reminder> iterator() {
        return reminders.iterator();
    }

    public interface ReminderAddedListener {
        void onReminderAdded(Reminder reminder);
    }

    public interface ReminderRemovedListener {
        void onReminderRemoved(Reminder user);
    }

}