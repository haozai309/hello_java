package com.web.networktest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

    private static final String TAG = "NetworkTest/MainActivity";
    private static final int SHOW_RESPONSE = 0;
    private static int flag = 1;

    private Button mSendRequest;
    private TextView mResponseText;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case SHOW_RESPONSE:
                String response = (String) msg.obj;
                if (response.length() > 30) {
                    Log.i(TAG, "response " + response.substring(0, 30));
                }
                mResponseText.setText(response);
                break;
            default:
                Log.e(TAG, "default flow");
                break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSendRequest = (Button) findViewById(R.id.send_request);
        mResponseText = (TextView) findViewById(R.id.response);
        mSendRequest.setOnClickListener(this);
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
        if (v.getId() == R.id.send_request) {
            Log.i(TAG, "press button " + v.getId());
            String text;
            if (flag % 2 == 0) {
                text = "Get web page via HttpURLConnection";
                sendHttpRequestWithHttpUrlConnection();
            } else {
                text = "Get web page via HttpClient";
                sendRequestWithHttpClient();
            }
            Toast.makeText(this, text + flag, Toast.LENGTH_SHORT).show();
            flag++;
        }
    }

    private void sendHttpRequestWithHttpUrlConnection() {
        Log.i(TAG, "sendHttpRequestWithHttpUrlConnection+");
        new Thread(new Runnable() {

            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    InputStream input = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    message.obj = response.toString();
                    mHandler.sendMessage(message);
                    Log.i(TAG, "sendMessage");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void sendRequestWithHttpClient() {
        Log.i(TAG, "sendRequestWithHttpClient");
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("http://www.youku.com");
                    HttpResponse httpResponse = httpClient.execute(httpGet);

                    Log.i(TAG, "status is " + httpResponse.getStatusLine().getStatusCode());

                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "utf-8");
                        Message message = new Message();
                        message.obj = response.toString();
                        mHandler.sendMessage(message);
                        Log.i(TAG, "sendMessage");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
