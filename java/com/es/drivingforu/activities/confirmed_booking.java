package com.es.drivingforu.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.es.drivingforu.R;

public class confirmed_booking extends AppCompatActivity {

    String sp,ep,trip,date,time,driver;
    TextView t1,t2,t3,t4,t5,t6;
    Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_booking);
        t1=(TextView)findViewById(R.id.starting_point_c);
        t2=(TextView)findViewById(R.id.ending_point_c);
        t3=(TextView)findViewById(R.id.radio_trip_c);
        t4=(TextView)findViewById(R.id.date_id_c);
        t5=(TextView)findViewById(R.id.time_id_c);
        bt=(Button)findViewById(R.id.logout);

        Intent intent=getIntent();

        sp=intent.getStringExtra("starting_key");
        ep=intent.getStringExtra("ending_key");
        trip=intent.getStringExtra("trip_radio_key");
        date=intent.getStringExtra("date_key");
        time=intent.getStringExtra("time_key");


        t1.setText(t1.getText().toString()+sp);
        t2.setText(t2.getText().toString()+ep);
        t3.setText(t3.getText().toString()+trip);
        t4.setText(t4.getText().toString()+date);
        t5.setText(t5.getText().toString()+time);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(confirmed_booking.this,starting_acitvity.class);
                startActivity(i);
            }
        });

    }
}
