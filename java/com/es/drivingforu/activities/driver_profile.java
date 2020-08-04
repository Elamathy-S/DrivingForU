package com.es.drivingforu.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.es.drivingforu.R;

public class driver_profile extends AppCompatActivity {

    String EmailHolder,PhoneNumberHolder,AddressHolder;
    TextView Email,PhoneNumber,Address;
    Button logout;
    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile);

        Email = (TextView)findViewById(R.id.textView1);

        logout = (Button)findViewById(R.id.button1);

        Intent intent = getIntent();

        // Receiving User Email Send By MainActivity.
        EmailHolder = intent.getStringExtra(login5.UserEmail);

        // Setting up received email to TextView.
        Email.setText(Email.getText().toString()+ EmailHolder);

        sqLiteHelper = new SQLiteHelper(this);

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(driver_profile.this,starting_acitvity.class);
                startActivity(i);
            }
        });
    }
}
