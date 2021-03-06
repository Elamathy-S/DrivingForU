package com.es.drivingforu.activities;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteHelper2 extends SQLiteOpenHelper {

    static String DATABASE_NAME="UserDatabase";

    public static final String TABLE_NAME="UserTable";

    public static final String Table_Column_ID="id";

    public static final String Table_Column_1_Name="name";

    public static final String Table_Column_2_Email="email";

    public static final String Table_Column_3_Password="password";

    public static final String Table_Column_4_Number="number";

    public static final String Table_Column_5_Address="address";

    public SQLiteHelper2(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_Email+" VARCHAR, "+Table_Column_3_Password+" VARCHAR, "+Table_Column_4_Number+" VARCHAR, "+Table_Column_5_Address+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    public void onDisplay(SQLiteDatabase database) {
        String DISPLAY_TABLE="SELECT * FROM "+TABLE_NAME;
        database.execSQL(DISPLAY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

}