package com.example.shopfloor.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopfloor.CriteriaActivity;
import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class AddSeq1Activity extends AppCompatActivity {

    SharedPreferences prf, pref;
    Intent intent;

    public String str ="";
    Character op = 'q';
    float i,num,numtemp;
    TextView tvInputseq;
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
    private TextView tvqcname2;
    private TextView tvusername6;
    private TextView tvshift1;
    private TextView tvcodeshift2;
    private TextView tvnamawc4;
    private TextView tvid3;
    private TextView tvip8;

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;

    private static String TAG = AddSeq1Activity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_seq1);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        setTitle(null);
//        Toolbar topToolBar = findViewById(R.id.toolbar);
//        setSupportActionBar(topToolBar);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Input Quantity");

        TextView toolbarText = findViewById(R.id.toolbar_text);
        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText(getTitle());
            setSupportActionBar(toolbar);
        }

        Toolbar topToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolbar);


        tvInputSeq1 = findViewById(R.id.tvInputSeq1);

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
        tvqcname2 = findViewById(R.id.tvqcname2);
        tvusername6 = findViewById(R.id.tvusername6);
        tvshift1 = findViewById(R.id.tvshift1);
        tvcodeshift2 = findViewById(R.id.tvcodeshift2);
        tvnamawc4 = findViewById(R.id.tvnamawc4);
        tvid3 = findViewById(R.id.tvid3);
        tvip8 = findViewById(R.id.tvip8);


        TextView tvdocnum6 = findViewById(R.id.tvdocnum6);
        prf = getSharedPreferences("docNum", MODE_PRIVATE);
        tvdocnum6.setText(prf.getString("tvnodoc", null));

        TextView tvinqty = findViewById(R.id.tvInputSeq1);
        prf = getSharedPreferences("inQty", MODE_PRIVATE);
        tvinqty.setText(prf.getString("tvinqty", null).replace(".000000",""));

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
        Log.e(TAG, "prod nama" + prf.getString("tvprodname", null));

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

        TextView tvqcname = findViewById(R.id.tvqcname2);
        prf = getSharedPreferences("Qcname", MODE_PRIVATE);
        tvqcname.setText(prf.getString("tvqcname", null));

        TextView tvusername = findViewById(R.id.tvusername6);
        prf = getSharedPreferences("Username", MODE_PRIVATE);
        tvusername.setText(prf.getString("tvusername", null));

        TextView tvshift = findViewById(R.id.tvshift1);
        prf = getSharedPreferences("Shift", MODE_PRIVATE);
        tvshift.setText(prf.getString("tvshift", null));

        TextView tvcodesh = findViewById(R.id.tvcodeshift2);
        prf = getSharedPreferences("Codeshift", MODE_PRIVATE);
        tvcodesh.setText(prf.getString("tvcodeshift", null));

        TextView tvnamawc = findViewById(R.id.tvnamawc4);
        prf = getSharedPreferences("Namawc", MODE_PRIVATE);
        tvnamawc.setText(prf.getString("tvnamawc", null));

        TextView tvid = findViewById(R.id.tvid3);
        prf = getSharedPreferences("Id", MODE_PRIVATE);
        tvid.setText(String.valueOf(prf.getString("tvid", null)));

        /****************nilai input qty seq*******************/
        prf = getSharedPreferences("inQty", MODE_PRIVATE);
        prf = getSharedPreferences("Noprod", MODE_PRIVATE);

        /**************nilai output qty***********************/
        pref = getSharedPreferences("outQty", MODE_PRIVATE);

        Realm.init(getApplicationContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        serverModels = new ArrayList<>();

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c:results1) {
            text = text + c.getAddress();
        }
        tvip8.setText(text);

    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_seq && tvInputSeq1.length() != 0) {

            pref = getSharedPreferences("docNum", MODE_PRIVATE);
            String tvnodoc = tvdocnum6.getText().toString();
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("tvnodoc", tvnodoc);
            editor.commit();

            pref = getSharedPreferences("inQty", MODE_PRIVATE);
            String tvinqty = tvInputSeq1.getText().toString();
            SharedPreferences.Editor editor1 = pref.edit();
            editor1.putString("tvinqty", tvinqty);
            editor1.commit();

            pref = getSharedPreferences("prodNo", MODE_PRIVATE);
            String tvprodno = tvnoprod3.getText().toString();
            SharedPreferences.Editor editor2 = pref.edit();
            editor2.putString("tvprodno", tvprodno);
            editor2.commit();

            pref = getSharedPreferences("Sequence", MODE_PRIVATE);
            String tvsequence = tvsequence3.getText().toString();
            SharedPreferences.Editor editor3 = pref.edit();
            editor3.putString("tvsequence", tvsequence);
            editor3.commit();

            pref = getSharedPreferences("SequenceQty", MODE_PRIVATE);
            String tvseqqty = tvseqqty2.getText().toString();
            SharedPreferences.Editor editor4 = pref.edit();
            editor4.putString("tvseqqty", tvseqqty);
            editor4.commit();

            pref = getSharedPreferences("prodName", MODE_PRIVATE);
            String tvprodname = tvnmprod2.getText().toString();
            SharedPreferences.Editor editor5 = pref.edit();
            editor5.putString("tvprodname", tvprodname);
            editor5.commit();

            pref = getSharedPreferences("workCenter", MODE_PRIVATE);
            String tvworkcenter = tvworkcenter1.getText().toString();
            SharedPreferences.Editor editor6 = pref.edit();
            editor6.putString("tvworkcenter", tvworkcenter);
            editor6.commit();

            pref  = getSharedPreferences("docEntry", MODE_PRIVATE);
            String tvdocentry = tvdocentry2.getText().toString();
            SharedPreferences.Editor editor7 = pref.edit();
            editor7.putString("tvdocentry", tvdocentry);
            editor7.commit();

            pref = getSharedPreferences("prodCode", MODE_PRIVATE);
            String tvprodcode = tvprodcode1.getText().toString();
            SharedPreferences.Editor editor8 = pref.edit();
            editor8.putString("tvprodcode", tvprodcode);
            editor8.commit();

            pref = getSharedPreferences("prodPlanQty", MODE_PRIVATE);
            String tvprodplanqty = tvprodplanqty1.getText().toString();
            SharedPreferences.Editor editor9 = pref.edit();
            editor9.putString("tvprodplanqty", tvprodplanqty);
            editor9.commit();

            pref = getSharedPreferences("prodStatus", MODE_PRIVATE);
            String tvprodstatus = tvprodstatus0.getText().toString();
            SharedPreferences.Editor editor10 = pref.edit();
            editor10.putString("tvprodstatus", tvprodstatus);
            editor10.commit();

            pref = getSharedPreferences("routeCode", MODE_PRIVATE);
            String tvroutecode = tvroutecode0.getText().toString();
            SharedPreferences.Editor editor11 = pref.edit();
            editor11.putString("tvroutecode", tvroutecode);
            editor11.commit();

            pref = getSharedPreferences("routeName", MODE_PRIVATE);
            String tvroutename = tvroutename0.getText().toString();
            SharedPreferences.Editor editor12 = pref.edit();
            editor12.putString("tvroutename", tvroutename);
            editor12.commit();

            pref = getSharedPreferences("tanggalMulai", MODE_PRIVATE);
            String tvtglmulai = tvtglmulai0.getText().toString();
            SharedPreferences.Editor editor13 = pref.edit();
            editor13.putString("tvtglmulai", tvtglmulai);
            editor13.commit();

            pref = getSharedPreferences("jamMulai", MODE_PRIVATE);
            String tvjammulai = tvjammulai0.getText().toString();
            SharedPreferences.Editor editor14 = pref.edit();
            editor14.putString("tvjammulai", tvjammulai);
            editor14.commit();

            pref = getSharedPreferences("status", MODE_PRIVATE);
            String tvstatusp = tvstatus1.getText().toString();
            SharedPreferences.Editor editor15 = pref.edit();
            editor15.putString("tvstatus", tvstatusp);
            editor15.commit();

            pref = getSharedPreferences("posted", MODE_PRIVATE);
            String tvposted = tvposted5.getText().toString();
            SharedPreferences.Editor editor16 = pref.edit();
            editor16.putString("tvposted", tvposted);
            editor16.commit();

            pref = getSharedPreferences("Qcname", MODE_PRIVATE);
            String tvqcname = tvqcname2.getText().toString();
            SharedPreferences.Editor editor17 = pref.edit();
            editor17.putString("tvqcname", tvqcname);
            editor17.commit();

            pref = getSharedPreferences("Username", MODE_PRIVATE);
            String tvusername = tvusername6.getText().toString();
            SharedPreferences.Editor editor18 = pref.edit();
            editor18.putString("tvusername", tvusername);
            editor18.commit();

            pref = getSharedPreferences("Shift", MODE_PRIVATE);
            String tvshift = tvshift1.getText().toString();
            SharedPreferences.Editor editor19 = pref.edit();
            editor19.putString("tvshift", tvshift);
            editor19.commit();

            pref = getSharedPreferences("Codeshift", MODE_PRIVATE);
            String tvcodesh = tvcodeshift2.getText().toString();
            SharedPreferences.Editor editor20 = pref.edit();
            editor20.putString("tvcodeshift", tvcodesh);
            editor20.commit();

            pref = getSharedPreferences("Namawc", MODE_PRIVATE);
            String tvnamawc = tvnamawc4.getText().toString();
            SharedPreferences.Editor editor21 = pref.edit();
            editor21.putString("tvnamawc", tvnamawc);
            editor21.commit();

            pref = getSharedPreferences("Id", MODE_PRIVATE);
            String tvid = tvid3.getText().toString();
            SharedPreferences.Editor editor22 = pref.edit();
            editor22.putString("tvid", tvid);
            editor22.commit();

            startActivity(new Intent(getApplicationContext(), OutQtyDetActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), "Output Quantity tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
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
        tvInputSeq1.setText("");
    }

    private void insert(int i) {
        str = str+Integer.toString(i);
        num = Integer.valueOf(str).intValue();
        tvInputSeq1.setText(str);
    }
}
