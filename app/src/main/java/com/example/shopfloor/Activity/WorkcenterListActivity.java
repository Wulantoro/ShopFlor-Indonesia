package com.example.shopfloor.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.WorkcenterAdapter;
import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.Models.Workcenter;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.GlobalVars;
import com.example.shopfloor.Utils.RealmHelper;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class WorkcenterListActivity extends AppCompatActivity {

    private RecyclerView rv;
    private WorkcenterAdapter adapter;
    private Gson gson;
    private List<Workcenter> list;
    private Workcenter workcenter;
    private EditText search;
    private TextView tvCodeWC;
    private SharedPreferences pref, prf;
    private TextView tvip2;

    private Context context;

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workcenter_list);


        gson = new Gson();
        list = new ArrayList<Workcenter>();
        rv = findViewById(R.id.rvWorkcenterList);
        tvip2 = findViewById(R.id.tvip2);

//        TextView tvipadd = findViewById(R.id.tvip2);
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
        tvip2.setText(text);

        /*****tambahan*******/
        search = (EditText) findViewById(R.id.search);
        rv = (RecyclerView) findViewById(R.id.rvWorkcenterList);
        rv.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        // rv.setItemAnimator(new DefaultItemAnimator());
        loadData();

        adapter = new WorkcenterAdapter(list,this);
        rv.setAdapter(adapter);

        addTextListener();

    }

    private void loadData() {

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Sedang Proses");
        progress.setTitle("Sedang Proses");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        if (adapter != null)
            adapter.clearAll();

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c:results1) {
            text = text + c.getAddress();


//        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/workcenter")
//        AndroidNetworking.get(prf.getString("tvip", null) + "index.php/workcenter")
            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/workcenter")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        public void onResponse(JSONObject response) {
                            List<Workcenter> results = new ArrayList<>();
                            try {
                                Log.e("resp", response.toString(1));

                                if (results != null)
                                    results.clear();

                                String message = response.getString("message");

                                if (message.equals("Workcenter were found")) {
                                    String records = response.getString("data");

                                    JSONArray dataArr = new JSONArray(records);

                                    if (dataArr.length() > 0) {

                                        for (int i = 0; i < dataArr.length(); i++) {
//                                        System.out.println("res "+dataArr.getJSONObject(i).toString());\

                                            Workcenter workcenter = gson.fromJson(dataArr.getJSONObject(i).toString(), Workcenter.class);
                                            results.add(workcenter);
                                        }
                                    }
                                }

                                progress.dismiss();

                            } catch (JSONException e) {
                                e.printStackTrace();

                                progress.dismiss();

                            }

                            adapter.addAll(results);
                        }

                        public void onError(ANError anError) {
                            progress.dismiss();
                        }
                    });
        }
    }

    public void addTextListener() {


        search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<Workcenter> filteredList = new ArrayList<>();

                for (int i = 0; i < list.size(); i++) {

                     final String text = list.get(i).getCode().toLowerCase();
                    if (text.contains(query.toString())) {

                        filteredList.add(list.get(i));
                        /********************untuk cek value yang di search***************/
                /*Log.e("aaaa ", "aaaaa " + String.valueOf(list.size()));
                for(Workcenter wc:list){
                    Log.e("respon", wc.getName());

                    if(wc.getName().contains(query.toString())){
                        filteredList.add(wc);*/
                /**************************************************************************/

                    }
                }

                rv.setLayoutManager(new LinearLayoutManager(WorkcenterListActivity.this));
                adapter = new WorkcenterAdapter(filteredList, WorkcenterListActivity.this);
                rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        }
}
