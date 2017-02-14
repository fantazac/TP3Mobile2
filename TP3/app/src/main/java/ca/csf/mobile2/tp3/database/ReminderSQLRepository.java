package ca.csf.mobile2.tp3.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ca.csf.mobile2.tp3.model.Reminder;

public class ReminderSQLRepository implements ReminderRepository
{
    private SQLiteDatabase database;

    public ReminderSQLRepository(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public void create(Reminder reminder){
        Cursor cursor = null;

        try {
            database.beginTransaction();

            cursor = database.rawQuery(ReminderDatabaseTable.INSERT_SQL, new String[]{
                    reminder.getDescription()
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
    public void delete(Reminder user){
        Cursor cursor = null;

        try {
            database.beginTransaction();

            cursor = database.rawQuery(ReminderDatabaseTable.DELETE_SQL, new String[]{
                    String.valueOf(user.getId())
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
