package com.web.networktest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

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
    private static int flag = 0;

    private Button mSendRequest;
    private TextView mResponseText;
    private String mPrefixText;

    private static final String HTTP_LINK = "http://192.168.1.105/~haozai309/get_data.xml";

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case SHOW_RESPONSE:
                String response = (String) msg.obj;
                if (response.length() > 100) {
                    Log.i(TAG, "response " + response.substring(0, 100));
                } else {
                    Log.i(TAG, "response " + response);
                }
                mResponseText.setText(mPrefixText + "\n" + response);
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
            if (flag % 3 == 0) {
                mPrefixText = "Get web page via HttpURLConnection";
                sendHttpRequestWithHttpUrlConnection();
            } else if (flag % 3 == 1) {
                mPrefixText = "Get web page via HttpClient";
                sendRequestWithHttpClient();
            } else {
                mPrefixText = "Get web page and pull parse";
                sendRequestAndParse();
            }
            Toast.makeText(this, mPrefixText + " " + flag, Toast.LENGTH_SHORT).show();
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
                    URL url = new URL(HTTP_LINK);
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
                    HttpGet httpGet = new HttpGet(HTTP_LINK);
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

    private void sendRequestAndParse() {
        Log.i(TAG, "sendRequestAndParse");
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(HTTP_LINK);
                    HttpResponse httpResponse = httpClient.execute(httpGet);

                    Log.i(TAG, "status is " + httpResponse.getStatusLine().getStatusCode());

                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "utf-8");
                        Message message = new Message();
                        message.obj = response.toString();
                        mHandler.sendMessage(message);
                        Log.i(TAG, "sendMessage");

                        parseXMLWithPull(response);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseXMLWithPull(String xmlData) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                // Log.i(TAG, "eventType is " + eventType);
                switch (eventType) {
                case XmlPullParser.START_TAG:
                    if ("id".equals(nodeName)) {
                        id = xmlPullParser.nextText();
                    } else if ("name".equals(nodeName)) {
                        name = xmlPullParser.nextText();
                    } else if ("version".equals(nodeName)) {
                        version = xmlPullParser.nextText();
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("app".equals(nodeName)) {
                        Log.i(TAG, "id is " + id);
                        Log.i(TAG, "name is " + name);
                        Log.i(TAG, "version is " + version);
                    }
                default:
                    break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
