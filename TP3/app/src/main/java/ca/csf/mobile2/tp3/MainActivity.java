package ca.csf.mobile2.tp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TimePicker;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    protected CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void injectViews(@ViewById(R.id.calendarView) CalendarView calendarView){
        this.calendarView = calendarView;
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                daySelected(view);
            }
        });
    }

    public void daySelected(View view) {
        Intent dayReminders = new Intent(getApplicationContext(), DayRemindersActivity_.class);
        startActivity(dayReminders);
        finish();
    }
}
