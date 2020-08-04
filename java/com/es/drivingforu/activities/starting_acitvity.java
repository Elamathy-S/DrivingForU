package com.es.drivingforu.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.es.drivingforu.R;

public class starting_acitvity extends AppCompatActivity implements View.OnClickListener {

    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.es.drivingforu.R.layout.activity_starting_acitvity);

        b1=(Button)findViewById(R.id.user);
        b2=(Button)findViewById(R.id.driver);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == b1){
            Intent intent = new Intent(starting_acitvity.this,login5.class);
            startActivity(intent);
        }

        if(v == b2){
            Intent intent = new Intent(starting_acitvity.this,login4.class);
            startActivity(intent);
        }
    }
}
