package com.example.aprzybylo.learn.PushService;

import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class PushService extends FirebaseMessagingService {

    public static final String TAG = "MsgFirebaseServ";

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
      //  sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Abc")
                .setContentText("Bcd")
              // .setSmallIcon(getResources().getDrawable(R.id.))
                .build();
        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        manager.notify(2, notification);
    }

//    @Override
//    public void onNewToken(String token) {
//        sendRegistrationToServer(token);
//    }



}
