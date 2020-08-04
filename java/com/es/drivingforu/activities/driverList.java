package com.es.drivingforu.activities;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.es.drivingforu.R;
import com.es.drivingforu.activities.SQLiteHelper2;
import com.es.drivingforu.model.user;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class driverList extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_list);

        db=openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

        Cursor c= db.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME,null);

        TextView v=(TextView)findViewById(R.id.v);

        c.moveToFirst();
        String temp="";
        while(! c.isAfterLast())
        {
            String s2=c.getString(0);
            String s3=c.getString(1);
            String s4=c.getString(2);
            String s5=c.getString(3);
            String s6=c.getString(4);
            String s7=c.getString(5);
            String s8=c.getString(6);

            temp=temp+"\n ID:"+s2+"\n Name:"+s3+"\nEmail:"+s4+"\nPassword:"+s5+"\nNumber:"+s6+"\nAddress"+s7+"\nRating"+s8;
            c.moveToNext();
        }
        v.setText(temp);

     
    }
}
