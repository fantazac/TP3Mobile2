package ca.csf.mobile2.tp3.viewmodel;

import android.databinding.Bindable;
import android.os.Handler;

import com.android.databinding.library.baseAdapters.BR;

import java.text.SimpleDateFormat;
import java.util.Date;

import ca.acodebreak.android.databind.list.DatabindableViewModel;
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
    public String getDate() {
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yy");
        System.out.println(dateFormat.format(new Date(reminder.getUtcTime())));
        return dateFormat.format(new Date(reminder.getUtcTime()));
    }

    @Bindable
    public String getDescription() {
        return reminder.getDescription();
    }

    public void delete() {
        reminderList.remove(reminder);
    }

    @Override
    public Reminder getModelObject() {
        return reminder;
    }
}
