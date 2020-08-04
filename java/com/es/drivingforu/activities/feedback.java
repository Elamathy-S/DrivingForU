package com.es.drivingforu.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.es.drivingforu.R;

public class feedback extends AppCompatActivity {

    Button b1;
    EditText e1;
    RatingBar r1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        b1=(Button)findViewById(R.id.btnSubmit);
        e1=(EditText)findViewById(R.id.comment);
        r1=(RatingBar)findViewById(R.id.rating);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (b1.getText().toString().isEmpty()) {
                    Toast.makeText(feedback.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                } else {
                    e1.setText("");
                    r1.setRating(0);
                    Toast.makeText(feedback.this, "Thank you for sharing your feedback", Toast.LENGTH_SHORT).show();

                    Intent intent =new Intent(feedback.this,starting_acitvity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
