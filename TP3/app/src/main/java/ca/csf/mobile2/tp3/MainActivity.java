package ca.csf.mobile2.tp3;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    protected CalendarView calendarView;

    public static final String SELECTED_DATE_UTC = "UTC_DATE_FOR_REMINDER";
    public static final String SELECTED_DATE = "DATE_FOR_REMINDER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void injectViews(@ViewById(R.id.calendarView) CalendarView calendarView){
        this.calendarView = calendarView;
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                daySelected(calendar.getTime().getTime());
            }
        });
    }

    public void daySelected(long utcTimeOfSelectedDate) {
        Intent dayReminders = new Intent(getApplicationContext(), DayRemindersActivity_.class);
        dayReminders.putExtra(SELECTED_DATE_UTC, utcTimeOfSelectedDate);
        startActivity(dayReminders);
    }
}
