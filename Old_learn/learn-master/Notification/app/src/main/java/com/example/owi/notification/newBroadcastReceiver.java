package com.example.owi.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;


public class newBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        /*
        * on Receive recognize type
        * and save string to Bundle
        *
        * */

        Intent serviceIntent = new Intent(context, newService.class);

        String intentAction = intent.getAction();
            switch( intentAction ){

                case "android.intent.action.ACTION_POWER_CONNECTED":
                    serviceIntent.putExtra("ACTION", "ACTION_POWER_CONNECTED");
                    break;

                case "android.intent.action.AIRPLANE_MODE":
                    serviceIntent.putExtra("ACTION", "AIRPLANE_MODE");
                    break;
            }

            context.startService(serviceIntent);
    }

}
