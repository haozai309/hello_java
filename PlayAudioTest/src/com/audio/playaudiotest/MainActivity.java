package com.audio.playaudiotest;

import java.io.File;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    private static final String TAG = "PlayAudioTest/MainActivity";
    private Button mPlay;
    private Button mPause;
    private Button mStop;
    private MediaPlayer mMediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlay = (Button) findViewById(R.id.play);
        mPause = (Button) findViewById(R.id.pause);
        mStop = (Button) findViewById(R.id.stop);
        mPlay.setOnClickListener(this);
        mPause.setOnClickListener(this);
        mStop.setOnClickListener(this);
        initMediaPlayer();
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
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.play:
            if (!mMediaPlayer.isPlaying()) {
                mMediaPlayer.start();
            }
            break;
        case R.id.pause:
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
            }
            break;
        case R.id.stop:
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.reset();
                initMediaPlayer();
            }
            break;
        default:
            break;
        }
    }

    private void initMediaPlayer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(),
                    "source_rcs/zhainan-222s.mp3");
            Log.i(TAG, "File path is " + file.getAbsolutePath());
            Log.i(TAG, "File exist " + file.exists());
            mMediaPlayer.setDataSource(file.getPath());
            mMediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
