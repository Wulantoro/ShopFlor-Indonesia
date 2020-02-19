package com.example.shopfloor.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;

import androidx.appcompat.app.AppCompatActivity;
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
    public SharedPreferences pref, prf;

    private String workcenter;
    private String username;
    private String namawc;

    private Camera mCamera;

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;


    private boolean barcodeScanned = false;
    private boolean previewing = true;

    private static String TAG = StartDocActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_doc);

        btscan = findViewById(R.id.btnscan);

        pref = getSharedPreferences("Namewc", MODE_PRIVATE);
        namawc = pref.getString("tvnamewc", null);
        Log.e(TAG, "nmworkcentot = " + namawc);

        pref = getSharedPreferences("Workcenter", MODE_PRIVATE);
        workcenter = pref.getString("workcenter0", null);
        Log.e(TAG, "workcentot = " + workcenter);

        pref = getSharedPreferences("userId", MODE_PRIVATE);
        username = pref.getString("tvuserid", null);
        Log.e(TAG, "usernametot = " + username);

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

               workcenter = pref.getString("workcenter0", null);
               SharedPreferences.Editor editor = pref.edit();
               editor.putString("workcenter1", workcenter);
               editor.commit();

                pref = getSharedPreferences("userId", MODE_PRIVATE);
                username = pref.getString("tvuserid", null);
                SharedPreferences.Editor editor1 = pref.edit();
                editor1.putString("tvuserid0", username);
                editor1.commit();

                pref = getSharedPreferences("Namewc", MODE_PRIVATE);
                namawc = pref.getString("tvnamewc", null);
                SharedPreferences.Editor editor3 = pref.edit();
                editor3.putString("tvnamewc0", namawc);
                editor3.commit();

                startActivity(iAdd_doc);
            }
        });
    }
}
