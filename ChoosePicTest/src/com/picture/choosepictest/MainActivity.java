package com.picture.choosepictest;

import java.io.File;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String TAG = "ChoosePicTest/MainActivity";

    private static final int TAKE_PHOTO = 1;
    private static final int CROP_PHOTO = 2;

    private Button mTakePhoto;
    private Button mCropPhoto;
    private ImageView mPicture;
    private Uri mImageUri;
    private Uri mCropImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTakePhoto = (Button) findViewById(R.id.take_photo);
        mCropPhoto = (Button) findViewById(R.id.crop_photo);
        mPicture = (ImageView) findViewById(R.id.picture);

        mImageUri = resetFile("output_image.jpg");
        mCropImageUri = resetFile("output_cropped_image.jpg");

        mTakePhoto.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });

        mCropPhoto.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(mImageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra("return-data", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mCropImageUri);
                    startActivityForResult(intent, CROP_PHOTO);
                } catch (ActivityNotFoundException anfe) {
                    String errorMessage = "Your device doesn't support the crop action!";
                    Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "requestCode is " + requestCode + ", resultCode is " + resultCode);
        switch (requestCode) {
        case TAKE_PHOTO:
            if (resultCode == RESULT_OK) {
                Log.i(TAG, "Take a photo from camera and save.");
                updateImage(mImageUri);
            }
            break;
        case CROP_PHOTO:
            if (resultCode == RESULT_OK) {
                Log.i(TAG, "Crop the image and save.");
                updateImage(mCropImageUri);
            }
            break;
        default:
            break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private Uri resetFile(String name) {
        File outputImage = new File(Environment.getExternalStorageDirectory(), name);
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Uri.fromFile(outputImage);
    }

    private void updateImage(Uri uri) {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(
                    uri));
            mPicture.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
