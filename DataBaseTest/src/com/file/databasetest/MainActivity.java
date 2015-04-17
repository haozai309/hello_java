package com.file.databasetest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

    private Button mCreateDatabase;
    private Button mAddData;
    private Button mUpdateData;
    private Button mDeleteData;
    private Button mQueryData;
    private TextView mResult;
    private MyDatabaseHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
        mCreateDatabase = (Button) findViewById(R.id.create_database);
        mAddData = (Button) findViewById(R.id.add_data);
        mUpdateData = (Button) findViewById(R.id.update_data);
        mDeleteData = (Button) findViewById(R.id.delete_data);
        mQueryData = (Button) findViewById(R.id.query_data);
        mResult = (TextView) findViewById(R.id.result);

        mCreateDatabase.setOnClickListener(this);
        mAddData.setOnClickListener(this);
        mUpdateData.setOnClickListener(this);
        mDeleteData.setOnClickListener(this);
        mQueryData.setOnClickListener(this);
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
                mResult.append("\nname is " + name + "\n"
                                + "author is " + author + "\n"
                                + "pages is " + pages + "\n"
                                + "price is " + price);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
