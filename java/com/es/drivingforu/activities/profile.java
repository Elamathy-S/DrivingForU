package com.es.drivingforu.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.es.drivingforu.R;

public class profile extends AppCompatActivity {

    String EmailHolder,PhoneNumberHolder,AddressHolder;
    TextView Email,PhoneNumber,Address;
    Button New_trip, feedback ;

    SQLiteHelper2 sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Email = (TextView)findViewById(R.id.textView1);
        New_trip = (Button)findViewById(R.id.button1);
        feedback = (Button)findViewById(R.id.button2);

        Intent intent = getIntent();

        // Receiving User Email Send By MainActivity.
        EmailHolder = intent.getStringExtra(login5.UserEmail);

        // Setting up received email to TextView.
        Email.setText(Email.getText().toString()+ EmailHolder);

        sqLiteHelper = new SQLiteHelper2(this);

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

/*        cursor = sqLiteDatabase.rawQuery("SELECT "+SQLiteHelper2.Table_Column_4_Number+" FROM "+SQLiteHelper2.TABLE_NAME+" WHERE "+SQLiteHelper2.Table_Column_2_Email+"="+EmailHolder+";", null);

        String firstName = cursor.getString(4);

        PhoneNumber.setText(firstName);
*/
        // Adding click listener to Log Out button.
        New_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(profile.this,new_trip.class);
                startActivity(i);
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(profile.this,feedback.class);
                startActivity(i);
            }
        });



    }
}
