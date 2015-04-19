package com.example.locationtest;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
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

    private TextView mPosition;
    private Button mGetPosition;
    private String mProvider;
    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mPosition = (TextView) findViewById(R.id.position_text_view);
        mGetPosition = (Button) findViewById(R.id.get_location);
        mGetPosition.setOnClickListener(this);
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
            Location location = mLocationManager.getLastKnownLocation(mProvider);
            Log.i(TAG, "location " + mProvider);
            if (location != null) {
                showLocation(location);
            }
            mLocationManager.requestLocationUpdates(mProvider, 5000, 1, mLocationListener);
            break;

        default:
            break;
        }

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
            showLocation(location);
        }
    };

    private void showLocation(Location location) {
        String currentLocation = "latitude is " + location.getLatitude() + ", longitude "
                + location.getLongitude();
        Log.i(TAG, "currentLocation is " + currentLocation);
        mPosition.setText(currentLocation);
    }
}
