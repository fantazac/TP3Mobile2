package ca.csf.mobile2.tp3.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.TextView;

import ca.csf.mobile2.tp3.BR;
import ca.csf.mobile2.tp3.model.Reminder;

public class ReminderViewModel extends BaseObservable implements Reminder.ReminderListener
{
    private final Reminder reminder;

    public ReminderViewModel(Reminder reminder) {
        this.reminder = reminder;
    }

    @Bindable
    public int getYear() {
        return reminder.getYear();
    }

    @Bindable
    public int getMonth() {
        return reminder.getMonth();
    }

    @Bindable
    public int getDate() {
        return reminder.getDate();
    }

    @Bindable
    public int getImportance() {
        return reminder.getImportance();
    }

    @Bindable
    public String getDescription() {
        return reminder.getDescription();
    }

    @BindingAdapter("date")
    public static void setDateOnView(TextView textView, String date) {
        textView.setText(date);
    }

    @Override
    public void onReminderChanged(Reminder eventSource) {

    }
}
