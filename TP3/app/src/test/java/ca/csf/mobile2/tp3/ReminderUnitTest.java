package ca.csf.mobile2.tp3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ca.csf.mobile2.tp3.model.Reminder;

import static org.junit.Assert.*;

public class ReminderUnitTest
{
    private Reminder reminder1;
    private Reminder reminder2;
    private Reminder reminder3;

    @Before
    public void before()
    {
        reminder1 = new Reminder(0,"Reminder1", 0, 1487556898);
        reminder2 = new Reminder(1,"Reminder2", 1, 1487556900);
        reminder3 = new Reminder(2,"Reminder3", 2, 1487556902);
    }

    @Test
    public void testGetId()
    {
        assertEquals(0, reminder1.getId());
        assertEquals(1, reminder2.getId());
        assertEquals(2, reminder3.getId());
    }

    @Test
    public void testGetDescription()
    {
        assertEquals("Reminder1", reminder1.getDescription());
        assertEquals("Reminder2", reminder2.getDescription());
        assertEquals("Reminder3", reminder3.getDescription());
    }

    @Test
    public void testGetImportance()
    {
        assertEquals(0, reminder1.getImportance());
        assertEquals(1, reminder2.getImportance());
        assertEquals(2, reminder3.getImportance());
    }

    @Test
    public void testGetTime()
    {
        assertEquals(1487556898, reminder1.getUtcTime());
        assertEquals(1487556900, reminder2.getUtcTime());
        assertEquals(1487556902, reminder3.getUtcTime());
    }
}
