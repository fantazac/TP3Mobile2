package ca.csf.mobile2.tp3.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Date;

import javax.inject.Inject;

import ca.csf.mobile2.tp3.databinding.components.CreateNewReminderActivityComponent;
import ca.csf.mobile2.tp3.databinding.application.MaillesReminderApplication;
import ca.csf.mobile2.tp3.R;
<<<<<<< HEAD
import ca.csf.mobile2.tp3.Service.NotifyService;
import ca.csf.mobile2.tp3.database.ReminderDatabaseTableHelper;
=======
>>>>>>> 08bd9ba706a4fa41e3efd049e89e9a3a2079c060
import ca.csf.mobile2.tp3.database.ReminderRepository;
import ca.csf.mobile2.tp3.model.Reminder;
import ca.csf.mobile2.tp3.model.ReminderList;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

@EActivity(R.layout.activity_create_reminder)
public class CreateNewReminderActivity extends AppCompatActivity {

    protected TimePicker timePicker;
    protected Button notImportantButton;
    protected Button importantButton;
    protected Button veryImportantButton;
    protected TextView newDateTextView;
    protected EditText descriptionEditText;

    private ReminderList reminderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CreateNewReminderActivityComponent createNewReminderActivityComponent = getCreateNewReminderActivityComponent();
        createNewReminderActivityComponent.inject(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private CreateNewReminderActivityComponent getCreateNewReminderActivityComponent(){
        return getMaillesReminderApplication().getCreateNewReminderActivityComponent();
    }

    private MaillesReminderApplication getMaillesReminderApplication(){
        return (MaillesReminderApplication) getApplication();
    }

    @Inject
    public void initializeDependencies(ReminderRepository reminderRepository){
        reminderList = reminderRepository.retrieveRemindersForDay(getIntent().getLongExtra(MainActivity.SELECTED_DATE_UTC, -1),
                getIntent().getLongExtra(MainActivity.SELECTED_DATE_UTC, -1) + DayRemindersActivity.SECONDS_IN_A_DAY);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void injectViews(@ViewById(R.id.timePicker) TimePicker timePicker,
                               @ViewById(R.id.notImportantButton) Button notImportantButton,
                               @ViewById(R.id.importantButton) Button importantButton,
                               @ViewById(R.id.veryImportantButton) Button veryImportantButton,
                               @ViewById(R.id.newDateTextView) TextView newDateTextView,
                               @ViewById(R.id.descriptionEditText) EditText descriptionEditText){
        this.timePicker = timePicker;
        timePicker.setIs24HourView(true);

        this.notImportantButton = notImportantButton;
        notImportantButton.setSelected(true);

        this.importantButton = importantButton;
        this.veryImportantButton = veryImportantButton;

        this.newDateTextView = newDateTextView;
        newDateTextView.setText(getIntent().getStringExtra(MainActivity.SELECTED_DATE));

        this.descriptionEditText = descriptionEditText;
    }

    public void onImportanceButtonClicked(View view) {
        int buttonId = view.getId();

        notImportantButton.setSelected(false);
        importantButton.setSelected(false);
        veryImportantButton.setSelected(false);

        if (buttonId == notImportantButton.getId()) {
            notImportantButton.setSelected(true);
        } else if (buttonId == importantButton.getId()) {
            importantButton.setSelected(true);
        } else if (buttonId == veryImportantButton.getId()) {
            veryImportantButton.setSelected(true);
        }
    }

    protected int getSelectedImportance(){
        if(importantButton.isSelected()){
            return 1;
        }
        if(veryImportantButton.isSelected()){
            return 2;
        }
        else{
            return 0;
        }
    }

    public void returnToDaySelected(View view) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(new Date(getIntent().getLongExtra(MainActivity.SELECTED_DATE_UTC, -1)));
        calender.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
        calender.set(Calendar.MINUTE, timePicker.getMinute());
        reminderList.add(new Reminder(0, descriptionEditText.getText().toString(), getSelectedImportance(), calender.getTime().getTime()));
        alarmMethod(calender);

        finish();
    }

    private void alarmMethod(Calendar calendar){
        Intent myIntent = new Intent(getApplicationContext() , NotifyService.class);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, (calendar.getTimeInMillis()), pendingIntent);

        Toast.makeText(CreateNewReminderActivity.this, "Reminder added", Toast.LENGTH_LONG).show();
    }
}
