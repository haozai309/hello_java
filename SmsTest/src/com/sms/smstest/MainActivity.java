package com.sms.smstest;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String TAG = "SmsTest/MainActivity";

    private TextView mSender;
    private TextView mContent;
    private IntentFilter mIntentFilter;
    private MessageReciever mMessageReceiver;

    private EditText mTo;
    private EditText mMsgInput;
    private Button mSendMessage;

    private IntentFilter mSendFilter;
    private SendStatusReceiver mSendStatusReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSender = (TextView) findViewById(R.id.sender);
        mContent = (TextView) findViewById(R.id.content);
        mTo = (EditText) findViewById(R.id.to);
        mMsgInput = (EditText) findViewById(R.id.msg_input);
        mSendMessage = (Button) findViewById(R.id.send);
        mSendMessage.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mTo.getText().toString().length() > 0
                        && mMsgInput.getText().toString().length() > 0) {
                    mMsgInput.setText("");
                    Intent intent = new Intent("SENT_SMS_ACTION");
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0,
                            intent, 0);
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(mTo.getText().toString(), null, mMsgInput.getText()
                            .toString(), pendingIntent, null);
                }
            }
        });

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        mIntentFilter.setPriority(100);
        mMessageReceiver = new MessageReciever();
        registerReceiver(mMessageReceiver, mIntentFilter);

        mSendFilter = new IntentFilter();
        mSendFilter.addAction("SENT_SMS_ACTION");
        mSendStatusReceiver = new SendStatusReceiver();
        registerReceiver(mSendStatusReceiver, mSendFilter);
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
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        unregisterReceiver(mSendStatusReceiver);
        super.onDestroy();
    }

    class MessageReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "Received intent " + intent);
            Bundle bundle = intent.getExtras();
            Object[] pdus = (Object[]) bundle.get("pdus"); // get SMS message
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < messages.length; ++i) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            String address = messages[0].getOriginatingAddress();
            String fullMessage = "";
            for (SmsMessage message : messages) {
                fullMessage += message.getMessageBody();
            }
            Log.i(TAG, "address is " + address);
            Log.i(TAG, "fullMessage is " + fullMessage);

            mSender.setText(address);
            mContent.setText(fullMessage);

            // filter this message by stop this broadcast
            abortBroadcast();
        }
    }

    class SendStatusReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (getResultCode() == RESULT_OK) {
                Toast.makeText(context, "Send succeeded", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Send failed.", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
