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

        reminderList.addReminderAddedListener(reminderAddedListener);
        reminderList.addReminderRemovedListener(reminderRemovedListener);

        return reminderList;
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
