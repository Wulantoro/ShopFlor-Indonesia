package com.example.shopfloor.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopfloor.R;

public class OutQtyDetActivity extends AppCompatActivity {


    public SharedPreferences pref, prf;

    public String str ="";
    Character op = 'q';
    float i,num,numtemp;
    private TextView tvInputSeq1;

    private TextView tvdocnum6;
    private TextView tvinqty3;
    private TextView tvnoprod3;
    private TextView tvsequence3;
    private TextView tvseqqty2;
    private TextView tvnmprod2;
    private TextView tvOutputSeq1;
    private TextView tvworkcenter1;
    private TextView tvdocentry2;
    private TextView tvprodcode1;
    private TextView tvprodplanqty1;
    private TextView tvprodstatus0;
    private TextView tvroutecode0;
    private TextView tvroutename0;
    private TextView tvtglmulai0;
    private TextView tvjammulai0;
    private TextView tvstatus1;
    private TextView tvposted5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_qty_det);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);
        Toolbar topToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolbar);

        tvOutputSeq1 = findViewById(R.id.tvOutputSeq1);

        tvdocnum6 = findViewById(R.id.tvdocnum6);
        tvinqty3 = findViewById(R.id.tvinqty3);
        tvnoprod3 = findViewById(R.id.tvnoprod3);
        tvsequence3 = findViewById(R.id.tvsequence3);
        tvseqqty2 = findViewById(R.id.tvseqqty2);
        tvnmprod2 = findViewById(R.id.tvnmprod2);
        tvworkcenter1 = findViewById(R.id.tvworkcenter1);
        tvdocentry2 = findViewById(R.id.tvdocentry2);
        tvprodcode1 = findViewById(R.id.tvprodcode1);
        tvprodplanqty1 = findViewById(R.id.tvprodplanqty1);
        tvprodstatus0 = findViewById(R.id.tvprodstatus0);
        tvroutecode0 = findViewById(R.id.tvroutecode0);
        tvroutename0 = findViewById(R.id.tvroutename0);
        tvtglmulai0 = findViewById(R.id.tvtglmulai0);
        tvjammulai0 = findViewById(R.id.tvjammulai0);
        tvstatus1 = findViewById(R.id.tvstatus1);
        tvposted5 = findViewById(R.id.tvposted5);

        TextView tvdocnum6 = findViewById(R.id.tvdocnum6);
        prf = getSharedPreferences("docNum", MODE_PRIVATE);
        tvdocnum6.setText(prf.getString("tvnodoc", null));

        TextView tvinqty = findViewById(R.id.tvinqty3);
        prf = getSharedPreferences("inQty", MODE_PRIVATE);
        tvinqty.setText(prf.getString("tvinqty", null));

        TextView tvnoprod = findViewById(R.id.tvnoprod3);
        prf = getSharedPreferences("prodNo", MODE_PRIVATE);
        tvnoprod.setText(prf.getString("tvprodno", null));

        TextView tvsequence = findViewById(R.id.tvsequence3);
        prf = getSharedPreferences("Sequence", MODE_PRIVATE);
        tvsequence.setText(prf.getString("tvsequence", null));

        TextView tvseqqty = findViewById(R.id.tvseqqty2);
        prf = getSharedPreferences("SequenceQty", MODE_PRIVATE);
        tvseqqty.setText(prf.getString("tvseqqty", null));

        TextView tvnmprod = findViewById(R.id.tvnmprod2);
        prf = getSharedPreferences("prodName", MODE_PRIVATE);
        tvnmprod.setText(prf.getString("tvprodname", null));

        TextView tvworkcenter = findViewById(R.id.tvworkcenter1);
        prf = getSharedPreferences("workCenter", MODE_PRIVATE);
        tvworkcenter.setText(prf.getString("tvworkcenter", null));

        TextView tvdocentry = findViewById(R.id.tvdocentry2);
        prf = getSharedPreferences("docEntry", MODE_PRIVATE);
        tvdocentry.setText(prf.getString("tvdocentry", null));

        TextView tvprodcode = findViewById(R.id.tvprodcode1);
        prf = getSharedPreferences("prodCode", MODE_PRIVATE);
        tvprodcode.setText(prf.getString("tvprodcode", null));

        TextView tvprodplanqty = findViewById(R.id.tvprodplanqty1);
        prf = getSharedPreferences("prodPlanQty", MODE_PRIVATE);
        tvprodplanqty.setText(prf.getString("tvprodplanqty", null));

        TextView tvprodstatus = findViewById(R.id.tvprodstatus0);
        prf = getSharedPreferences("prodStatus", MODE_PRIVATE);
        tvprodstatus.setText(prf.getString("tvprodstatus", null));

        TextView tvroutecode = findViewById(R.id.tvroutecode0);
        prf = getSharedPreferences("routeCode", MODE_PRIVATE);
        tvroutecode.setText(prf.getString("tvroutecode", null));

        TextView tvroutename = findViewById(R.id.tvroutename0);
        prf = getSharedPreferences("routeName", MODE_PRIVATE);
        tvroutename.setText(prf.getString("tvroutename", null));

        TextView tvtglmulai = findViewById(R.id.tvtglmulai0);
        prf = getSharedPreferences("tanggalMulai", MODE_PRIVATE);
        tvtglmulai.setText(prf.getString("tvtglmulai", null));

        TextView tvjammulai = findViewById(R.id.tvjammulai0);
        prf = getSharedPreferences("jamMulai", MODE_PRIVATE);
        tvjammulai.setText(prf.getString("tvjammulai", null));

        TextView tvstatus = findViewById(R.id.tvstatus1);
        prf = getSharedPreferences("status", MODE_PRIVATE);
        tvstatus.setText(prf.getString("tvstatus", null));

        TextView tvposted = findViewById(R.id.tvposted5);
        prf = getSharedPreferences("posted", MODE_PRIVATE);
        tvposted.setText(prf.getString("tvposted", null));

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_seq && tvOutputSeq1.length() != 0) {
            pref = getSharedPreferences("inQty", MODE_PRIVATE);
            String tvinqty = tvinqty3.getText().toString();
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("tvinqty", tvinqty);
            editor.commit();

            pref = getSharedPreferences("Noprod", MODE_PRIVATE);
            String tvnoprod = tvnoprod3.getText().toString();
            SharedPreferences.Editor editor1 = pref.edit();
            editor1.putString("tvnoprod", tvnoprod);
            editor1.commit();

            pref = getSharedPreferences("outQty", MODE_PRIVATE);
            String tvoutqty = tvOutputSeq1.getText().toString();
            SharedPreferences.Editor editor2 = pref.edit();
            editor2.putString("tvoutqty", tvoutqty);
            editor2.commit();

            pref = getSharedPreferences("prodName", MODE_PRIVATE);
            String tvprodname = tvnmprod2.getText().toString();
            SharedPreferences.Editor editor3 = pref.edit();
            editor3.putString("tvprodname", tvprodname);
            editor3.commit();

            pref = getSharedPreferences("Sequence", MODE_PRIVATE);
            String tvsequence = tvsequence3.getText().toString();
            SharedPreferences.Editor editor4 = pref.edit();
            editor4.putString("tvsequence", tvsequence);
            editor4.commit();

            pref = getSharedPreferences("SequenceQty", MODE_PRIVATE);
            String tvseqqty = tvseqqty2.getText().toString();
            SharedPreferences.Editor editor5 = pref.edit();
            editor5.putString("tvseqqty", tvseqqty);
            editor5.commit();

            pref = getSharedPreferences("workCenter", MODE_PRIVATE);
            String tvworkcenter = tvworkcenter1.getText().toString();
            SharedPreferences.Editor editor6 = pref.edit();
            editor6.putString("tvworkcenter", tvworkcenter);
            editor6.commit();

            pref = getSharedPreferences("prodPlanQty", MODE_PRIVATE);
            String tvprodplanqty = tvprodplanqty1.getText().toString();
            SharedPreferences.Editor editor7 = pref.edit();
            editor7.putString("tvprodplanqty", tvprodplanqty);
            editor7.commit();

            pref = getSharedPreferences("routeCode", MODE_PRIVATE);
            String tvroutecode = tvroutecode0.getText().toString();
            SharedPreferences.Editor editor8 = pref.edit();
            editor8.putString("tvroutecode", tvroutecode);
            editor8.commit();

            pref = getSharedPreferences("routeName", MODE_PRIVATE);
            String tvroutename = tvroutename0.getText().toString();
            SharedPreferences.Editor editor9 = pref.edit();
            editor9.putString("tvroutename", tvroutename);
            editor9.commit();

            pref = getSharedPreferences("tanggalMulai", MODE_PRIVATE);
            String tvtglmulai = tvtglmulai0.getText().toString();
            SharedPreferences.Editor editor10 = pref.edit();
            editor10.putString("tvtglmulai", tvtglmulai);
            editor10.commit();

            pref = getSharedPreferences("jamMulai", MODE_PRIVATE);
            String tvjammulai = tvjammulai0.getText().toString();
            SharedPreferences.Editor editor11 = pref.edit();
            editor11.putString("tvjammulai", tvjammulai);
            editor11.commit();

            pref = getSharedPreferences("posted", MODE_PRIVATE);
            String tvposted = tvposted5.getText().toString();
            SharedPreferences.Editor editor12 = pref.edit();
            editor12.putString("tvposted", tvposted);
            editor12.commit();

            startActivity(new Intent(getApplicationContext(), CriteriaQCActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), "Output Quantity tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void btn1Clicked(View v) {
        insert(1);
    }

    public void btn2Clicked(View v) {
        insert(2);
    }
    public void btn3Clicked(View v) {
        insert(3);
    }
    public void btn4Clicked(View v) {
        insert(4);
    }
    public void btn5Clicked(View v) {
        insert(5);
    }
    public void btn6Clicked(View v) {
        insert(6);
    }
    public void btn7Clicked(View v) {
        insert(7);
    }
    public void btn8Clicked(View v) {
        insert(8);
    }
    public void btn9Clicked(View v) {
        insert(9);
    }
    public void btn0Clicked(View v) {
        insert(0);
    }
    public void btnclearClicked(View v) {
        clear();
    }

    private void clear() {
        str = "";
        num = 0;
        numtemp = 0;
        tvOutputSeq1.setText("");
    }

    private void insert(int i) {
        str = str+Integer.toString(i);
        num = Integer.valueOf(str).intValue();
        tvOutputSeq1.setText(str);
    }

}
