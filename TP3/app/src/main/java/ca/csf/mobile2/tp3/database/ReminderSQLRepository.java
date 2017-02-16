package ca.csf.mobile2.tp3.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ca.csf.mobile2.tp3.model.Reminder;
import ca.csf.mobile2.tp3.model.ReminderList;

public class ReminderSQLRepository implements ReminderRepository
{
    private SQLiteDatabase database;

    public ReminderSQLRepository(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public ReminderList retrieveAll() {
        return getReminderListFromDatabase(ReminderDatabaseTable.SELECT_ALL_SQL, new String[]{});
    }

    @Override
    public ReminderList retrieveAllOrderedByTime() {
        return getReminderListFromDatabase(ReminderDatabaseTable.SELECT_ALL_SQL_BY_TIME, new String[]{});
    }

    @Override
    public ReminderList retrieveAllOrderedByImportance() {
        return getReminderListFromDatabase(ReminderDatabaseTable.SELECT_ALL_SQL_BY_IMPORTANCE, new String[]{});
    }

    @Override
    public ReminderList retrieveRemindersForDay(long utcTime, long utcTimeNextDay) {
        return getReminderListFromDatabase(ReminderDatabaseTable.SELECT_SQL_FROM_UTC_TIME, new String[]{String.valueOf(utcTime), String.valueOf(utcTimeNextDay)});
    }

    private ReminderList getReminderListFromDatabase(String request, String[] parametersForSelect){
        Cursor cursor = null;

        try {
            database.beginTransaction();

            cursor = database.rawQuery(request, parametersForSelect);
            ReminderList reminderList = new ReminderList();
            while (cursor.moveToNext()) {
                Reminder reminder = new Reminder(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)), Long.parseLong(cursor.getString(3)));
                reminderList.add(reminder);
            }

            database.setTransactionSuccessful();
            return reminderList;

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            database.endTransaction();
        }
    }

    @Override
    public void create(Reminder reminder){
        Cursor cursor = null;

        try {
            database.beginTransaction();

            cursor = database.rawQuery(ReminderDatabaseTable.INSERT_SQL, new String[]{
                    reminder.getDescription(), String.valueOf(reminder.getImportance()), String.valueOf(reminder.getUtcTime())
            });

            cursor.moveToLast();

            cursor.close();

            cursor = database.rawQuery("SELECT last_insert_rowid()",new String[]{});
            cursor.moveToNext();
            reminder.setId(cursor.getInt(0));

            database.setTransactionSuccessful();

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            database.endTransaction();
        }
    }

    @Override
    public void delete(Reminder reminder){
        Cursor cursor = null;

        try {
            database.beginTransaction();

            cursor = database.rawQuery(ReminderDatabaseTable.DELETE_SQL, new String[]{
                    String.valueOf(reminder.getId())
            });

            cursor.moveToLast();

            database.setTransactionSuccessful();

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            database.endTransaction();
        }
    }
}
