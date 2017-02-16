package ca.csf.mobile2.tp3.database;

import ca.csf.mobile2.tp3.model.Reminder;
import ca.csf.mobile2.tp3.model.ReminderList;

public class ReminderRepositorySyncDecorator implements ReminderRepository {

    private final ReminderRepository reminderRepository;
    private final ReminderList.ReminderAddedListener reminderAddedListener;
    private final ReminderList.ReminderRemovedListener reminderRemovedListener;

    public ReminderRepositorySyncDecorator(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
        this.reminderAddedListener = reminderRepository::create;
        this.reminderRemovedListener = reminderRepository::delete;
    }

    @Override
    public ReminderList retrieveAll() {
        ReminderList reminderList = reminderRepository.retrieveAll();
        setListeners(reminderList);
        return reminderList;
    }

    @Override
    public ReminderList retrieveAllOrderedByTime() {
        ReminderList reminderList = reminderRepository.retrieveAllOrderedByTime();
        setListeners(reminderList);
        return reminderList;
    }

    @Override
    public ReminderList retrieveAllOrderedByImportance() {
        ReminderList reminderList = reminderRepository.retrieveAllOrderedByImportance();
        setListeners(reminderList);
        return reminderList;
    }

    @Override
    public ReminderList retrieveRemindersForDay(long utcTime, long utcTimeNextDay) {
        ReminderList reminderList = reminderRepository.retrieveRemindersForDay(utcTime, utcTimeNextDay);
        setListeners(reminderList);
        return reminderList;
    }

    private void setListeners(ReminderList reminderList){
        reminderList.addReminderAddedListener(reminderAddedListener);
        reminderList.addReminderRemovedListener(reminderRemovedListener);
    }

    @Override
    public void create(Reminder reminder) {
        reminderRepository.create(reminder);
    }

    @Override
    public void delete(Reminder reminder) {
        reminderRepository.delete(reminder);
    }
}
