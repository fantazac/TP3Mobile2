package ca.csf.mobile2.tp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_create_reminder)
public class CreateNewReminderActivity extends AppCompatActivity {

    protected TimePicker timePicker;
    protected Button notImportantButton;
    protected Button importantButton;
    protected Button veryImportantButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void injectViews(@ViewById(R.id.timePicker) TimePicker timePicker,
                               @ViewById(R.id.notImportantButton) Button notImportantButton,
                               @ViewById(R.id.importantButton) Button importantButton,
                               @ViewById(R.id.veryImportantButton) Button veryImportantButton){
        this.timePicker = timePicker;
        timePicker.setIs24HourView(true);

        this.notImportantButton = notImportantButton;
        notImportantButton.setSelected(true);

        this.importantButton = importantButton;
        this.veryImportantButton = veryImportantButton;
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
        Intent returnToDaySelected = new Intent(getApplicationContext(), DayRemindersActivity_.class);
        startActivity(returnToDaySelected);
        finish();
    }

    public void newReminderBack(View view) {
        Intent returnToDaySelected = new Intent(getApplicationContext(), DayRemindersActivity_.class);
        startActivity(returnToDaySelected);
        finish();
    }
}
