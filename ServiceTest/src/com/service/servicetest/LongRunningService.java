package com.service.servicetest;

import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class LongRunningService extends Service {

    private static final String TAG = "ServiceTest/LongRunningService";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        new Thread(new Runnable() {

            @Override
            public void run() {
                Log.i(TAG, "executed at " + new Date().toString());
            }
        }).start();
        startAlarm();
        return super.onStartCommand(intent, flags, startId);
    }

    private void startAlarm() {
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long elapsedTime = SystemClock.elapsedRealtime() + 3 * 1000;
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, elapsedTime, pendingIntent);
    }
}
