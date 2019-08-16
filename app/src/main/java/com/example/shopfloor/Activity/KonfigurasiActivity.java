package com.example.shopfloor.Activity;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shopfloor.R;

public class KonfigurasiActivity extends AppCompatActivity {

    private TextView tvandroidid1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfigurasi);

        tvandroidid1 = findViewById(R.id.tvandroidid1);

        tvandroidid1.setText(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
    }
}
