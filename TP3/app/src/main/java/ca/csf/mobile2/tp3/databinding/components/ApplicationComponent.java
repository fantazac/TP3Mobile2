package ca.csf.mobile2.tp3.databinding.components;

import javax.inject.Singleton;

import ca.csf.mobile2.tp3.database.ReminderRepository;
import ca.csf.mobile2.tp3.databinding.modules.ReminderDatabaseTableHelperModule;
import ca.csf.mobile2.tp3.databinding.modules.ReminderRepositoryModule;
import dagger.Component;

/**
 * Created by Alexandre on 2017-02-17.
 */
@Singleton
@Component(modules = {ReminderDatabaseTableHelperModule.class, ReminderRepositoryModule.class})
public interface ApplicationComponent {

    ReminderRepository reminderRepository();

}
