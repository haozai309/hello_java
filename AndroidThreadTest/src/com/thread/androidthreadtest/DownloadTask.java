package com.thread.androidthreadtest;

import android.os.AsyncTask;
import android.util.Log;

/*
 * This class show usage of AsyncTask, to start the task, call downloadTask.execute()
 */
public class DownloadTask extends AsyncTask<Void, Integer, Boolean> {

    private static final String TAG = "ThreadTest/DownloadTask";

    @Override
    protected void onPreExecute() {
        Log.i(TAG, "[onPreExecute] show progress dialog");
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            while (true) {
                int downloadPercent = doDownload();
                publishProgress(downloadPercent);
                if (downloadPercent >= 100) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private int doDownload() {
        return 0;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.i(TAG, "[onProgressUpdate] progressis " + values[0]);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        Log.i(TAG, "[onPostExecute]");
        if (result) {
            Log.i(TAG, "Downloaded success");
        } else {
            Log.i(TAG, "Downloaded failed");
        }
    }

}
