package ca.csf.mobile2.tp3.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Date;

import ca.csf.mobile2.tp3.R;

@EActivity(R.layout.activity_reminder_list)
public class ReminderListActivity extends AppCompatActivity {

    protected Button sortByTimeButton;
    protected Button sortByImportanceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void injectViews(@ViewById(R.id.sortByTimeButton) Button sortByTimeButton,
                               @ViewById(R.id.sortByImportanceButton) Button sortByImportanceButton){
        this.sortByTimeButton = sortByTimeButton;
        this.sortByImportanceButton = sortByImportanceButton;

        sortByTimeButton.setSelected(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSortButtonClicked(View view) {
        int buttonId = view.getId();

        sortByTimeButton.setSelected(false);
        sortByImportanceButton.setSelected(false);

        if (buttonId == sortByTimeButton.getId()) {
            sortByTimeButton.setSelected(true);
        } else if (buttonId == sortByImportanceButton.getId()) {
            sortByImportanceButton.setSelected(true);
        }
    }
}
