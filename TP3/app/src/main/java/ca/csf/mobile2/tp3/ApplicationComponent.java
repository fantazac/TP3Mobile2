package ca.csf.mobile2.tp3;

import android.support.test.espresso.core.deps.dagger.Component;

import javax.inject.Singleton;

import ca.csf.mobile2.tp3.database.ReminderDatabaseTableHelper;
import ca.csf.mobile2.tp3.database.ReminderRepository;

/**
 * Created by Alexandre on 2017-02-17.
 */
@Singleton
@Component(modules = {ReminderDatabaseTableHelperModule.class, ReminderRepositoryModule.class})
public interface ApplicationComponent {

    ReminderDatabaseTableHelper reminderDatabaseTableHelper();
    ReminderRepository reminderRepository();

}
