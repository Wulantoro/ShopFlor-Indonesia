package com.example.shopfloor.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopfloor.R;

import java.util.Calendar;

public class SuccessDocActivity extends AppCompatActivity {

    private DatePicker datePickerSuc;
    private Calendar calendarSuc;
    private int year, month, day;
    private TextView dateViewSuc;
    private Button btnCoba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_doc);

        /*btnCoba = (Button) findViewById(R.id.btnCoba);
        btnCoba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDetail = new Intent(getApplicationContext(), DetailSuccDocActivity.class);
                startActivity(iDetail);
            }
        });*/

/*******************tanggal *****************/
        dateViewSuc = (TextView) findViewById(R.id.tvtanggal1success);
        calendarSuc = Calendar.getInstance();
        year = calendarSuc.get(Calendar.YEAR);

        month = calendarSuc.get(Calendar.MONTH);
        day = calendarSuc.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
    }

    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Pilih tanggal", Toast.LENGTH_SHORT).show();

    }

    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            showDate(year, month + 1, dayOfMonth);
        }
    };

    private void showDate(int year, int month, int day) {
        dateViewSuc.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }

}

