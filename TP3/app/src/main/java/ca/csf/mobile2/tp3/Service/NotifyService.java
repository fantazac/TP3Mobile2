package ca.csf.mobile2.tp3.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.Nullable;

import ca.csf.mobile2.tp3.R;
import ca.csf.mobile2.tp3.activity.CreateNewReminderActivity;
import ca.csf.mobile2.tp3.activity.MainActivity;

public class NotifyService extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification mNotify = new Notification.Builder(this)
                .setContentTitle("You have a reminder! - " + intent.getStringExtra(CreateNewReminderActivity.REMINDER_TIME))
                .setContentText(intent.getStringExtra(CreateNewReminderActivity.REMINDER_DESCRIPTION))
                .setSmallIcon(R.drawable.ic_add)
                .setContentIntent(pendingIntent)
                .setColor(100)
                .setSound(sound)
                .build();
        System.out.println(intent.getStringExtra(CreateNewReminderActivity.REMINDER_DESCRIPTION));
        notificationManager.notify(1, mNotify);

        return super.onStartCommand(intent, flags, startId);
    }
}
