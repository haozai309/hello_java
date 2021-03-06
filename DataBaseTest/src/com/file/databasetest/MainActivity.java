package com.file.databasetest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

    private static final String TAG = "DatabaseTest/MainActivity";
    private static final String PATH_BOOK = "content://com.file.databasetest.provider/book";

    private Button mCreateDatabase;
    private Button mAddData;
    private Button mUpdateData;
    private Button mDeleteData;
    private Button mQueryData;
    private Button mReplaceData;

    private Button mAddBook;
    private Button mQueryBook;
    private Button mUpdateBook;
    private Button mDeleteBook;
    private String mNewId;

    private TextView mResult;
    private MyDatabaseHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 4);
        mResult = (TextView) findViewById(R.id.result);

        mCreateDatabase = (Button) findViewById(R.id.create_database);
        mAddData = (Button) findViewById(R.id.add_data);
        mUpdateData = (Button) findViewById(R.id.update_data);
        mDeleteData = (Button) findViewById(R.id.delete_data);
        mQueryData = (Button) findViewById(R.id.query_data);
        mReplaceData = (Button) findViewById(R.id.replace_data);

        mCreateDatabase.setOnClickListener(this);
        mAddData.setOnClickListener(this);
        mUpdateData.setOnClickListener(this);
        mDeleteData.setOnClickListener(this);
        mQueryData.setOnClickListener(this);
        mReplaceData.setOnClickListener(this);

        mAddBook = (Button) findViewById(R.id.add_book);
        mQueryBook = (Button) findViewById(R.id.query_book);
        mUpdateBook = (Button) findViewById(R.id.update_book);
        mDeleteBook = (Button) findViewById(R.id.delete_book);

        mAddBook.setOnClickListener(this);
        mQueryBook.setOnClickListener(this);
        mUpdateBook.setOnClickListener(this);
        mDeleteBook.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.create_database:
            mDbHelper.getWritableDatabase();
            break;

        case R.id.add_data:
            addData();
            break;

        case R.id.update_data:
            updateData();
            break;

        case R.id.delete_data:
            deleteData();
            break;

        case R.id.query_data:
            queryData();
            break;

        case R.id.replace_data:
            replaceData();
            break;

        case R.id.add_book:
            addBook();
            break;

        case R.id.query_book:
            queryBook();
            break;

        case R.id.update_book:
            updateBook();
            break;

        case R.id.delete_book:
            deleteBook();
            break;
        default:
            break;
        }
    }

    private void addData() {
        // db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
        // new String[] {"The Da Vinci Code", "Dan Brown", "454", "16.96"});
        // db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
        // new String[] {"The Lost Symbol", "Dan Brown", "510", "19.95"});
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "The Da Vinci Code");
        values.put("author", "Dan Brown");
        values.put("pages", 454);
        values.put("price", 16.96);
        db.insert("Book", null, values);
        values.clear();
        values.put("name", "The Lost Symbol");
        values.put("author", "Dan Brown");
        values.put("pages", 510);
        values.put("price", 19.95);
        db.insert("Book", null, values);
    }

    private void updateData() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price", 10.99);
        // db.execSQL("update Book set price = ? where name = ?", new String[]{"10.99",
        // "The Da Vinci Code"});
        db.update("Book", values, "name = ?", new String[] { "The Da Vinci Code" });
    }

    private void deleteData() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // db.execSQL("delete from Book where pages > ?", new String[] { "500" });
        db.delete("Book", "pages > ?", new String[] { "500" });
    }

    private void queryData() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // db.rawQuery("select * from Book", null);
        Cursor cursor = db.query("Book", null, null, null, null, null, null);
        mResult.setText("----- query data from Book -----");
        mResult.append("\nTotal count is " + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                mResult.append("\nname is " + name + "\n" + "author is " + author + "\n"
                        + "pages is " + pages + "\n" + "price is " + price);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void replaceData() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete("Book", null, null);
            Log.i(TAG, "delete table Book");
            int a = 1;
            int b = 2;
            // change to a < b, transaction will fail
            if (a > b) {
                throw new NullPointerException();
            }
            ContentValues values = new ContentValues();
            values.put("name", "Game of Thrones");
            values.put("author", "George Martin");
            values.put("pages", 720);
            values.put("price", 20.85);
            db.insert("Book", null, values);
            Log.i(TAG, "insert a record to Book");
            db.setTransactionSuccessful();
            Log.i(TAG, "setTransactionSuccessful");
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            Log.i(TAG, "endTransaction");
        }
    }

    private void addBook() {
        Uri uri = Uri.parse(PATH_BOOK);
        ContentValues values = new ContentValues();
        values.put("name", "A Clash of Kings");
        values.put("author", "George");
        values.put("pages", 1040);
        values.put("price", 22.85);
        Uri newUri = getContentResolver().insert(uri, values);
        mNewId = newUri.getPathSegments().get(1);
        Log.i(TAG, "New id is " + mNewId);
    }

    private void queryBook() {
        Uri uri = Uri.parse(PATH_BOOK);
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        mResult.setText("----- query data from Book -----");
        mResult.append("\nTotal count is " + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                mResult.append("\nname is " + name + "\n" + "author is " + author + "\n"
                        + "pages is " + pages + "\n" + "price is " + price);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void updateBook() {
        Uri uri = Uri.parse(PATH_BOOK + "/" + mNewId);
        ContentValues values = new ContentValues();
        values.put("name", "A story of Swords");
        values.put("pages", 1216);
        values.put("price", 24.05);
        getContentResolver().update(uri, values, null, null);
    }

    private void deleteBook() {
        Uri uri = Uri.parse(PATH_BOOK + "/" + mNewId);
        getContentResolver().delete(uri, null, null);
    }
}
