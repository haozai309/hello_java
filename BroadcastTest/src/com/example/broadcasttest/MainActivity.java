package com.example.broadcasttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    private static final String TAG = "BroadcastTest/MainActivity";
    private Button mSendBroadcast;
    private Button mSendOrderedBroadcast;
    private Button mSendLocalBroadcast;
    private IntentFilter mIntentFilter;
    private NetworkChangedReceiver mNetworkChangedReceiver;

    private LocalBroadcastReceiver mLocalBroadcastReceiver;
    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSendBroadcast = (Button) findViewById(R.id.send_broadcast);
        mSendOrderedBroadcast = (Button) findViewById(R.id.send_ordered_broadcast);
        mSendLocalBroadcast = (Button) findViewById(R.id.send_local_broadcast);

        mSendBroadcast.setOnClickListener(this);
        mSendOrderedBroadcast.setOnClickListener(this);
        mSendLocalBroadcast.setOnClickListener(this);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        mNetworkChangedReceiver = new NetworkChangedReceiver();
        registerReceiver(mNetworkChangedReceiver, mIntentFilter);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("com.example.broadcast.MY_BROADCAST");
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mLocalBroadcastReceiver = new LocalBroadcastReceiver();
        mLocalBroadcastManager.registerReceiver(mLocalBroadcastReceiver, mIntentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNetworkChangedReceiver);
        mLocalBroadcastManager.unregisterReceiver(mLocalBroadcastReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("com.example.broadcast.MY_BROADCAST");
        switch (v.getId()) {
        case R.id.send_broadcast:
            sendBroadcast(intent);
            break;

        case R.id.send_ordered_broadcast:
            sendOrderedBroadcast(intent, null);
            break;

        case R.id.send_local_broadcast:
            mLocalBroadcastManager.sendBroadcast(intent);
        default:
            break;
        }
    }

    class NetworkChangedReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive " + intent);
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "Network is available.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Network is available.");
            } else {
                Toast.makeText(context, "Network is unavailable.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Network is unavailable.");
            }
        }

    }

    class LocalBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "receive in LocalBroadcastReceiver");
            Toast.makeText(context, "receive in LocalBroadcastReceiver", Toast.LENGTH_SHORT).show();
        }

    }
}
