package com.example.egyptimportandexport.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "name.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "orderName";
    public static final String COLUMN_HOSPITAL = "hospital";
    public static final String COLUMN_DOCTOR = "doctor";
    public static final String COLUMN_ORDER = "order";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_TIMESTRMP = "timestrap";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table orderName (id INTEGER PRIMARY KEY AUTOINCREMENT , hospital TEXT , doctor TEXT , orrder TEXT , time INTEGER )");
     }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS orderName");
        onCreate(sqLiteDatabase);
    }
}
