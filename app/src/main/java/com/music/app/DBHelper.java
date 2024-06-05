package com.music.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "MusicDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Songs(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, artist TEXT, album TEXT, year INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Songs");
        onCreate(db);
    }

    public boolean insertSong(String title, String artist, String album, int year) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("artist", artist);
        contentValues.put("album", album);
        contentValues.put("year", year);
        long result = db.insert("Songs", null, contentValues);
        return result != -1;
    }

    public Cursor getAllSongs() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Songs", null);
    }

    public boolean updateSong(int id, String title, String artist, String album, int year) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("artist", artist);
        contentValues.put("album", album);
        contentValues.put("year", year);
        db.update("Songs", contentValues, "id = ?", new String[]{String.valueOf(id)});
        return true;
    }

    public Integer deleteSong(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Songs", "id = ?", new String[]{String.valueOf(id)});
    }
}
