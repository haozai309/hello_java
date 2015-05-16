package com.example.compasstest;

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

    protected static final String TAG = "test/MainActivity";
    private static final int SHOW_STANDARD = 15;

    private TextView mOrientationView;
    private SensorManager mSensorManager;
    private float[] mAccelerometerValues = new float[3];
    private float[] mMagneticValues = new float[3];
    private double[] mOrientation = new double[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOrientationView = (TextView) findViewById(R.id.orientation);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor magneticSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(mListener, magneticSensor, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(mListener, accelerometerSensor,
                SensorManager.SENSOR_DELAY_GAME);

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
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                mAccelerometerValues = event.values.clone();

            } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                mMagneticValues = event.values.clone();
                showValues(mAccelerometerValues);
            }

            float[] r = new float[9];
            float[] values = new float[3];
            SensorManager.getRotationMatrix(r, null, mAccelerometerValues, mMagneticValues);
            SensorManager.getOrientation(r, values);
            updateIfNeed(values);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Log.i(TAG, "onAccuracyChanged " + accuracy);
        }
    };

    private void updateIfNeed(float values[]) {
        Boolean haschange = false;

        StringBuffer hint = new StringBuffer("Orientation: ");
        for (int i = 0; i < values.length; i++) {
            if (Math.abs(values[i]) > SHOW_STANDARD) {
                mOrientation[i] = Math.toDegrees(values[i]);
                haschange = true;
            }
            hint.append(mOrientation[i] + ", ");
        }

        if (haschange) {
            Log.i(TAG, hint.toString());
            mOrientationView.setText(hint.toString());
        }
    }

    private void showValues(float values[]) {
        StringBuffer hint = new StringBuffer("");
        for (int i = 0; i < values.length; i++) {
            hint.append(values[i] + ", ");
        }
        Log.i(TAG, hint.toString());
    }
}
