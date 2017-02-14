package ca.csf.mobile2.tp3.database;

import ca.csf.mobile2.tp3.model.Reminder;

public interface ReminderRepository
{
    //Group retrieveAll();

    void create(Reminder user);

    void delete(Reminder user);
}
