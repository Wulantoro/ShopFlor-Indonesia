package com.example.shopfloor.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopfloor.Adapter.ServerAdapter;
import com.example.shopfloor.MainActivity;
import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class KonfigurasiActivity extends AppCompatActivity {

    private TextView tvandroidid1;
    private EditText tvip;
    private Button btnsimpanip;
    String sid;
    String saddress;
    Realm realm;
    RealmHelper realmHelper;
    RecyclerView recyclerView;
    ServerAdapter serverAdapter;
    List<ServerModel> serverModels;
    ServerModel serverModel;
    String address;
    Integer id;
    private Button btnhapus;
    private SharedPreferences pref, prf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfigurasi);

        tvandroidid1 = findViewById(R.id.tvandroidid1);
        tvip = findViewById(R.id.tvip);
        btnsimpanip = findViewById(R.id.btnsimpanip);
        btnhapus = findViewById(R.id.btnhapus);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Setup Realm
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);


        serverModels = new ArrayList<>();

        serverModels = realmHelper.getAllAddress();

        show();

        tvandroidid1.setText(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
//
        TextView tvipadd = findViewById(R.id.tvip);
        prf = getSharedPreferences("Ip", MODE_PRIVATE);
        tvipadd.setText(prf.getString("tvip", null));

        btnsimpanip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    saddress = tvip.getText().toString();
                    serverModel = new ServerModel();
                    serverModel.setAddress(saddress);
                    realmHelper = new RealmHelper(realm);
                    realmHelper.save(serverModel);
                    Toasty.success(getApplicationContext(), "Berhasil Disimpan!", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realmHelper.delete();

                Toasty.success(getApplicationContext(), "Berhasil dihapus", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onRestart() {
        List<ServerModel> moveResults = new ArrayList<>();
        super.onRestart();
        serverAdapter.notifyDataSetChanged();

        show();
    }

    public void show(){
        serverAdapter = new ServerAdapter(this, serverModels);
        recyclerView.setAdapter(serverAdapter);



    }
}
