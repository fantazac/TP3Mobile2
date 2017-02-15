package ca.csf.mobile2.tp3.viewmodel;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.widget.RelativeLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

import ca.acodebreak.android.databind.list.DatabindableViewModel;
import ca.csf.mobile2.tp3.R;
import ca.csf.mobile2.tp3.model.Reminder;
import ca.csf.mobile2.tp3.model.ReminderList;

public class ReminderViewModel extends DatabindableViewModel<Reminder> {
    private final ReminderList reminderList;
    private final Reminder reminder;
    private final Handler handler;

    public ReminderViewModel(ReminderList reminderList, Reminder reminder, Handler handler) {
        this.reminderList = reminderList;
        this.reminder = reminder;
        this.handler = handler;
    }

    @Bindable
    public String getDateAndTime() {
        String date;
        String time;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        date = dateFormat.format(new Date(reminder.getUtcTime()));

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
        time = dateFormat2.format(new Date(reminder.getUtcTime()));

        return date + " - " + time;
    }

    @Bindable
    public String getDescription() {
        return reminder.getDescription();
    }

    @Bindable
    public int getImportance(){
        return reminder.getImportance();
    }

    @BindingAdapter("reminderBackground")
    public static void setReminderBackgroundWithImportance(RelativeLayout view, int importance){
        switch (importance) {
            case 2:
                view.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.fragment_very_important_background));
                break;
            case 1:
                view.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.fragment_important_background));
                break;
            default:
                view.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.fragment_not_important_background));
                break;
        }
    }

    public void delete() {
        reminderList.remove(reminder);
    }

    @Override
    public Reminder getModelObject() {
        return reminder;
    }
}
