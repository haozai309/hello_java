package com.media.notificationtest;

import java.io.File;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    private Button mSendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSendNotice = (Button) findViewById(R.id.send_notice);
        mSendNotice.setOnClickListener(this);
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
        switch (v.getId()) {
        case R.id.send_notice:
            NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
            Intent intent = new Intent(this, NotificationActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            Uri soundUri = Uri.fromFile(new File("/system/media/audio/ringtones/Zeta.ogg"));

            Notification notification = new Notification.Builder(this)
                    .setContentTitle("Notification comes")
                    .setContentText("Content text")
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setSound(soundUri)
                    .setVibrate(new long[]{0, 1000, 1000, 1000})
                    .setLights(Color.GREEN, 1000, 1000)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();
            notification.flags |= Notification.FLAG_SHOW_LIGHTS;
            notificationManager.notify(1, notification);
            break;

        default:
            break;
        }

    }
}
