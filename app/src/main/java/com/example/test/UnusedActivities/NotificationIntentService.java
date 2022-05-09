package com.example.test.UnusedActivities;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationManagerCompat;

import com.example.test.Driving_Activity;
import com.example.test.R;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class NotificationIntentService extends IntentService {
    //this is the channel id for notification manager
    private static final int NOTIFICATION_ID = 3;

    public NotificationIntentService() {
        super("NotfactionIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Notification.Builder noBuilder = new Notification.Builder(this);
        //todo need to change the Notification title
        noBuilder.setContentTitle("this is the Notification title");
        noBuilder.setContentText("this is the Notification body");
        noBuilder.setSmallIcon(R.drawable.logo);
        Intent noIntent = new Intent(this, Driving_Activity.class);
        PendingIntent pendingintent = PendingIntent.getActivity(this,2,noIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        noBuilder.setContentIntent(pendingintent);
        Notification notification = noBuilder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notification);
        NotificationManager mNotificationManager;
        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            noBuilder.setChannelId(channelId);
        }
        noBuilder.build();
        mNotificationManager.notify(NOTIFICATION_ID, notification);


    }
}