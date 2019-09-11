package com.example.shopfloor.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class StartDocActivity extends AppCompatActivity {

    private Button btnAdd_doc, btscan;
    private TextView tvwc2;
    private TextView tvusername1;
    private TextView tvnamawc1;
    public SharedPreferences pref, prf;
    private TextView tvip3;

    private Camera mCamera;

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;


    private boolean barcodeScanned = false;
    private boolean previewing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_doc);



        tvwc2 = findViewById(R.id.tvwc2);
        tvusername1 = findViewById(R.id.tvusername1);
        btscan = findViewById(R.id.btnscan);
        tvnamawc1 = findViewById(R.id.tvnamawc1);
        tvip3 = findViewById(R.id.tvip3);

        TextView tvwcname = findViewById(R.id.tvnamawc1);
        prf = getSharedPreferences("Namewc", MODE_PRIVATE);
        tvwcname.setText(prf.getString("tvnamewc", null));

       TextView tvwc = findViewById(R.id.tvwc2);
        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        tvwc.setText(prf.getString("workcenter", null));

        TextView tvusername = findViewById(R.id.tvusername1);
        prf = getSharedPreferences("userId", MODE_PRIVATE);
        tvusername.setText(prf.getString("tvuserid", null));

//        TextView tvipadd = findViewById(R.id.tvip3);
//        prf = getSharedPreferences("Ip", MODE_PRIVATE);
//        tvipadd.setText(prf.getString("tvip", null));

        //        Setup Realm
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
        tvip3.setText(text);

        btscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScanActivity.class);
                startActivity(intent);

            }
        });

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

                pref = getSharedPreferences("userId", MODE_PRIVATE);
                String tvuserid = tvusername1.getText().toString();
                SharedPreferences.Editor editor1 = pref.edit();
                editor1.putString("tvuserid", tvuserid);
                editor1.commit();

                pref = getSharedPreferences("Namewc", MODE_PRIVATE);
                String tvwcname = tvnamawc1.getText().toString();
                SharedPreferences.Editor editor2 = pref.edit();
                editor2.putString("tvnamewc", tvwcname);
                editor2.commit();

//                pref = getSharedPreferences("Ip", MODE_PRIVATE);
//                String tvipadd = tvip3.getText().toString();
//                SharedPreferences.Editor editor3 = pref.edit();
//                editor3.putString("tvip", tvipadd);
//                editor3.commit();
                startActivity(iAdd_doc);
            }
        });
    }


}
