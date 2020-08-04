package com.es.drivingforu.sql;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.es.drivingforu.model.driver;

import java.util.ArrayList;
import java.util.List;

public class databaseHelperClass2 extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "DriverManager.db";

    // User table name
    private static final String TABLE_DRIVER = "driver";

    // User Table Columns names
    private static final String COLUMN_DRIVER_ID = "user_id";
    private static final String COLUMN_DRIVER_NAME = "user_name";
    private static final String COLUMN_DRIVER_EMAIL = "user_email";
    private static final String COLUMN_DRIVER_PASSWORD = "user_password";
    private static final String COLUMN_DRIVER_NUMBER ="user_number";
    private static final String COLUMN_DRIVER_ADDRESS ="user_address";


    // create table sql query
    private String CREATE_DRIVER_TABLE = "CREATE TABLE " + TABLE_DRIVER + "("
            + COLUMN_DRIVER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DRIVER_NAME + " TEXT,"
            + COLUMN_DRIVER_EMAIL + " TEXT," + COLUMN_DRIVER_PASSWORD + " TEXT," + COLUMN_DRIVER_NUMBER +" TEXT," + COLUMN_DRIVER_ADDRESS + " TEXT" +")";

    // drop table sql query
    private String DROP_DRIVER_TABLE = "DROP TABLE IF EXISTS " + TABLE_DRIVER;

    /**
     * Constructor
     *
     * @param context
     */
    public databaseHelperClass2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DRIVER_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_DRIVER_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param driver
     */
    public void addDriver(driver driver) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DRIVER_NAME, driver.getName());
        values.put(COLUMN_DRIVER_EMAIL, driver.getEmail());
        values.put(COLUMN_DRIVER_PASSWORD, driver.getPassword());
        values.put(COLUMN_DRIVER_NUMBER, driver.getNumber());
        values.put(COLUMN_DRIVER_ADDRESS, driver.getAddress());

        // Inserting Row
        db.insert(TABLE_DRIVER, null, values);
        db.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<driver> getAllDriver() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_DRIVER_ID,
                COLUMN_DRIVER_EMAIL,
                COLUMN_DRIVER_NAME,
                COLUMN_DRIVER_PASSWORD,
                COLUMN_DRIVER_NUMBER,
                COLUMN_DRIVER_ADDRESS
        };
        // sorting orders
        String sortOrder =
                COLUMN_DRIVER_NAME + " ASC";
        List<driver> driverList = new ArrayList<driver>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_DRIVER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                driver driver = new driver();
                driver.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_DRIVER_ID))));
                driver.setName(cursor.getString(cursor.getColumnIndex(COLUMN_DRIVER_NAME)));
                driver.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_DRIVER_EMAIL)));
                driver.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_DRIVER_PASSWORD)));
                driver.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_DRIVER_NUMBER)));
                driver.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_DRIVER_ADDRESS)));
                // Adding user record to list
                driverList.add(driver);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return driverList;
    }

    /**
     * This method to update user record
     *
     * @param driver
     */
    public void updateDriver(driver driver) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DRIVER_NAME, driver.getName());
        values.put(COLUMN_DRIVER_EMAIL, driver.getEmail());
        values.put(COLUMN_DRIVER_PASSWORD, driver.getPassword());

        // updating row
        db.update(TABLE_DRIVER, values, COLUMN_DRIVER_ID + " = ?",
                new String[]{String.valueOf(driver.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param driver
     */
    public void deleteDriver(driver driver) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_DRIVER, COLUMN_DRIVER_ID + " = ?",
                new String[]{String.valueOf(driver.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkDriver(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_DRIVER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_DRIVER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_DRIVER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkDriver(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_DRIVER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_DRIVER_EMAIL + " = ?" + " AND " + COLUMN_DRIVER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_DRIVER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
