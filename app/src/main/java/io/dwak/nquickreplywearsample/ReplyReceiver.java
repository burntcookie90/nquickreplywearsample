package io.dwak.nquickreplywearsample;

import android.app.Notification;
import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

public class ReplyReceiver extends BroadcastReceiver {
    public ReplyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle resultsFromIntent = RemoteInput.getResultsFromIntent(intent);
        CharSequence reply = resultsFromIntent.getCharSequence(MainActivity.REPLY_KEY);
        int notificationId = resultsFromIntent.getInt(MainActivity.NOTIFICATION_ID);
        Log.d("ReplyReceiver", notificationId + " " +  reply.toString());

        Notification notification = new NotificationCompat.Builder(context)
            .setContentText("Replied!")
            .setSmallIcon(R.mipmap.ic_launcher)
            .build();

       NotificationManagerCompat.from(context)
           .notify(0, notification);
    }
}
