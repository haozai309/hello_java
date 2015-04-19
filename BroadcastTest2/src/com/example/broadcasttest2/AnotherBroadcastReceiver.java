package com.example.broadcasttest2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AnotherBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "BroadcastTest/AnotherBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String text = "received in Another BroadcastReceiver";
        Log.i(TAG, text);
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
