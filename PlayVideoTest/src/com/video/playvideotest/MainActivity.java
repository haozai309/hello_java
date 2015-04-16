package com.video.playvideotest;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends Activity implements OnClickListener {

    private VideoView mVideoView;
    private Button mPlay;
    private Button mPause;
    private Button mReplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoView = (VideoView) findViewById(R.id.video_view);
        mPlay = (Button) findViewById(R.id.play);
        mPause = (Button) findViewById(R.id.pause);
        mReplay = (Button) findViewById(R.id.replay);
        mPlay.setOnClickListener(this);
        mPause.setOnClickListener(this);
        mReplay.setOnClickListener(this);
        initVideoPath();
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
        if (mVideoView != null) {
            mVideoView.suspend();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.play:
            if (!mVideoView.isPlaying()) {
                mVideoView.start();
            }
            break;
        case R.id.pause:
            if (mVideoView.isPlaying()) {
                mVideoView.pause();
            }
            break;
        case R.id.replay:
            if (mVideoView.isPlaying()) {
                mVideoView.resume();
            }
            break;
        default:
            break;
        }
    }

    private void initVideoPath() {
        File file = new File(Environment.getExternalStorageDirectory(),
                "source_rcs/iphone6_leak.mp4");
        mVideoView.setVideoPath(file.getPath());
    }
}
