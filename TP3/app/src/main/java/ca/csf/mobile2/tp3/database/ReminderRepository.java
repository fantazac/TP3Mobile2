package ca.csf.mobile2.tp3.database;

import ca.csf.mobile2.tp3.model.Reminder;
import ca.csf.mobile2.tp3.model.ReminderList;

public interface ReminderRepository
{
    ReminderList retrieveAll();

    ReminderList retrieveAllOrderedByTime();

    ReminderList retrieveAllOrderedByImportance();

    ReminderList retrieveRemindersForDay(long utcTime, long utcTimeNextDay);

    void create(Reminder user);

    void delete(Reminder user);
}
