package com.example.locationtest;

import java.io.IOException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    private static final String TAG = "LocationTest/MainActivity";
    private static final int SHOW_LOCATION = 1;

    private TextView mPosition;
    private Button mGetPosition;
    private Button mShowPosition;
    private String mProvider;
    private Location mLocation;
    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mPosition = (TextView) findViewById(R.id.position_text_view);
        mGetPosition = (Button) findViewById(R.id.get_location);
        mShowPosition = (Button) findViewById(R.id.show_location);
        mGetPosition.setOnClickListener(this);
        mShowPosition.setOnClickListener(this);
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
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(mLocationListener);
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.get_location:
            getCurrentLocation();
            break;
        case R.id.show_location:
            showLocation();
            break;

        default:
            break;
        }

    }

    private void getCurrentLocation() {

        List<String> providerList = mLocationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            mProvider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            mProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "No location provider to use", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.i(TAG, "mProvider " + mProvider);
        mLocation = mLocationManager.getLastKnownLocation(mProvider);
        Log.i(TAG, "location " + mProvider);
        if (mLocation != null) {
            displayLocation(mLocation);
        }
        mLocationManager.requestLocationUpdates(mProvider, 5000, 1, mLocationListener);
    }

    private void displayLocation(Location location) {
        String currentLocation = "latitude is " + location.getLatitude() + ", longitude "
                + location.getLongitude();
        Log.i(TAG, "currentLocation is " + currentLocation);
        mPosition.setText(currentLocation);
    }

    private void showLocation() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    StringBuilder url = new StringBuilder();
//                    url.append("https://maps.googleapis.com/maps/api/geocode/json?latlng=")
//                            .append(mLocation.getLatitude()).append(",")
//                            .append(mLocation.getLongitude()).append("&sensor=false");
                    url.append("http://192.168.1.104/~haozhang/location.json");
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(url.toString());
                    httpGet.addHeader("Accept-Language", "zh-CN");
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity httpEntity = httpResponse.getEntity();
                        String response = EntityUtils.toString(httpEntity, "utf-8");
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray resultArray = jsonObject.getJSONArray("results");
                        Log.i(TAG, "resultArray " + resultArray);
                        if (resultArray.length() > 0) {
                            JSONObject subObject = resultArray.getJSONObject(0);
                            String address = subObject.getString("formatted_address");
                            Message message = new Message();
                            message.what = SHOW_LOCATION;
                            message.obj = address;
                            mHandler.sendMessage(message);
                        }
                    }
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        ;
    }

    LocationListener mLocationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.i(TAG, "onStatusChanged " + provider);
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.i(TAG, "onProviderEnabled " + provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.i(TAG, "onProviderDisabled " + provider);
        }

        @Override
        public void onLocationChanged(Location location) {
            displayLocation(location);
        }
    };

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case SHOW_LOCATION:
                String currentLocation = (String) msg.obj;
                mPosition.setText(currentLocation);
                break;

            default:
                break;
            }
        }

    };
}
