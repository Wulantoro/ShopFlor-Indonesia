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
    private TextView tvdocentry0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reject);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);
        Toolbar topToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        tvdocentry0 = findViewById(R.id.tvdocentry0);

        TextView tvdocentry = findViewById(R.id.tvdocentry0);
        prf = getSharedPreferences("Docentry", MODE_PRIVATE);
        tvdocentry.setText(String.valueOf(prf.getString("tvdocentry", null)));
        Log.e("docemtry", prf.getString("tvdocentry", null));

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
        prf = getSharedPreferences("Namaprod", MODE_PRIVATE);
        tvnmprod.setText(prf.getString("tvnamaprod", null));

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
        tglsel.setText(prf.getString("tvtglsel", null));

        /******************************jamsel*********************************/
        TextView jamsel = findViewById(R.id.tvjamsel1);
        prf = getSharedPreferences("Jamsel", MODE_PRIVATE);
        jamsel.setText(String.valueOf(prf.getString("tvjamsel", null)));
       Log.e("jam selesai = ", String.valueOf(prf.getString("jamsel", null)));

       TextView tvdocsts = findViewById(R.id.tvdocsts2);
       prf = getSharedPreferences("Docsts", MODE_PRIVATE);
       tvdocsts.setText(prf.getString("tvdocsts", null));

       TextView tvinqty = findViewById(R.id.tvInputQty1);
       prf = getSharedPreferences("Inqty", MODE_PRIVATE);
       tvinqty.setText(prf.getString("tvinqty", null));

       TextView tvoutqty = findViewById(R.id.tvOutputQty1);
       prf = getSharedPreferences("Outqty", MODE_PRIVATE);
       tvoutqty.setText(prf.getString("tvoutqty", null));

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
