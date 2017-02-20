package ca.csf.mobile2.tp3.databinding.application;

import android.app.Application;

import ca.csf.mobile2.tp3.databinding.components.ApplicationComponent;
import ca.csf.mobile2.tp3.databinding.components.CreateNewReminderActivityComponent;
import ca.csf.mobile2.tp3.databinding.components.DaggerApplicationComponent;
import ca.csf.mobile2.tp3.databinding.components.DaggerCreateNewReminderActivityComponent;
import ca.csf.mobile2.tp3.databinding.components.DaggerDayRemindersActivityComponent;
import ca.csf.mobile2.tp3.databinding.components.DaggerMainActivityComponent;
import ca.csf.mobile2.tp3.databinding.components.DaggerReminderListActivityComponent;
import ca.csf.mobile2.tp3.databinding.components.DayRemindersActivityComponent;
import ca.csf.mobile2.tp3.databinding.components.MainActivityComponent;
import ca.csf.mobile2.tp3.databinding.components.ReminderListActivityComponent;
import ca.csf.mobile2.tp3.databinding.modules.ReminderDatabaseTableHelperModule;

public class MaillesReminderApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = createApplicationComponent();
    }

    private ApplicationComponent createApplicationComponent(){
        return DaggerApplicationComponent.builder().reminderDatabaseTableHelperModule(new ReminderDatabaseTableHelperModule(this)).build();
    }

    public MainActivityComponent getMainActivityComponent(){
        return DaggerMainActivityComponent.builder().applicationComponent(applicationComponent).build();
    }

    public DayRemindersActivityComponent getDayRemindersActivityComponent(){
        return DaggerDayRemindersActivityComponent.builder().applicationComponent(applicationComponent).build();
    }

    public CreateNewReminderActivityComponent getCreateNewReminderActivityComponent(){
        return DaggerCreateNewReminderActivityComponent.builder().applicationComponent(applicationComponent).build();
    }

    public ReminderListActivityComponent getReminderListActivityComponent(){
        return DaggerReminderListActivityComponent.builder().applicationComponent(applicationComponent).build();
    }
}
