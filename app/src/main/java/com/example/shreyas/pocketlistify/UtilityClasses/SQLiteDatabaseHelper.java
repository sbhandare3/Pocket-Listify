package com.example.shreyas.pocketlistify.UtilityClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by shreyas on 8/11/2017.
 */

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TaskData.db";
    public static final String TABLE_NAME = "TaskInfo";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "Name" ;
    public static final String COLUMN_DETAIL =  "Detail";
    public static final String COLUMN_DATE =  "Date";
    public static final String COLUMN_PRIORITY =  "Priority";

    public SQLiteDatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);

    }

    // OnCreate method. Called when helper is created for the first time.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Note: do not use AUTO INCREMENT on a primary key integer
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                COLUMN_NAME + " TEXT, " + COLUMN_DETAIL + " TEXT, " + COLUMN_DATE + " TEXT, " + COLUMN_PRIORITY + " TEXT);");
        System.out.println("Database is created");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    // Custom method to insert stuff
    public void insertEntry(String name, String detail, String date, String priority){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(COLUMN_NAME,name);
        content.put(COLUMN_DETAIL,detail);
        content.put(COLUMN_DATE,date);
        content.put(COLUMN_PRIORITY,priority);
        db.insert(TABLE_NAME,null,content);
    }

    // Custom method to return entries ascending
    public Cursor getEntries(){
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_DATE + " DESC",null);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " ASC",null);
        return cursor;
    }

    // get today's task count
    public int getTodaysCount(){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy", Locale.US);
        String todayDate = df.format(date);
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "Date = ?";
        String[] selectionArgs = {todayDate};
        Cursor c = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        int result = c.getCount();
        c.close();
        return result;
    }

    // Custom method to return entries descending
    public Cursor getLatestFirstEntries(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " DESC",null);
        return cursor;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public boolean updateData(int pos, String name, String detail, String date, String priority){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,name);
        contentValues.put(COLUMN_DETAIL,detail);
        contentValues.put(COLUMN_DATE,date);
        contentValues.put(COLUMN_PRIORITY,priority);
        db.update(TABLE_NAME,contentValues,"id = ?",new String[] {Integer.toString(pos)});
        return true;
    }
}
