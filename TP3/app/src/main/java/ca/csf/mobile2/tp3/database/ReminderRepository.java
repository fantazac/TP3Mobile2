package ca.csf.mobile2.tp3.database;

import ca.csf.mobile2.tp3.model.Reminder;
import ca.csf.mobile2.tp3.model.ReminderList;

public interface ReminderRepository
{
    ReminderList retrieveAll();

    void create(Reminder user);

    void delete(Reminder user);
}
