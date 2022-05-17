package com.example.itube;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) { super(context, "youtube_url_db", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE USERS (USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT, FULL_NAME TEXT)";
        String CREATE_URL_TABLE = "CREATE TABLE URLS (URL_ID INTEGER PRIMARY KEY AUTOINCREMENT, USER TEXT, URL TEXT)";
        sqLiteDatabase.execSQL(CREATE_URL_TABLE);
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS USERS";
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        String DROP_URL_TABLE = "DROP TABLE IF EXISTS URLS";
        sqLiteDatabase.execSQL(DROP_URL_TABLE);

        onCreate(sqLiteDatabase);
    }

    public long insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", user.getUsername());
        contentValues.put("PASSWORD", user.getPassword());
        contentValues.put("FULL_NAME", user.getFullName());
        long row = db.insert("USERS", null, contentValues);
        db.close();
        return row;
    }

    public long insertURL(YoutubeURL youtubeURL) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USER", youtubeURL.getUser());
        contentValues.put("URL", youtubeURL.getYoutubeURL());
        long row = db.insert("URLS", null, contentValues);
        db.close();
        return row;
    }

    public boolean fetchUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("USERS", new String[]{"USER_ID"}, "USERNAME=? AND PASSWORD=?", new String[]{username, password}, null, null, null);
        int num_rows = cursor.getCount();
        cursor.close();
        db.close();
        return num_rows > 0;
    }

    public List<YoutubeURL> fetchAllURL() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM URLS";
        Cursor cursor = db.rawQuery(sql, null);

        List<YoutubeURL> youtubeURLList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                YoutubeURL youtubeURL = new YoutubeURL(cursor.getString(1), cursor.getString(2));
                youtubeURLList.add(youtubeURL);
                cursor.moveToNext();
            } while(!cursor.isAfterLast());
        }
        return youtubeURLList;
    }
}
