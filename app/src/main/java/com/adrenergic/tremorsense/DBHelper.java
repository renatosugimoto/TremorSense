package com.adrenergic.tremorsense;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    //Must increment version if schema is changed
    public static int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "userData.db";
    public static final String DATA_TABLE_NAME = "tremordata";
    public static final String DATA_COLUMN_DATE = "date";
    public static final String DATA_COLUMN_TIME = "time";
    public static final String DATA_COLUMN_NOTE = "note";
    public static final String DATA_COLUMN_RESTING = "resting";
    public static final String DATA_COLUMN_POSTURE = "posture";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA = ",";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + DATA_TABLE_NAME + " (" + DATA_COLUMN_DATE + " DATE," + DATA_COLUMN_TIME + " TIME," + DATA_COLUMN_NOTE + TEXT_TYPE + COMMA + DATA_COLUMN_RESTING + TEXT_TYPE + COMMA + DATA_COLUMN_POSTURE + TEXT_TYPE + " )";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DATA_TABLE_NAME;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Give up.
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Cry forever.
    }
}
