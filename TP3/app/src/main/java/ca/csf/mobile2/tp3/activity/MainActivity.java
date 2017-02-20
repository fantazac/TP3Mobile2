package ca.csf.mobile2.tp3.activity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import ca.csf.mobile2.tp3.R;
import ca.csf.mobile2.tp3.database.ReminderRepository;
import ca.csf.mobile2.tp3.databinding.application.MaillesReminderApplication;
import ca.csf.mobile2.tp3.databinding.components.MainActivityComponent;
import ca.csf.mobile2.tp3.model.ReminderList;
import ca.csf.mobile2.tp3.service.NotifyService;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity{

    public static final int MILLIS_TO_SECONDS = 1000;
    public static final int SECONDS_IN_A_DAY = 86400000;
    public static final int REMINDER_CREATED = 420;
    public static final int BACK_TO_MAIN_ACTIVITY = 421;

    protected DatePicker datePicker;

    protected ReminderRepository reminderRepository;
    protected ReminderList reminderList;

    public static final String SELECTED_DATE_UTC = "UTC_DATE_FOR_REMINDER";
    public static final String SELECTED_DATE = "DATE_FOR_REMINDER";

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

    @Inject
    public void initializeDependencies(ReminderRepository reminderRepository){
        this.reminderRepository = reminderRepository;

        setupAlarmForEarliestReminder();
    }

    protected void setupAlarmForEarliestReminder(){
        reminderList = reminderRepository.retrieveAllOrderedByTime();

        if(!reminderList.isEmpty()){
            NotifyService.setupService(getApplicationContext(), reminderList);
        }
    }

    protected void injectViews(@ViewById(R.id.datePicker) DatePicker datePicker){
        this.datePicker = datePicker;

        datePicker.setMinDate(Calendar.getInstance().getTime().getTime());
        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), (view, year, monthOfYear, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
            onDaySelected(calendar.getTime().getTime() / MILLIS_TO_SECONDS * MILLIS_TO_SECONDS);
        });
    }

    public void onDaySelected(long utcTimeOfSelectedDate) {
        Intent dayReminders = new Intent(getApplicationContext(), DayRemindersActivity_.class);
        dayReminders.putExtra(SELECTED_DATE_UTC, utcTimeOfSelectedDate);
        startActivityForResult(dayReminders, BACK_TO_MAIN_ACTIVITY);
    }

    public void onShowReminderListButtonClicked(View view) {
        Intent reminderList = new Intent(getApplicationContext(), ReminderListActivity_.class);
        startActivityForResult(reminderList, BACK_TO_MAIN_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BACK_TO_MAIN_ACTIVITY) {
            setupAlarmForEarliestReminder();
        }
    }
}
