package ca.csf.mobile2.tp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_create_reminder)
public class CreateNewReminderActivity extends AppCompatActivity {

    protected TimePicker timePicker;
    protected Button notImportantButton;
    protected Button importantButton;
    protected Button veryImportantButton;
    protected TextView newDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                               @ViewById(R.id.newDateTextView) TextView newDateTextView){
        this.timePicker = timePicker;
        timePicker.setIs24HourView(true);

        this.notImportantButton = notImportantButton;
        notImportantButton.setSelected(true);

        this.importantButton = importantButton;
        this.veryImportantButton = veryImportantButton;

        this.newDateTextView = newDateTextView;
        newDateTextView.setText(getIntent().getStringExtra(MainActivity.SELECTED_DATE));
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

    public void returnToDaySelected(View view) {
        finish();
    }
}
