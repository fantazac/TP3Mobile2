package ca.csf.mobile2.tp3.viewmodel;

import android.databinding.Bindable;
import android.os.Handler;

import ca.csf.mobile2.tp3.model.Reminder;
import ca.csf.mobile2.tp3.model.ReminderList;

public class ReminderViewModel extends DatabindableViewModel<Reminder>
{
    private final ReminderList reminderList;
    private final Reminder reminder;
    private final Handler handler;

    private final Reminder.ReminderChangedListener reminderChangedListener;

    public ReminderViewModel(ReminderList reminderList, Reminder reminder, Handler handler) {
        this.reminderList = reminderList;
        this.reminder = reminder;
        this.handler = handler;

        reminderChangedListener = this::onReminderChanged;
        reminder.addReminderChangedListener(reminderChangedListener);
    }

    @Bindable
    public long getUtcTime() {return reminder.getUtcTime();}

    @Bindable
    public String getDescription() {
        return reminder.getDescription();
    }

    public void delete() {
        reminder.removeReminderChangedListener(reminderChangedListener);
        reminderList.remove(reminder);
    }

    @Override
    public void onReminderChanged(Reminder eventSource) {
        notifyPropertyChanged(BR.name);
    }
}
