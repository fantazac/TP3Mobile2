package ca.csf.mobile2.tp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_day_selected)
public class DayRemindersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void createNewReminder(View view) {
        Intent createNewReminder = new Intent(getApplicationContext(), CreateNewReminderActivity_.class);
        startActivity(createNewReminder);
        finish();
    }

    public void daySelectedBack(View view) {
        Intent main = new Intent(getApplicationContext(), MainActivity_.class);
        startActivity(main);
        finish();
    }
}