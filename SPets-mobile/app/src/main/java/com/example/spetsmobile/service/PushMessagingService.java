package com.example.spetsmobile.service;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushMessagingService extends FirebaseMessagingService {

    private LocalBroadcastManager broadcaster;

    @Override
    public void onCreate() {
        broadcaster = LocalBroadcastManager.getInstance(this);
    }

    @SuppressLint("NewApi")
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        String title = message.getNotification().getTitle();
        String body = message.getNotification().getBody();
        String CHANNEL_ID = "MESSAGE";

        Intent intent = new Intent("MyMessage");
        intent.putExtra("key", title);
        intent.putExtra("value", body);

        broadcaster.sendBroadcast(intent);

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                "SPets",
                NotificationManager.IMPORTANCE_HIGH);
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder notiBuilder = new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("Thông Báo")
                .setContentText(body)
                .setSmallIcon(com.example.spetsmobile.R.mipmap.ic_launcher)
                .setAutoCancel(true);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        NotificationManagerCompat.from(this).notify(1, notiBuilder.build());

        super.onMessageReceived(message);
    }

}
