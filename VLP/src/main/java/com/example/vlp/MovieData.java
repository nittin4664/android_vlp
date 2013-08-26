package com.example.vlp;

import static android.provider.BaseColumns._ID;
import static com.example.vlp.Constant.NAME;
import static com.example.vlp.Constant.TABLE_NAME;
import static com.example.vlp.Constant.NUMBER;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nitinku on 21/08/13.
 */
public class MovieData extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "vlp.db";
    private static final int DATABASE_VERSION = 1;
    public MovieData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + NUMBER + " TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXIST"+TABLE_NAME);
        onCreate(db);
    }
}
