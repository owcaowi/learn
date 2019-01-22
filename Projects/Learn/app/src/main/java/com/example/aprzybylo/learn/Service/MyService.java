package com.example.aprzybylo.learn.Service;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.aprzybylo.learn.OtherActivity;
import com.example.aprzybylo.learn.R;

public class MyService extends Service {

    public static String TAG = "SERVICE";

    @Override
    public void onCreate() {

        if (Build.VERSION.SDK_INT >= 26) {
            Notification notification = getNotification();
            // should not be 0
            startForeground(1, notification);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    runFirstTask();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private Notification getNotification() {

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, OtherActivity.class), 0);

        String channelId = "some_channel_id";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setChannel(channelId, "Foreground info");
        }

        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setTicker("New notification")
                    .setContentTitle("Title")
                    .setContentText("New notification")
                    .setContentIntent(contentIntent)
                    .setChannelId(channelId)
                    .build();
        }

        return notification;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setChannel(String channelId, CharSequence channelName){

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        notificationManager.createNotificationChannel(notificationChannel);

    }

    public void runFirstTask() throws InterruptedException {

        int count = 1;
        do {
            Thread.sleep(1000);
            Log.d(TAG, String.valueOf(count));
            count++;
        } while (count < 10);

        stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Stop");
    }
}
