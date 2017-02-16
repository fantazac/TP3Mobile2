package ca.csf.mobile2.tp3.activity;

import android.content.Intent;
import android.os.Handler;
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

import ca.acodebreak.android.databind.list.BR;
import ca.csf.mobile2.tp3.R;
import ca.csf.mobile2.tp3.database.ReminderDatabaseTableHelper;
import ca.csf.mobile2.tp3.database.ReminderRepository;
import ca.csf.mobile2.tp3.database.ReminderRepositorySyncDecorator;
import ca.csf.mobile2.tp3.database.ReminderSQLRepository;
import ca.csf.mobile2.tp3.databinding.ActivityDaySelectedBinding;
import ca.csf.mobile2.tp3.model.ReminderList;
import ca.csf.mobile2.tp3.viewmodel.ReminderListViewModel;

@EActivity(R.layout.activity_day_selected)
public class DayRemindersActivity extends AppCompatActivity {

    public static final int SECONDS_IN_A_DAY = 86400000;

    protected TextView dateTextView;
    protected Date selectedDate;

    private ReminderDatabaseTableHelper reminderDatabaseTableHelper;
    private ReminderRepository reminderRepository;
    private ReminderList reminderList;

    private ActivityDaySelectedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume(){
        super.onResume();
        reminderDatabaseTableHelper = new ReminderDatabaseTableHelper(this, MainActivity.DATABASE_FILE_NAME);
        reminderRepository = new ReminderRepositorySyncDecorator(new ReminderSQLRepository(reminderDatabaseTableHelper.getWritableDatabase()));
        reminderList = reminderRepository.retrieveRemindersForDay(getIntent().getLongExtra(MainActivity.SELECTED_DATE_UTC, -1), getIntent().getLongExtra(MainActivity.SELECTED_DATE_UTC, -1) + SECONDS_IN_A_DAY);
    }

    protected void injectViews(@ViewById(R.id.dateTextView) TextView dateTextView,
                               @ViewById(R.id.activity_day_selected) View rootView){
        this.dateTextView = dateTextView;
        selectedDate = new Date(getIntent().getLongExtra(MainActivity.SELECTED_DATE_UTC, -1));
        dateTextView.setText(getCurrentDay(selectedDate));

        reminderDatabaseTableHelper = new ReminderDatabaseTableHelper(this, MainActivity.DATABASE_FILE_NAME);
        reminderRepository = new ReminderRepositorySyncDecorator(new ReminderSQLRepository(reminderDatabaseTableHelper.getWritableDatabase()));
        reminderList = reminderRepository.retrieveRemindersForDay(getIntent().getLongExtra(MainActivity.SELECTED_DATE_UTC, -1), getIntent().getLongExtra(MainActivity.SELECTED_DATE_UTC, -1) + SECONDS_IN_A_DAY);

        binding = ActivityDaySelectedBinding.bind(rootView);
        binding.setReminderItemLayoutId(R.layout.item_reminder);
        binding.setReminderItemVariableId(BR.reminder);
        binding.setReminderList(new ReminderListViewModel(reminderList, new Handler(getMainLooper())));
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