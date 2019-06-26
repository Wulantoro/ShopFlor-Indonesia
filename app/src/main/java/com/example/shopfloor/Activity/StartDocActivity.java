package com.example.shopfloor.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shopfloor.R;

public class StartDocActivity extends AppCompatActivity {

    private Button btnAdd_doc;
    private TextView tvwc2;
    public SharedPreferences pref, prf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_doc);

        tvwc2 = findViewById(R.id.tvwc2);


       TextView tvwc = findViewById(R.id.tvwc2);
        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        tvwc.setText(prf.getString("workcenter", null));

        btnAdd_doc = findViewById(R.id.btnAdd_doc);
        btnAdd_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref = getSharedPreferences("Workcenter", MODE_PRIVATE);
                Intent iAdd_doc = new Intent(StartDocActivity.this, Add_DocActivity.class);

                String tvwc = tvwc2.getText().toString();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("workcenter", tvwc);
                editor.commit();
                startActivity(iAdd_doc);
            }
        });
    }
}
