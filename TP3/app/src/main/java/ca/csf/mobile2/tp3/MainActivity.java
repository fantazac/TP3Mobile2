package ca.csf.mobile2.tp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import org.androidannotations.annotations.EActivity;

@EActivity
public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private Button notImportantButton;
    private Button importantButton;
    private Button veryImportantButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reminder);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        notImportantButton = (Button) findViewById(R.id.notImportantButton);
        importantButton = (Button) findViewById(R.id.importantButton);
        veryImportantButton = (Button) findViewById(R.id.veryImportantButton);

        notImportantButton.setSelected(true);
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
}
