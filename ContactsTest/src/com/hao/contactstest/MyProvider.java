package com.hao.contactstest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyProvider extends ContentProvider {

    public static final int TABLE_DIR = 0;
    public static final int TABLE_ITEM = 1;
    public static final int TABLE2_DIR = 2;
    public static final int TABLE2_ITEM = 3;
    private static UriMatcher sUriMatcher;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI("com.hao.contactstest.provider", "table1", TABLE_DIR);
        sUriMatcher.addURI("com.hao.contactstest.provider", "table1/#", TABLE_ITEM);
        sUriMatcher.addURI("com.hao.contactstest.provider", "table2", TABLE2_DIR);
        sUriMatcher.addURI("com.hao.contactstest.provider", "table2/#", TABLE2_ITEM);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        switch (sUriMatcher.match(uri)) {
        case TABLE_DIR:
            break;

        case TABLE_ITEM:
            break;

        case TABLE2_DIR:
            break;

        case TABLE2_ITEM:
            break;

        default:
            break;
        }
        return null;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
        case TABLE_DIR:
            return "vnd.android.cursor.dir/vnd.com.hao.contacts.provider.table1";

        case TABLE_ITEM:
            return "vnd.android.cursor.item/vnd.com.hao.contacts.provider.table1";

        case TABLE2_DIR:
            return "vnd.android.cursor.dir/vnd.com.hao.contacts.provider.table2";

        case TABLE2_ITEM:
            return "vnd.android.cursor.item/vnd.com.hao.contacts.provider.table2";

        default:
            break;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

}
