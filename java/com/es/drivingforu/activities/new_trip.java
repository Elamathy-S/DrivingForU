package com.es.drivingforu.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.es.drivingforu.R;

import java.util.Calendar;

public class new_trip extends AppCompatActivity implements View.OnClickListener {

    EditText sp, ep;
    RadioGroup trip;
    RadioButton trip_type;
    TextView current_date,current_time;
    Button sd,dateButton,timeButton;
    Integer radio,mDay,mMonth,mYear,mHour,mMinute;
    String starting, ending, time, date, trip_radio, dn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

        sp = (EditText) findViewById(R.id.starting_point);
        ep = (EditText) findViewById(R.id.ending_point);
        trip = (RadioGroup) findViewById(R.id.radio_trip);
        sd = (Button) findViewById(R.id.select_driver);
        dateButton = (Button) findViewById(R.id.date_id);
        timeButton = (Button) findViewById(R.id.time_id);
        current_date = (TextView) findViewById(R.id.current_date);
        current_time = (TextView) findViewById(R.id.current_time);

        sd.setOnClickListener(this);
        dateButton.setOnClickListener(this);
        timeButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == dateButton) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            current_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (v == timeButton) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            current_time.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if  (v == sd) {
            starting = sp.getText().toString();
            ending = ep.getText().toString();
            radio = trip.getCheckedRadioButtonId();
            trip_type = (RadioButton) findViewById(radio);
            trip_radio = trip_type.getText().toString();
            date = current_date.getText().toString();
            time = current_time.getText().toString();

            Intent intent = new Intent(new_trip.this, Display_SQL.class);
            intent.putExtra("starting_key", starting);
            intent.putExtra("ending_key", ending);
            intent.putExtra("trip_radio_key", trip_radio);
            intent.putExtra("date_key", date);
            intent.putExtra("time_key", time);
            startActivity(intent);
        }
    }
}
