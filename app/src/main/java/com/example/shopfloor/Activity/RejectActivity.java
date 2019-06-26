package com.example.shopfloor.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shopfloor.Fragment.RejectListFragment;
import com.example.shopfloor.R;

public class RejectActivity extends AppCompatActivity {
    SharedPreferences prf;
    private Button btnFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reject);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);
        Toolbar topToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        /*****************qtyin**********************/
        TextView inqty = findViewById(R.id.tvInputQty1);
        prf = getSharedPreferences("inQty", MODE_PRIVATE);
        inqty.setText(prf.getString("inqty", null));

        /*****************************qtyout***************************/
        TextView outqty = findViewById(R.id.tvOutputQty1);
        prf = getSharedPreferences("outQty", MODE_PRIVATE);
        outqty.setText(prf.getString("outqty", null));

        /************************workcenter*******************************/
        TextView workcenter = findViewById(R.id.tvWorkcenter);
        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        workcenter.setText(prf.getString("workcenter", null));

        /*********************noprod****************************************/
        TextView tvnoprod = findViewById(R.id.tvnoprod1);
        prf = getSharedPreferences("Noprod", MODE_PRIVATE);
        tvnoprod.setText(prf.getString("tvnoprod", null));

        /********************************nmprod*******************************/
        TextView tvnmprod = findViewById(R.id.tvnmprod1);
        prf = getSharedPreferences("Nmprod", MODE_PRIVATE);
        tvnmprod.setText(prf.getString("tvnmprod", null));

        /****************************sequence************************************/
        TextView tvsequence = findViewById(R.id.tvsequence1);
        prf = getSharedPreferences("Sequence", MODE_PRIVATE);
        tvsequence.setText(prf.getString("tvsequence", null));

        /***************************sequenceqty**********************************/
        TextView tvsequenceqty = findViewById(R.id.tvseqqty1);
        prf = getSharedPreferences("SequenceQty", MODE_PRIVATE);
        tvsequenceqty.setText(prf.getString("tvsequenceqty", null));

        /*******************tglsel*************************************************/
        TextView tglsel = findViewById(R.id.tvtglsel1);
        prf = getSharedPreferences("Tglsel", MODE_PRIVATE);
        tglsel.setText(prf.getString("tglsel", null));

        /******************************jamsel*********************************/
        TextView jamsel = findViewById(R.id.tvjamsel1);
        prf = getSharedPreferences("Jamsel", MODE_PRIVATE);
        jamsel.setText(String.valueOf(prf.getString("jamsel", null)));
       Log.e("jam selesai = ", String.valueOf(prf.getString("jamsel", null)));

       btnFrag = findViewById(R.id.btnFrag);
       btnFrag.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(RejectActivity.this, RespRejectFragActivity.class);
               startActivity(intent);
           }
       });
    }

}
