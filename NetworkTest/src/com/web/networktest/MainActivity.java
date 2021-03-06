package com.web.networktest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
    private static final String PREFIX_LINK = "http://192.168.1.104/~haozhang/";
    private static final String XML_LINK = PREFIX_LINK + "get_data.xml";
    private static final String JSON_LINK = PREFIX_LINK + "get_data.json";

    private static final int SHOW_RESPONSE_XML = 0;
    private static final int SHOW_RESPONSE_JSON = 1;
    private static int flag = 0;

    private Button mSendRequest;
    private TextView mResponseText;
    private String mPrefixText;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case SHOW_RESPONSE_XML:
            case SHOW_RESPONSE_JSON:
                String response = (String) msg.obj;
                Log.i(TAG, "response " + response);
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
            if (flag % 4 == 0) {
                mPrefixText = "Get web page via HttpURLConnection";
                sendHttpRequestWithHttpUrlConnection(SHOW_RESPONSE_XML);
            } else if (flag % 4 == 1) {
                mPrefixText = "Get web page via HttpClient";
                sendRequestWithHttpClient(SHOW_RESPONSE_XML);
            } else if (flag % 4 == 2) {
                mPrefixText = "Get Json data via HttpURLConnection";
                sendHttpRequestWithHttpUrlConnection(SHOW_RESPONSE_JSON);
            } else {
                mPrefixText = "Get Json data via HttpClient";
                sendRequestWithHttpClient(SHOW_RESPONSE_JSON);
            }
            Toast.makeText(this, mPrefixText + " " + flag, Toast.LENGTH_SHORT).show();
            flag++;
        }
    }

    private void sendHttpRequestWithHttpUrlConnection(final int type) {
        Log.i(TAG, "sendHttpRequestWithHttpUrlConnection");
        final String link;
        if (type == SHOW_RESPONSE_XML) {
            link = XML_LINK;
        } else {
            link = JSON_LINK;
        }

        new Thread(new Runnable() {

            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(link);
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
                    message.what = type;
                    message.obj = response.toString();
                    mHandler.sendMessage(message);
                    Log.i(TAG, "sendMessage");

                    if (type == SHOW_RESPONSE_XML) {
                        // parse the xml data via Pull way
                        parseXMLWithPull(response.toString());
                    } else {
                        parseJsonWithJsonObject(response.toString());
                    }

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

    private void sendRequestWithHttpClient(final int type) {
        Log.i(TAG, "sendRequestWithHttpClient");

        final String link;
        if (type == SHOW_RESPONSE_XML) {
            link = XML_LINK;
        } else {
            link = JSON_LINK;
        }

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(link);
                    HttpResponse httpResponse = httpClient.execute(httpGet);

                    Log.i(TAG, "status is " + httpResponse.getStatusLine().getStatusCode());

                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "utf-8");
                        Message message = new Message();
                        message.what = type;
                        message.obj = response.toString();
                        mHandler.sendMessage(message);
                        Log.i(TAG, "sendMessage");

                        if (type == SHOW_RESPONSE_XML) {
                            // parse the xml data via SAX way
                            parseXMLWithSAX(response.toString());
                        } else {
                            parseJsonWithGson(response.toString());
                        }

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

    private void parseXMLWithSAX(String xmlData) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler handler = new ContentHandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseJsonWithJsonObject(String jsonData) {
        Log.i(TAG, "parseJsonWithJsonObject");
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.i(TAG, "[Json] id is " + id);
                Log.i(TAG, "[Json] name is " + name);
                Log.i(TAG, "[Json] version is " + version);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseJsonWithGson(String jsonData) {
        Log.i(TAG, "parseJsonWithGson");
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>() {
        }.getType());
        for (App app : appList) {
            Log.i(TAG, "[Json] id is " + app.getId());
            Log.i(TAG, "[Json] name is " + app.getName());
            Log.i(TAG, "[Json] version is " + app.getVersion());
        }
    }
}
