package io.dwak.nquickreplywearsample;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String REPLY_KEY = "REPLY_KEY";
    public static final String NOTIFICATION_ID = "notificationId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postNotification();
            }
        });
    }

    private void postNotification() {

        Intent replyIntent = new Intent(this, ReplyReceiver.class);
        replyIntent.putExtra(NOTIFICATION_ID, 0);

        PendingIntent replyPendingIntent = PendingIntent.getBroadcast(this, 1, replyIntent, PendingIntent
            .FLAG_UPDATE_CURRENT);

        RemoteInput remoteInput = new RemoteInput.Builder(REPLY_KEY)
            .setLabel("Reply")
            .build();

        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.mipmap.ic_launcher,
            "Notification", replyPendingIntent)
            .addRemoteInput(remoteInput)
            .build();

        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();
        wearableExtender.addAction(action);
        Notification notification = new NotificationCompat.Builder(this)
            .extend(wearableExtender)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Title")
            .setContentText("Text")
            .build();

        NotificationManagerCompat.from(this)
            .notify(0, notification);
    }
}
