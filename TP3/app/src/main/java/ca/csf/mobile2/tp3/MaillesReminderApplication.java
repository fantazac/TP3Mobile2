package ca.csf.mobile2.tp3;

import android.app.Application;

/**
 * Created by Alexandre on 2017-02-17.
 */

public class MaillesReminderApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = createApplicationComponent();
    }

    private ApplicationComponent createApplicationComponent(){
        return null;
    }

    public MainActivityComponent getMainActivityComponent(){
        return null;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
