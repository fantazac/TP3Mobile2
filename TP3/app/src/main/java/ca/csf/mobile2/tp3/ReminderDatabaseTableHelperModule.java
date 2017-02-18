package ca.csf.mobile2.tp3;

import android.content.Context;

import javax.inject.Singleton;

import ca.csf.mobile2.tp3.database.ReminderDatabaseTableHelper;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandre on 2017-02-17.
 */
@Module
public class ReminderDatabaseTableHelperModule {

    public static final String DATABASE_FILE_NAME = "reminders.db";
    private Context context;

    public ReminderDatabaseTableHelperModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    public ReminderDatabaseTableHelper provideReminderDatabaseTableHelper(){
        return new ReminderDatabaseTableHelper(context, DATABASE_FILE_NAME);
    }

}
