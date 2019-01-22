package com.example.aprzybylo.learn.Service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;


public class MyIntentService extends IntentService {

    public static String TAG = "SERVICE";
    public static String NAME = "NAME";
    public static String FIRST_TASK = "FIRST";
    public static String SECOND_TASK = "SECOND";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        stopSelf();

        String taskNumber = intent.getStringExtra(NAME);

        try {
            if (FIRST_TASK.equals(taskNumber)) {
                runFirstTask();
            } else {
                runSecondTask();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void runFirstTask() throws InterruptedException {

        int count = 1;
        do {
            Thread.sleep(1000);
            Log.d(TAG, String.valueOf(count));
            count++;
        } while (count < 10);
    }

    public void runSecondTask() throws InterruptedException {

        int count = 20;
        do {
            Thread.sleep(1000);
            Log.d(TAG, String.valueOf(count));
            count--;
        } while (count > 10);

    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Stoped");
        super.onDestroy();
    }

}
