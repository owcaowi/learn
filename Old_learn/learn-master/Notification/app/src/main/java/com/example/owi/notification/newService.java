package com.example.owi.notification;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import java.util.Random;

public class newService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // Define destination Class
        Intent mainClass = new Intent(getBaseContext(), otherActivity.class);
        // Receive Action Name
        String actionName = getActionName(intent);

        mainClass.putExtra("ACTION", actionName);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                mainClass, PendingIntent.FLAG_CANCEL_CURRENT);


        //Create Notification
        createNotification( "Action type", actionName,  R.drawable.bell, contentIntent );

        return super.onStartCommand(intent, flags, startId);

    }


    public String getActionName(Intent intent){

        String actionName = new String();

        if( !intent.getExtras().isEmpty() ){
            actionName = (String) intent.getExtras().getString("ACTION","");
        }

        return actionName;
    }

    /**
     *  Creates and sends notifications
     *
     * @param title - The title of notification. Displayed on the top.
     * @param message - The message of notification. Displayed under the title.
     * @param icon - Icon in directory "drawable"
     * @param contentIntent - PendingIntent
     */

    public void createNotification( String title, String message, int icon, PendingIntent contentIntent  ){

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentIntent(contentIntent)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(message);
        Notification n = builder.build();
        Integer notificationNumber = 1 + (int)(Math.random() * 100);
        nm.notify(notificationNumber, n);

    }

}
