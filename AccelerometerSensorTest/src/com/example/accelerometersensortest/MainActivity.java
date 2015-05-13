package com.example.accelerometersensortest;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final String TAG = "test/MainActivity";
    private static final int SHOW_STANDARD = 15;

    private float[] mMaxAccermeter = { 0, 0, 0 };

    private SensorManager mSensorManager;
    private TextView mAccelerometerValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAccelerometerValue = (TextView) findViewById(R.id.accelerometer_value);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(mListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
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
        super.onDestroy();
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(mListener);
        }
    }

    private SensorEventListener mListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            Boolean haschange = false;

            StringBuffer hint = new StringBuffer("Shake: ");
            for (int i = 0; i < mMaxAccermeter.length; i++) {
                if (Math.abs(event.values[i]) > SHOW_STANDARD) {
                    mMaxAccermeter[i] = Math.abs(event.values[i]);
                    haschange = true;
                }
                hint.append(mMaxAccermeter[i] + ", ");
            }

            if (haschange) {
                Log.i(TAG, hint.toString());
                mAccelerometerValue.setText(hint.toString());
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Log.i(TAG, "onAccuracyChanged " + accuracy);
        }
    };
}
