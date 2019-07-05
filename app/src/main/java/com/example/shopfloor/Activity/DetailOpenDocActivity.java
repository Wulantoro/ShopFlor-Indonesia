package com.example.shopfloor.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.shopfloor.Adapter.OpenDocAdapter;
import com.example.shopfloor.Models.Header;
import com.example.shopfloor.R;

public class DetailOpenDocActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private TextView tvNo_doc1;
    private TextView tvNo_prod1;
    private TextView tvprod1;
    private TextView tvNm_prod1;
    private TextView tvRoute_Code1;
    private TextView tvRoute_Code2;
    private TextView tvQty_rencProd1;
    private TextView tvSts_prod1;
    private TextView tvSquence1;
    private TextView tvSquence_Qty1;
    private TextView tvShift1;
    private TextView tvTgl_mulai1;
    private TextView tvJam_mulai1;
    private TextView tvinqty4;
    private TextView tvworkcenter3;
    private TextView tvdocentry1;
    private TextView tvstatus;
    private TextView tvposted4;
    private TextView tvqcname1;
    private TextView tvusername5;
    private OpenDocAdapter adapter;
    private Header header;

    public SharedPreferences pref;
    SharedPreferences prf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_open_doc);

        tvNo_doc1 = findViewById(R.id.tvNo_doc1);
        tvNo_prod1 = findViewById(R.id.tvNo_prod1);
        tvprod1 = findViewById(R.id.tvprod1);
        tvNm_prod1 = findViewById(R.id.tvNm_prod1);
        tvRoute_Code1 = findViewById(R.id.tvRoute_Code1);
        tvRoute_Code2 = findViewById(R.id.tvRoute_Code2);
        tvQty_rencProd1 = findViewById(R.id.tvQty_rencProd1);
        tvSts_prod1 = findViewById(R.id.tvSts_prod1);
        tvSquence1 = findViewById(R.id.tvSquence1);
        tvSquence_Qty1 = findViewById(R.id.tvSquence_Qty1);
        tvShift1 = findViewById(R.id.tvShift1);
        tvTgl_mulai1 = findViewById(R.id.tvTgl_mulai1);
        tvJam_mulai1 = findViewById(R.id.tvJam_mulai1);
        tvinqty4 = findViewById(R.id.tvinqty4);
        tvworkcenter3 = findViewById(R.id.tvworkcenter3);
        tvdocentry1 = findViewById(R.id.tvdocentry1);
        tvstatus = findViewById(R.id.tvstatus);
        tvposted4 = findViewById(R.id.tvposted4);
        tvqcname1 = findViewById(R.id.tvqcname1);
        tvusername5 = findViewById(R.id.tvusername5);

        adapter = new OpenDocAdapter(this);

        //key dari opendocadapter
        header = getIntent().getParcelableExtra("key_opendoc");
        tvNo_doc1.setText(header.getDocNum());
        tvNo_prod1.setText(header.getProdNo());
        tvprod1.setText(header.getProdCode());
        tvNm_prod1.setText(header.getProdName());
        tvRoute_Code1.setText(header.getRouteCode());
        tvRoute_Code2.setText(header.getRouteName());
        tvQty_rencProd1.setText(header.getProdPlanQty());
        tvSts_prod1.setText(header.getProdStatus());
        tvSquence1.setText(header.getSequence());
        tvSquence_Qty1.setText(header.getSequenceQty());
        tvShift1.setText(header.getShift());
        tvTgl_mulai1.setText(header.getTanggalMulai().substring(0,10));
        tvJam_mulai1.setText(header.getJamMulai());
        tvinqty4.setText(header.getInQty());
        tvworkcenter3.setText(header.getWorkCenter());
        tvdocentry1.setText(String.valueOf(header.getDocEntry()));
        tvstatus.setText(header.getStatus());
        tvposted4.setText(String.valueOf(header.getPosted()));
        tvusername5.setText(header.getUserId());


        TextView tvqcname = findViewById(R.id.tvqcname1);
        prf = getSharedPreferences("Qcname", MODE_PRIVATE);
        tvqcname.setText(prf.getString("tvqcname", null));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        setTitle(null);
        Toolbar topToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolbar);


      /* tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        init();
    }

    protected void init() {
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(final ViewPager viewPager) {
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTabsFromPagerAdapter(pagerAdapter);*/
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        pref = getSharedPreferences("docNum", MODE_PRIVATE);
        String tvnodoc = tvNo_doc1.getText().toString();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("tvnodoc", tvnodoc);
        editor.commit();

        pref = getSharedPreferences("inQty", MODE_PRIVATE);
        String tvinqty = tvinqty4.getText().toString();
        SharedPreferences.Editor editor1 = pref.edit();
        editor1.putString("tvinqty", tvinqty);
        editor1.commit();

        pref = getSharedPreferences("prodNo", MODE_PRIVATE);
        String tvprodno = tvNo_prod1.getText().toString();
        SharedPreferences.Editor editor2 = pref.edit();
        editor2.putString("tvprodno", tvprodno);
        editor2.commit();

        pref = getSharedPreferences("Sequence", MODE_PRIVATE);
        String tvsequence = tvSquence1.getText().toString();
        SharedPreferences.Editor editor3 = pref.edit();
        editor3.putString("tvsequence", tvsequence);
        editor3.commit();

        pref = getSharedPreferences("SequenceQty", MODE_PRIVATE);
        String tvseqqty = tvSquence_Qty1.getText().toString();
        SharedPreferences.Editor editor4 = pref.edit();
        editor4.putString("tvseqqty", tvseqqty);
        editor4.commit();

        pref = getSharedPreferences("prodName", MODE_PRIVATE);
        String tvprodname = tvNm_prod1.getText().toString();
        SharedPreferences.Editor editor5 = pref.edit();
        editor5.putString("tvprodname", tvprodname);
        editor5.commit();

        pref = getSharedPreferences("workCenter", MODE_PRIVATE);
        String tvworkcenter = tvworkcenter3.getText().toString();
        SharedPreferences.Editor editor6 = pref.edit();
        editor6.putString("tvworkcenter", tvworkcenter);
        editor6.commit();

        pref  = getSharedPreferences("docEntry", MODE_PRIVATE);
        String tvdocentry = tvdocentry1.getText().toString();
        SharedPreferences.Editor editor7 = pref.edit();
        editor7.putString("tvdocentry", tvdocentry);
        editor7.commit();

        pref = getSharedPreferences("prodCode", MODE_PRIVATE);
        String tvprodcode = tvprod1.getText().toString();
        SharedPreferences.Editor editor8 = pref.edit();
        editor8.putString("tvprodcode", tvprodcode);
        editor8.commit();

        pref = getSharedPreferences("prodPlanQty", MODE_PRIVATE);
        String tvprodplanqty = tvQty_rencProd1.getText().toString();
        SharedPreferences.Editor editor9 = pref.edit();
        editor9.putString("tvprodplanqty", tvprodplanqty);
        editor9.commit();

        pref = getSharedPreferences("prodStatus", MODE_PRIVATE);
        String tvprodstatus = tvSts_prod1.getText().toString();
        SharedPreferences.Editor editor10 = pref.edit();
        editor10.putString("tvprodstatus", tvprodstatus);
        editor10.commit();

        pref = getSharedPreferences("routeCode", MODE_PRIVATE);
        String tvroutecode = tvRoute_Code1.getText().toString();
        SharedPreferences.Editor editor11 = pref.edit();
        editor11.putString("tvroutecode", tvroutecode);
        editor11.commit();

        pref = getSharedPreferences("routeName", MODE_PRIVATE);
        String tvroutename = tvRoute_Code2.getText().toString();
        SharedPreferences.Editor editor12 = pref.edit();
        editor12.putString("tvroutename", tvroutename);
        editor12.commit();

        pref = getSharedPreferences("tanggalMulai", MODE_PRIVATE);
        String tvtglmulai = tvTgl_mulai1.getText().toString();
        SharedPreferences.Editor editor13 = pref.edit();
        editor13.putString("tvtglmulai", tvtglmulai);
        editor13.commit();

        pref = getSharedPreferences("jamMulai", MODE_PRIVATE);
        String tvjammulai = tvJam_mulai1.getText().toString();
        SharedPreferences.Editor editor14 = pref.edit();
        editor14.putString("tvjammulai", tvjammulai);
        editor14.commit();

        pref = getSharedPreferences("status", MODE_PRIVATE);
        String tvstatusp = tvstatus.getText().toString();
        SharedPreferences.Editor editor15 = pref.edit();
        editor15.putString("tvstatus", tvstatusp);
        editor15.commit();

        pref = getSharedPreferences("posted", MODE_PRIVATE);
        String tvposted = tvposted4.getText().toString();
        SharedPreferences.Editor editor16 = pref.edit();
        editor16.putString("tvposted", tvposted);
        editor16.commit();

        pref = getSharedPreferences("Qcname", MODE_PRIVATE);
        String tvqcname = tvqcname1.getText().toString();
        SharedPreferences.Editor editor17 = pref.edit();
        editor17.putString("tvqcname", tvqcname);
        editor17.commit();

        pref = getSharedPreferences("Username", MODE_PRIVATE);
        String tvusername = tvusername5.getText().toString();
        SharedPreferences.Editor editor18 = pref.edit();
        editor18.putString("tvusername", tvusername);
        editor18.commit();

        startActivity(new Intent(getApplicationContext(), OutQtyDetActivity.class));
        return true;
    }



}
