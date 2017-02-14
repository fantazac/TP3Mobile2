package ca.csf.mobile2.tp3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReminderDatabaseTableHelper extends SQLiteOpenHelper
{
    public static final int VERSION = 1;

    public ReminderDatabaseTableHelper(Context context, String name) {
        super(context, name, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ReminderDatabaseTable.CREATE_TABLE_SQL);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ReminderDatabaseTable.DROP_TABLE_SQL);
        onCreate(db);
    }
}
