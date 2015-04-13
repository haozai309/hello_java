package com.thread.androidthreadtest;

import java.text.DateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener {

    private static final String TAG = "ThreadTest/MainActivity";
    private static final int UPDATE_TEXT = 1;

    private TextView mTextView;
    private Button mChangeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);
        mChangeText = (Button) findViewById(R.id.change_text);
        mChangeText.setOnClickListener(this);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case UPDATE_TEXT:
                Time now = new Time();
                now.setToNow();
                String currentDateTime = DateFormat.getDateTimeInstance().format(new Date());
                String display = "now: " + now + "\n" + "currentDateTime: " + currentDateTime;
                mTextView.setText("Nice to meet you\n" + display);
                break;
            default:
                break;
            }
        }

    };

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
        case R.id.change_text:
            Log.i(TAG, "send message");
            Message message = new Message();
            message.what = UPDATE_TEXT;
            mHandler.sendMessage(message);
            break;
        default:
            break;
        }
    }
}
