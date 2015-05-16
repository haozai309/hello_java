package com.example.hellolibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.hellolibrary.model.ParcelPerson;
import com.example.hellolibrary.model.Person;

public class MainActivity extends Activity implements OnClickListener {

    private static final String TAG = "test/MainActivity";
    private Button mTestSeriable;
    private Button mTestParcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTestSeriable = (Button) findViewById(R.id.test_seriable);
        mTestParcel = (Button) findViewById(R.id.test_parcel);
        mTestSeriable.setOnClickListener(this);
        mTestParcel.setOnClickListener(this);
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

    private void testSeriable() {
        Person person = new Person("Tom", 20);
        Intent intent = new Intent();
        intent.putExtra("person_data", person);
        Log.i(TAG, "person is " + person);

        Person beta = (Person) intent.getSerializableExtra("person_data");
        Log.i(TAG, "person B is " + beta);
    }

    private void testParcel() {
        ParcelPerson person = new ParcelPerson("Tom", 20);
        Intent intent = new Intent();
        intent.putExtra("person_data", person);
        Log.i(TAG, "[Parcel] person is " + person);

        ParcelPerson beta = (ParcelPerson) intent.getParcelableExtra("person_data");
        Log.i(TAG, "[Parcel] person B is " + beta);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.test_seriable) {
            testSeriable();
        } else if (v.getId() == R.id.test_parcel) {
            testParcel();
        }
    }
}
