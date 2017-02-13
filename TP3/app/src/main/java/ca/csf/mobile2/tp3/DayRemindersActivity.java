package ca.csf.mobile2.tp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.Date;

@EActivity(R.layout.activity_day_selected)
public class DayRemindersActivity extends AppCompatActivity {

    protected TextView dateTextView;
    protected Date selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void injectViews(@ViewById(R.id.dateTextView) TextView dateTextView){
        this.dateTextView = dateTextView;
        selectedDate = new Date(getIntent().getLongExtra(MainActivity.SELECTED_DATE_UTC, -1));
        dateTextView.setText(getCurrentDay(selectedDate));
    }

    protected String getCurrentDay(Date date) {
        java.text.DateFormat dateFormat = DateFormat.getLongDateFormat(this);

        String restOfDate = dateFormat.format(date);
        int indexOfFirstLetterOfMonth = restOfDate.indexOf(' ') + 1;
        restOfDate = restOfDate.substring(0, indexOfFirstLetterOfMonth) +
                restOfDate.substring(indexOfFirstLetterOfMonth, indexOfFirstLetterOfMonth+1).toUpperCase() +
                restOfDate.substring(indexOfFirstLetterOfMonth+1);

        return getDayOfTheWeek(date) + ", " + restOfDate;
    }

    protected String getDayOfTheWeek(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        String dayOfTheWeek = simpleDateFormat.format(date);
        return dayOfTheWeek.substring(0, 1).toUpperCase() + dayOfTheWeek.substring(1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void createNewReminder(View view) {
        Intent createNewReminder = new Intent(getApplicationContext(), CreateNewReminderActivity_.class);
        createNewReminder.putExtra(MainActivity.SELECTED_DATE, dateTextView.getText());
        createNewReminder.putExtra(MainActivity.SELECTED_DATE_UTC, selectedDate.getTime());
        startActivity(createNewReminder);
    }
}