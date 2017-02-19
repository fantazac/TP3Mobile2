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
import ca.csf.mobile2.tp3.activity.MainActivity;

public class NotifyService extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification mNotify = new Notification.Builder(this)
                .setContentTitle("Mailles Reminder")
                .setContentText("You have a reminder!")
                .setSmallIcon(R.drawable.ic_add)
                .setContentIntent(pendingIntent)
                .setSound(sound)
                .build();

        notificationManager.notify(1, mNotify);
    }
}
