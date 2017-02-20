package ca.csf.mobile2.tp3.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import ca.acodebreak.android.databind.list.BR;
import ca.csf.mobile2.tp3.databinding.application.MaillesReminderApplication;
import ca.csf.mobile2.tp3.R;
import ca.csf.mobile2.tp3.databinding.components.ReminderListActivityComponent;
import ca.csf.mobile2.tp3.database.ReminderRepository;
import ca.csf.mobile2.tp3.databinding.ActivityReminderListBinding;
import ca.csf.mobile2.tp3.model.Reminder;
import ca.csf.mobile2.tp3.model.ReminderList;
import ca.csf.mobile2.tp3.service.NotifyService;
import ca.csf.mobile2.tp3.viewmodel.ReminderListViewModel;

@EActivity(R.layout.activity_reminder_list)
public class ReminderListActivity extends AppCompatActivity {

    protected Button sortByTimeButton;
    protected Button sortByImportanceButton;

    private ReminderRepository reminderRepository;
    private ReminderList reminderList;

    private ActivityReminderListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ReminderListActivityComponent reminderListActivityComponent = getReminderListActivityComponent();
        reminderListActivityComponent.inject(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private ReminderListActivityComponent getReminderListActivityComponent(){
        return getMaillesReminderApplication().getReminderListActivityComponent();
    }

    private MaillesReminderApplication getMaillesReminderApplication(){
        return (MaillesReminderApplication) getApplication();
    }

    @Inject
    public void initializeDependencies(ReminderRepository reminderRepository){
        this.reminderRepository = reminderRepository;

        reminderList = reminderRepository.retrieveAllOrderedByTime();
    }

    protected void injectViews(@ViewById(R.id.sortByTimeButton) Button sortByTimeButton,
                               @ViewById(R.id.sortByImportanceButton) Button sortByImportanceButton,
                               @ViewById(R.id.activity_reminder_list) View rootView){
        this.sortByTimeButton = sortByTimeButton;
        this.sortByImportanceButton = sortByImportanceButton;

        sortByTimeButton.setSelected(true);

        binding = ActivityReminderListBinding.bind(rootView);
        binding.setReminderItemLayoutId(R.layout.item_reminder);
        binding.setReminderItemVariableId(BR.reminder);
        binding.setReminderList(new ReminderListViewModel(reminderList));
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
            reminderList = reminderRepository.retrieveAllOrderedByTime();
            setupNewService();
            binding.setReminderList(new ReminderListViewModel(reminderList));
        } else if (buttonId == sortByImportanceButton.getId()) {
            sortByImportanceButton.setSelected(true);
            reminderList = reminderRepository.retrieveAllOrderedByImportance();
            setupNewService();
            binding.setReminderList(new ReminderListViewModel(reminderList));
        }
    }

    protected void setupNewService(){
        ReminderList temporaryList = reminderRepository.retrieveAllOrderedByTime();
        if(!temporaryList.isEmpty()){
            NotifyService.setupService(getApplicationContext(), temporaryList);
        }
    }
}
