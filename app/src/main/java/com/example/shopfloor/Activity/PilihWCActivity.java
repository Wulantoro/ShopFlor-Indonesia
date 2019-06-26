package com.example.shopfloor.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shopfloor.Adapter.WorkcenterAdapter;
import com.example.shopfloor.Models.Workcenter;
import com.example.shopfloor.R;

public class PilihWCActivity extends AppCompatActivity {

    private TextView tvWCtampil;
    private WorkcenterAdapter adapter;
    private Workcenter workcenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_wc);

        tvWCtampil = findViewById(R.id.tvWCtampil);

        adapter = new WorkcenterAdapter(this);
        workcenter = getIntent().getParcelableExtra("key_wc");
        tvWCtampil.setText(workcenter.getCode());
    }
}
