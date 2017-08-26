package com.androvators.pocketlistify.pocketlistify.BroadcastReceivers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.androvators.pocketlistify.pocketlistify.Activities.MainActivity;
import com.androvators.pocketlistify.pocketlistify.R;
import com.androvators.pocketlistify.pocketlistify.UtilityClasses.SQLiteDatabaseHelper;

/**
 * Created by shreyas on 8/12/2017.
 */

public class NotificationReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent repeatingIntent = new Intent(context,MainActivity.class);
        repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,repeatingIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        SQLiteDatabaseHelper helper = new SQLiteDatabaseHelper(context);
        int todayCount = helper.getTodaysCount();
        String notifyTitle;
        if(todayCount>1)
            notifyTitle = "You have "+todayCount+" tasks pending today";
        else
            notifyTitle = "You have "+todayCount+" task pending today";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notify)
                .setContentTitle(notifyTitle)
                .setAutoCancel(true);

        notificationManager.notify(100,builder.build());
    }
}
