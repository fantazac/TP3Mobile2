package ca.csf.mobile2.tp3.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import ca.acodebreak.android.databind.list.BR;
import ca.csf.mobile2.tp3.R;
import ca.csf.mobile2.tp3.database.ReminderDatabaseTableHelper;
import ca.csf.mobile2.tp3.database.ReminderRepository;
import ca.csf.mobile2.tp3.database.ReminderRepositorySyncDecorator;
import ca.csf.mobile2.tp3.database.ReminderSQLRepository;
import ca.csf.mobile2.tp3.databinding.ActivityReminderListBinding;
import ca.csf.mobile2.tp3.model.ReminderList;
import ca.csf.mobile2.tp3.viewmodel.ReminderListViewModel;

@EActivity(R.layout.activity_reminder_list)
public class ReminderListActivity extends AppCompatActivity {

    protected Button sortByTimeButton;
    protected Button sortByImportanceButton;

    private ReminderDatabaseTableHelper reminderDatabaseTableHelper;
    private ReminderRepository reminderRepository;
    private ReminderList reminderListOrderedByTime;
    private ReminderList reminderListOrderedByImportance;

    private ActivityReminderListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void injectViews(@ViewById(R.id.sortByTimeButton) Button sortByTimeButton,
                               @ViewById(R.id.sortByImportanceButton) Button sortByImportanceButton,
                               @ViewById(R.id.activity_reminder_list) View rootView){
        this.sortByTimeButton = sortByTimeButton;
        this.sortByImportanceButton = sortByImportanceButton;

        sortByTimeButton.setSelected(true);

        /*reminderDatabaseTableHelper = new ReminderDatabaseTableHelper(this, MainActivity.DATABASE_FILE_NAME);
        reminderRepository = new ReminderRepositorySyncDecorator(new ReminderSQLRepository(reminderDatabaseTableHelper.getWritableDatabase()));
        reminderListOrderedByTime = reminderRepository.retrieveAllOrderedByTime();
        reminderListOrderedByImportance = reminderRepository.retrieveAllOrderedByImportance();*/

        binding = ActivityReminderListBinding.bind(rootView);
        binding.setReminderItemLayoutId(R.layout.item_reminder);
        binding.setReminderItemVariableId(BR.reminder);
        binding.setReminderList(new ReminderListViewModel(reminderListOrderedByTime, new Handler(getMainLooper())));
    }

    @Override
    protected void onResume(){
        super.onResume();
        /*reminderDatabaseTableHelper = new ReminderDatabaseTableHelper(this, MainActivity.DATABASE_FILE_NAME);
        reminderRepository = new ReminderRepositorySyncDecorator(new ReminderSQLRepository(reminderDatabaseTableHelper.getWritableDatabase()));
        reminderListOrderedByTime = reminderRepository.retrieveAllOrderedByTime();
        reminderListOrderedByImportance = reminderRepository.retrieveAllOrderedByImportance();*/
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
            binding.setReminderList(new ReminderListViewModel(reminderListOrderedByTime, new Handler(getMainLooper())));
        } else if (buttonId == sortByImportanceButton.getId()) {
            sortByImportanceButton.setSelected(true);
            binding.setReminderList(new ReminderListViewModel(reminderListOrderedByImportance, new Handler(getMainLooper())));
        }
    }
}
