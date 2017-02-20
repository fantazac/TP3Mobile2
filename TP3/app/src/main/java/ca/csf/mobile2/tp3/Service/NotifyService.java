package ca.csf.mobile2.tp3.service;

import android.app.AlarmManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

import ca.csf.mobile2.tp3.R;
import ca.csf.mobile2.tp3.model.ReminderList;

public class NotifyService extends Service{

    private static String reminderTime = "";
    private static String description = "";
    private static Context contextLocal;
    private static ReminderList reminderListLocal;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public NotifyService(){

    }

    public static void setupService(Context context, ReminderList reminderList){
        reminderListLocal = reminderList;
        contextLocal = context;
        Intent myIntent = new Intent(context, NotifyService.class);
        description = reminderList.getReminder(0).getDescription();
        reminderTime = new SimpleDateFormat("HH:mm").format(new Date(reminderList.getReminder(0).getUtcTime()));
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, reminderList.getReminder(0).getUtcTime(), pendingIntent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification mNotify = new Notification.Builder(this)
                .setContentTitle("You have a reminder! - " + reminderTime)
                .setContentText(description)
                .setSmallIcon(R.drawable.ic_add)
                .setContentIntent(pendingIntent)
                .setColor(100)
                .setSound(sound)
                .build();

        notificationManager.notify(1, mNotify);
        reminderListLocal.remove(reminderListLocal.getReminder(0));
        if(!reminderListLocal.isEmpty()){
            setupService(contextLocal, reminderListLocal);
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
