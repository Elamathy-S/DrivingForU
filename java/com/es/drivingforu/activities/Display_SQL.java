package com.es.drivingforu.activities;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.es.drivingforu.R;
import com.es.drivingforu.model.driver;


public class Display_SQL extends AppCompatActivity {

    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter ;
    ListView LISTVIEW;
    String sp,ep,trip,date,time,selectedItem,notify1;

    ArrayList<String> ID_Array;
    ArrayList<String> NAME_Array;
    ArrayList<String> PHONE_NUMBER_Array;
   // ArrayList<String> RATING_Array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__sql);

        LISTVIEW = (ListView) findViewById(R.id.listView1);

        ID_Array = new ArrayList<String>();

        NAME_Array = new ArrayList<String>();

        PHONE_NUMBER_Array = new ArrayList<String>();

//        RATING_Array = new ArrayList<String>();

        sqLiteHelper = new SQLiteHelper(this);

        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i =new Intent(Display_SQL.this, confirmed_booking.class);
                selectedItem = (String) parent.getItemAtPosition(position);

                i.putExtra("driver_name",selectedItem);
                i.putExtra("starting_key", sp);
                i.putExtra("ending_key", ep);
                i.putExtra("trip_radio_key", trip);
                i.putExtra("date_key", date);
                i.putExtra("time_key", time);
                startActivity(i);

                //Getting intent and PendingIntent instance
/*                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                //Get the SmsManager instance and call the sendTextMessage method to send message
                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(number, null, "hello javatpoint", pi,null);*/


                notify1="Trip: "+sp+" - "+ep+"\nDate: "+date+"\nTime: "+time+"\nEnjoy your trip!";

                NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify=new Notification.Builder
                        (getApplicationContext()).setContentTitle("Driving for u").setContentText("Driving For U").
                        setContentTitle(notify1).setSmallIcon(R.drawable.new_trip).build();

                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                notif.notify(0, notify);

            }
        });

        Intent intent=getIntent();

        sp=intent.getStringExtra("starting_key");
        ep=intent.getStringExtra("ending_key");
        trip=intent.getStringExtra("trip_radio_key");
        date=intent.getStringExtra("date_key");
        time=intent.getStringExtra("time_key");
    }


    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME+"", null);

        ID_Array.clear();
        NAME_Array.clear();
        PHONE_NUMBER_Array.clear();
//        RATING_Array.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));

                NAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name)));

                PHONE_NUMBER_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_4_Number)));

//                RATING_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_6_Rating)));

            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter(Display_SQL.this,

                ID_Array,
                NAME_Array,
                PHONE_NUMBER_Array
//                RATING_Array
        );

        LISTVIEW.setAdapter(listAdapter);

        cursor.close();
    }
}