package com.file.sharedpreferencestest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Button mSaveData;
    private Button mRestoreData;
    private TextView mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mData = (TextView) findViewById(R.id.data);
        mSaveData = (Button) findViewById(R.id.save_data);
        mRestoreData = (Button) findViewById(R.id.restore_data);

        mSaveData.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mData.setText("---- Save data ----");
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 28);
                editor.putBoolean("married", false);
                editor.commit();
            }
        });
        mRestoreData.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mData.setText("---- Restore data ----");
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                String name = pref.getString("name", "");
                int age = pref.getInt("age", 0);
                Boolean married = pref.getBoolean("married", false);
                String content = "name is " + name + "\n"
                            + "age is " + age + "\n"
                            + "married is " + married;
                mData.append("\n" + content);
            }
        });
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
}
