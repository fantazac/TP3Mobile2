package ca.csf.mobile2.tp3.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.TextView;

import java.util.Date;

import ca.csf.mobile2.tp3.BR;
import ca.csf.mobile2.tp3.model.Reminder;

public class ReminderViewModel extends BaseObservable implements Reminder.ReminderListener
{
    private final Reminder reminder;

    public ReminderViewModel(Reminder reminder) {
        this.reminder = reminder;
    }

    @Bindable
    public long getUtcTime() {return reminder.getUtcTime();}

    @Bindable
    public String getDescription() {
        return reminder.getDescription();
    }

    @Override
    public void onReminderChanged(Reminder eventSource) {

    }
}
