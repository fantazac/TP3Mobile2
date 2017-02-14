package ca.csf.mobile2.tp3.viewmodel;

import android.os.Handler;

import ca.acodebreak.android.databind.list.DatabindableViewModel;
import ca.acodebreak.android.databind.list.DatabindableViewModelList;
import ca.csf.mobile2.tp3.model.Reminder;
import ca.csf.mobile2.tp3.model.ReminderList;

public class ReminderListViewModel extends DatabindableViewModelList<Reminder> {

    private final ReminderList reminderList;
    private final Handler handler;

    public ReminderListViewModel(ReminderList reminderList, Handler handler) {
        this.reminderList = reminderList;
        this.handler = handler;

        for (Reminder reminder : reminderList) {
            addModelItem(reminder);
        }

        reminderList.addReminderAddedListener(this::onReminderAdded);
        reminderList.addReminderRemovedListener(this::onReminderRemoved);
    }

    private void onReminderAdded(Reminder reminder) {
        addModelItem(reminder);
    }

    private void onReminderRemoved(Reminder reminder) {
        removeModelItem(reminder);
    }

    @Override
    protected DatabindableViewModel<Reminder> createViewModel(Reminder reminder) {
        return new ReminderViewModel(reminderList, reminder, handler);
    }

}