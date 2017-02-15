package ca.csf.mobile2.tp3.activity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import ca.csf.mobile2.tp3.R;
import ca.csf.mobile2.tp3.database.ReminderDatabaseTableHelper;
import ca.csf.mobile2.tp3.database.ReminderRepository;
import ca.csf.mobile2.tp3.database.ReminderRepositorySyncDecorator;
import ca.csf.mobile2.tp3.database.ReminderSQLRepository;
import ca.csf.mobile2.tp3.model.ReminderList;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    protected CalendarView calendarView;

    public static final String DATABASE_FILE_NAME = "reminders.db";

    public static final String SELECTED_DATE_UTC = "UTC_DATE_FOR_REMINDER";
    public static final String SELECTED_DATE = "DATE_FOR_REMINDER";

    private ReminderDatabaseTableHelper reminderDatabaseTableHelper;
    private ReminderRepository reminderRepository;
    private ReminderList reminderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void injectViews(@ViewById(R.id.calendarView) CalendarView calendarView){
        this.calendarView = calendarView;
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            daySelected(calendar.getTime().getTime());
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        reminderDatabaseTableHelper = new ReminderDatabaseTableHelper(this, DATABASE_FILE_NAME);
        reminderRepository = new ReminderRepositorySyncDecorator(new ReminderSQLRepository(reminderDatabaseTableHelper.getWritableDatabase()));
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
