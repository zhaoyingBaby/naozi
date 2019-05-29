package com.example.dell.zuoye;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by DELL on 2019/5/27.
 */

public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        User user = intent.getParcelableExtra("user");
        NotificationManager systemService = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context, LoadActivity.class);
        PendingIntent activity = PendingIntent.getActivity(context, 100, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification build = new NotificationCompat.Builder(context)
                .setContentIntent(activity)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(user.getDesc())
                .setContentTitle("通知")
                .build();
        systemService.notify(1, build);

    }
}
