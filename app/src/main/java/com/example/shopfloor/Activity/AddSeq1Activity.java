package com.example.shopfloor.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView tvOutputSeq1;

    private String docnum;
   private String noprod;
    private String workcenter;
    private String username;
    private String prodcode;
    private String nmprod;
    private String prodplanqty;
    private String stsprod;
    private String routecode;
    private String routname;
    private String sequence;
    private String shift;
    private String sequenceqty;
    private String tglmulai;
    private String codeshift;
    private String jammulai;
    private String docentry;
    private String posted;
    private String namawc;
    private String id;

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;

    private static String TAG = AddSeq1Activity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_seq1);

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


        pref = getSharedPreferences("docNum", MODE_PRIVATE);
        docnum = pref.getString("tvnodoc", null);
        Log.e(TAG, "docnum = " + docnum);

        TextView tvinqty = findViewById(R.id.tvInputSeq1);
        prf = getSharedPreferences("inQty", MODE_PRIVATE);
        tvinqty.setText(prf.getString("tvinqty", null).replace(".000000",""));

        pref = getSharedPreferences("prodNo", MODE_PRIVATE);
        noprod = pref.getString("tvprodno", null);
        Log.e(TAG, "prodno = " + noprod);

        pref = getSharedPreferences("Sequence", MODE_PRIVATE);
        sequence = pref.getString("tvsequence", null);
        Log.e(TAG, "sequence = " + sequence);

        pref = getSharedPreferences("SequenceQty", MODE_PRIVATE);
        sequenceqty = pref.getString("tvseqqty", null);
        Log.e(TAG, "seqqty = " + sequenceqty);

        pref = getSharedPreferences("prodName", MODE_PRIVATE);
        nmprod = pref.getString("tvprodname", null);
        Log.e(TAG, "prod nama = " + nmprod);

        pref = getSharedPreferences("workCenter", MODE_PRIVATE);
        workcenter = pref.getString("tvworkcenter", null);
        Log.e(TAG, "workcenter = " + workcenter);

        pref = getSharedPreferences("docEntry", MODE_PRIVATE);
        docentry = pref.getString("tvdocentry", null);
        Log.e(TAG, "docentry = " + docentry);

        pref = getSharedPreferences("prodCode", MODE_PRIVATE);
        prodcode = pref.getString("tvprodcode", null);
        Log.e(TAG, "prodcode = " + prodcode);

        pref = getSharedPreferences("prodPlanQty", MODE_PRIVATE);
        prodplanqty = pref.getString("tvprodplanqty", null);
        Log.e(TAG, "prodplan qty = " + prodplanqty);

        pref = getSharedPreferences("prodStatus", MODE_PRIVATE);
        stsprod = pref.getString("tvprodstatus", null);
        Log.e(TAG, "stsprod = " + stsprod);

        pref = getSharedPreferences("routeCode", MODE_PRIVATE);
        routecode = pref.getString("tvroutecode", null);
        Log.e(TAG, "routecode = " + routecode);

        pref = getSharedPreferences("routeName", MODE_PRIVATE);
        routname = pref.getString("tvroutename", null);
        Log.e(TAG, "routename = " + routname);

        pref = getSharedPreferences("tanggalMulai", MODE_PRIVATE);
        tglmulai = pref.getString("tvtglmulai", null);
        Log.e(TAG, "tgkmulai = " + tglmulai);

        pref = getSharedPreferences("jamMulai", MODE_PRIVATE);
        jammulai = pref.getString("tvjammulai", null);
        Log.e(TAG, "jam mulai = " + jammulai);

        pref = getSharedPreferences("posted", MODE_PRIVATE);
        posted = pref.getString("tvposted", null);
        Log.e(TAG, "posted = " + posted);

        pref = getSharedPreferences("Username", MODE_PRIVATE);
        username = pref.getString("tvusername", null);
        Log.e(TAG, "username = " + username);

        pref = getSharedPreferences("Shift", MODE_PRIVATE);
        shift = pref.getString("tvshift", null);
        Log.e(TAG, "shift = " + shift );

        pref = getSharedPreferences("Codeshift", MODE_PRIVATE);
        codeshift = pref.getString("tvcodeshift", null);
        Log.e(TAG, "code shifr = " + codeshift);

        pref = getSharedPreferences("Id", MODE_PRIVATE);
        id = pref.getString("tvid", null);
        Log.e(TAG, "id = " + id);

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


    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_seq && tvInputSeq1.length() != 0) {

//
            pref = getSharedPreferences("docNum", MODE_PRIVATE);
            docnum = pref.getString("tvnodoc", null);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("tvnodoc", docnum);
            editor.commit();

            pref = getSharedPreferences("inQty", MODE_PRIVATE);
            String tvinqty = tvInputSeq1.getText().toString();
            SharedPreferences.Editor editor1 = pref.edit();
            editor1.putString("tvinqty", tvinqty);
            editor1.commit();

            pref = getSharedPreferences("prodNo", MODE_PRIVATE);
            noprod = pref.getString("tvprodno", null);
            SharedPreferences.Editor editor2 = pref.edit();
            editor2.putString("tvprodno", noprod);
            editor2.commit();

            pref = getSharedPreferences("Sequence", MODE_PRIVATE);
            sequence = pref.getString("tvsequence", null);
            SharedPreferences.Editor editor3 = pref.edit();
            editor3.putString("tvsequence", sequence);
            editor3.commit();

            pref = getSharedPreferences("SequenceQty", MODE_PRIVATE);
            sequenceqty = pref.getString("tvseqqty", null);
            SharedPreferences.Editor editor4 = pref.edit();
            editor4.putString("tvseqqty", sequenceqty);
            editor4.commit();

            pref = getSharedPreferences("prodName", MODE_PRIVATE);
            nmprod = pref.getString("tvprodname", null);
            SharedPreferences.Editor editor5 = pref.edit();
            editor5.putString("tvprodname", nmprod);
            editor5.commit();

            pref = getSharedPreferences("workCenter", MODE_PRIVATE);
            workcenter = pref.getString("tvworkcenter", null);
            SharedPreferences.Editor editor6 = pref.edit();
            editor6.putString("tvworkcenter", workcenter);
            editor6.commit();

            pref  = getSharedPreferences("docEntry", MODE_PRIVATE);
            docentry = pref.getString("tvdocentry", null);
            SharedPreferences.Editor editor7 = pref.edit();
            editor7.putString("tvdocentry", docentry);
            editor7.commit();

            pref = getSharedPreferences("prodCode", MODE_PRIVATE);;
            prodcode = pref.getString("tvprodcode", null);
            SharedPreferences.Editor editor8 = pref.edit();
            editor8.putString("tvprodcode", prodcode);
            editor8.commit();

            pref = getSharedPreferences("prodPlanQty", MODE_PRIVATE);
            prodplanqty = pref.getString("tvprodplanqty", null);
            SharedPreferences.Editor editor9 = pref.edit();
            editor9.putString("tvprodplanqty", prodplanqty);
            editor9.commit();

            pref = getSharedPreferences("prodStatus", MODE_PRIVATE);
            stsprod = pref.getString("tvprodstatus", null);
            SharedPreferences.Editor editor10 = pref.edit();
            editor10.putString("tvprodstatus", stsprod);
            editor10.commit();

            pref = getSharedPreferences("routeCode", MODE_PRIVATE);
            routecode = pref.getString("tvroutecode", null);
            SharedPreferences.Editor editor11 = pref.edit();
            editor11.putString("tvroutecode", routecode);
            editor11.commit();

            pref = getSharedPreferences("routeName", MODE_PRIVATE);
            routname = pref.getString("tvroutename", null);
            SharedPreferences.Editor editor12 = pref.edit();
            editor12.putString("tvroutename", routname);
            editor12.commit();

            pref = getSharedPreferences("tanggalMulai", MODE_PRIVATE);
            tglmulai = pref.getString("tvtglmulai", null);
            SharedPreferences.Editor editor13 = pref.edit();
            editor13.putString("tvtglmulai", tglmulai);
            editor13.commit();

            pref = getSharedPreferences("jamMulai", MODE_PRIVATE);
            jammulai = pref.getString("tvjammulai", null);
            SharedPreferences.Editor editor14 = pref.edit();
            editor14.putString("tvjammulai", jammulai);
            editor14.commit();

            pref = getSharedPreferences("posted", MODE_PRIVATE);
            posted = pref.getString("tvposted", null);
            SharedPreferences.Editor editor16 = pref.edit();
            editor16.putString("tvposted", posted);
            editor16.commit();


            pref = getSharedPreferences("Username", MODE_PRIVATE);
            username = pref.getString("tvusername", null);
            SharedPreferences.Editor editor18 = pref.edit();
            editor18.putString("tvusername", username);
            editor18.commit();

            pref = getSharedPreferences("Shift", MODE_PRIVATE);
            shift = pref.getString("tvshift", null);
            SharedPreferences.Editor editor19 = pref.edit();
            editor19.putString("tvshift", shift);
            editor19.commit();

            pref = getSharedPreferences("Codeshift", MODE_PRIVATE);
            codeshift = pref.getString("tvcodeshift", null);
            SharedPreferences.Editor editor20 = pref.edit();
            editor20.putString("tvcodeshift", codeshift);
            editor20.commit();

            pref = getSharedPreferences("Id", MODE_PRIVATE);
            id = Integer.parseInt(pref.getString("tvid", null));
            SharedPreferences.Editor editor22 = pref.edit();
            editor22.putString("tvid", String.valueOf(id));
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
