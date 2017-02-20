package ca.csf.mobile2.tp3.databinding.modules;

import javax.inject.Singleton;

import ca.csf.mobile2.tp3.database.ReminderDatabaseTableHelper;
import ca.csf.mobile2.tp3.database.ReminderRepository;
import ca.csf.mobile2.tp3.database.ReminderRepositorySyncDecorator;
import ca.csf.mobile2.tp3.database.ReminderSQLRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class ReminderRepositoryModule {

    @Provides
    @Singleton
    public ReminderRepository provideReminderRepository(ReminderDatabaseTableHelper reminderDatabaseTableHelper){
        return new ReminderRepositorySyncDecorator(new ReminderSQLRepository(reminderDatabaseTableHelper.getWritableDatabase()));
    }

}