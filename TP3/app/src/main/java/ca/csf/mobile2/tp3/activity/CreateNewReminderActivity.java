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

import ca.csf.mobile2.tp3.R;
import ca.csf.mobile2.tp3.database.ReminderDatabaseTableHelper;
import ca.csf.mobile2.tp3.database.ReminderRepository;
import ca.csf.mobile2.tp3.database.ReminderRepositorySyncDecorator;
import ca.csf.mobile2.tp3.database.ReminderSQLRepository;
import ca.csf.mobile2.tp3.model.Reminder;
import ca.csf.mobile2.tp3.model.ReminderList;

@EActivity(R.layout.activity_create_reminder)
public class CreateNewReminderActivity extends AppCompatActivity {

    protected TimePicker timePicker;
    protected Button notImportantButton;
    protected Button importantButton;
    protected Button veryImportantButton;
    protected TextView newDateTextView;
    protected EditText descriptionEditText;

    private ReminderDatabaseTableHelper reminderDatabaseTableHelper;
    private ReminderRepository reminderRepository;
    private ReminderList reminderList;

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
        reminderList = reminderRepository.retrieveAll();
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
        reminderList.add(new Reminder(0, descriptionEditText.getText().toString(), getSelectedImportance(), getIntent().getLongExtra(MainActivity.SELECTED_DATE_UTC, -1)));
        finish();
    }
}
