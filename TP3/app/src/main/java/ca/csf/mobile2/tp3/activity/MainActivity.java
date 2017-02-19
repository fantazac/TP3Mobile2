package ca.csf.mobile2.tp3.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


import javax.inject.Inject;

import ca.csf.mobile2.tp3.databinding.application.MaillesReminderApplication;
import ca.csf.mobile2.tp3.databinding.components.MainActivityComponent;
import ca.csf.mobile2.tp3.R;
import ca.csf.mobile2.tp3.database.ReminderRepository;
import ca.csf.mobile2.tp3.model.ReminderList;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private final int MILLIS_TO_SECONDS = 1000;

    protected DatePicker datePicker;

    public static final String SELECTED_DATE_UTC = "UTC_DATE_FOR_REMINDER";
    public static final String SELECTED_DATE = "DATE_FOR_REMINDER";

    private ReminderList reminderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivityComponent mainActivityComponent = getMainActivityComponent();
        mainActivityComponent.inject(this);
    }

    private MainActivityComponent getMainActivityComponent(){
        return getMaillesReminderApplication().getMainActivityComponent();
    }

    private MaillesReminderApplication getMaillesReminderApplication(){
        return (MaillesReminderApplication) getApplication();
    }

    protected void injectViews(@ViewById(R.id.datePicker) DatePicker datePicker){
        this.datePicker = datePicker;

        datePicker.setMinDate(Calendar.getInstance().getTime().getTime());
        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), (view, year, monthOfYear, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
            daySelected(calendar.getTime().getTime() / MILLIS_TO_SECONDS * MILLIS_TO_SECONDS);
        });
    }

    @Inject
    public void initializeDependencies(ReminderRepository reminderRepository){
        reminderList = reminderRepository.retrieveAll();
    }

    public void daySelected(long utcTimeOfSelectedDate) {
        Intent dayReminders = new Intent(getApplicationContext(), DayRemindersActivity_.class);
        dayReminders.putExtra(SELECTED_DATE_UTC, utcTimeOfSelectedDate);
        startActivity(dayReminders);
    }

    public void onClickShowReminderList(View view) {
        Intent reminderList = new Intent(getApplicationContext(), ReminderListActivity_.class);
        startActivity(reminderList);
    }
}
