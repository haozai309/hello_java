package com.service.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "ServiceTest/MyService";

    private DownloadBinder mBinder;

    // Invoke service's method via binder
    class DownloadBinder extends Binder {

        private static final String TAG = "ServiceTest/DownloadBinder";

        public void startDownload() {
            Log.i(TAG, "startDownload");
        }

        public int getProgress() {
            Log.i(TAG, "getProgress");
            return 0;
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Notification comes")
                .setContentText("Content text")
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        Log.i(TAG, "start foreground");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

}
