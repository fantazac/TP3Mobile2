package ca.csf.mobile2.tp3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ca.csf.mobile2.tp3.model.Reminder;
import ca.csf.mobile2.tp3.model.ReminderList;
import ca.csf.mobile2.tp3.viewmodel.ReminderListViewModel;

import static org.junit.Assert.*;


public class ReminderListUnitTest
{
    private Reminder reminder1;
    private Reminder reminder2;
    private Reminder reminder3;

    private ReminderList reminderList;

    @Before
    public void before()
    {
        reminder1 = new Reminder(0,"Reminder1", 0, 1487556898);
        reminder2 = new Reminder(1,"Reminder2", 1, 1487556900);
        reminder3 = new Reminder(2,"Reminder3", 2, 1487556902);

        reminderList = new ReminderList();


    }

    @Test
    public void AddReminder()
    {
        reminderList.add(reminder1);
        reminderList.add(reminder2);

        assertEquals(2, reminderList.getReminderListCount());
    }

    @Test
    public void RemoveReminder()
    {
        reminderList.add(reminder1);
        reminderList.add(reminder2);

        reminderList.remove(reminder1);
        reminderList.remove(reminder2);

        assertEquals(0, reminderList.getReminderListCount());
    }

    @Test
    public void AddReminderAddedListener()
    {
        reminderList.addReminderAddedListener(new ReminderList.ReminderAddedListener() {
            @Override
            public void onReminderAdded(Reminder reminder) {

            }
        });

        assertEquals(1, reminderList.getReminderAddedListenersCount());
    }

    @Test
    public void AddReminderRemovedListener()
    {
        reminderList.addReminderRemovedListener(new ReminderList.ReminderRemovedListener() {
            @Override
            public void onReminderRemoved(Reminder user) {

            }
        });

        assertEquals(1, reminderList.getReminderRemovedListenersCount());
    }
}
