package com.example.deepikasto_doapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user.db";
    private static final String TABLE_NAME = "user";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "pass";
    SQLiteDatabase db;

    public static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_NAME + " text not null, " + COLUMN_EMAIL + " text not null, " +
            COLUMN_PASS + " text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase database) {
        //Create Table when oncreate gets called
        database.execSQL(DATABASE_CREATE);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //if (oldVersion > 1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        //}
    }

    public boolean addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(COLUMN_NAME, user.name);
        c.put(COLUMN_EMAIL, user.email);
        c.put(COLUMN_PASS, user.password);
        long result = db.insert(TABLE_NAME, null, c);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,// Selecting Table
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_EMAIL, COLUMN_PASS},//Selecting columns want to query
                COLUMN_EMAIL + "=?",
                new String[]{user.email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,// Selecting Table
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_EMAIL, COLUMN_PASS},//Selecting columns want to query
                COLUMN_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }
}