package com.example.topideias.internshipandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by maau_ on 10/02/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "dbpeople";
    private static final int DB_VERSION = 1;

    private static final String TABLE_CREATE_PEOPLE = "CREATE TABLE people (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " name NVARCHAR (30 ) not null, " +
            " email NVARCHAR ( 150 ) not null, " +
            " country NVARCHAR ( 50 ) not null );";

    public DBHelper(Context context){
        super (context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_PEOPLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
