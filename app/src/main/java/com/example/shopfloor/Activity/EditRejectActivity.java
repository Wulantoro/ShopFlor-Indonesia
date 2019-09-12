package com.example.shopfloor.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.EditRejectAdapter;
import com.example.shopfloor.Adapter.InputRejectAdapter;
import com.example.shopfloor.Adapter.RejectAdapter;
import com.example.shopfloor.Models.InputReject;
import com.example.shopfloor.Models.Reject;
import com.example.shopfloor.Models.ServerModel;
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

public class EditRejectActivity extends AppCompatActivity {

    public static TextView tvReject2;
    public TextView tvdocentry4;
    public TextView tvlinenumb0;
    public static TextView tvcodereject0;
    public static TextView tvReject3;
    public static TextView tvid;
    public  static TextView tvhosthed;
    private TextView tvip15;
    Button btnReject;
    Button btnSimpan;
    public  static TextView tvRejectQty;
    Gson gson;
    RejectAdapter rejectAdapter;

    private InputRejectAdapter inputRejectAdapter;
    private InputReject inputReject;
    private EditRejectAdapter editRejectAdapter;

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;

    public static Dialog dialog;

    public String str ="";
    float i,num,numtemp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reject_edit);

        inputRejectAdapter = new InputRejectAdapter(this);
        rejectAdapter = new RejectAdapter(this);
        editRejectAdapter = new EditRejectAdapter(this);

        tvReject3 = findViewById(R.id.tvReject3);
        tvdocentry4 = findViewById(R.id.tvdocentry4);
        tvlinenumb0 = findViewById(R.id.tvlinenumb0);
        tvcodereject0 = findViewById(R.id.tvcodereject0);
        tvReject3 = findViewById(R.id.tvReject3);
        btnReject = findViewById(R.id.btnReject);
        btnSimpan = findViewById(R.id.btnSimpan);
        tvRejectQty = findViewById(R.id.tvRejectQty);
        tvid = findViewById(R.id.tvid);
        tvhosthed = findViewById(R.id.tvhosthed);
        tvip15 = findViewById(R.id.tvip15);

        inputReject = getIntent().getParcelableExtra("edit");
        tvRejectQty.setText(inputReject.getRejectQty().replace(".000000", ""));
        tvdocentry4.setText(String.valueOf(inputReject.getDocEntry()));
        tvlinenumb0.setText(String.valueOf(inputReject.getLineNumber()));
        tvcodereject0.setText(inputReject.getRejectCode());
        tvReject3.setText(inputReject.getRejectName());
        tvid.setText(String.valueOf(inputReject.getId()));
        tvhosthed.setText(String.valueOf(inputReject.getHostHeadEntry()));

        Realm.init(getApplicationContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        serverModels = new ArrayList<>();

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results) {
            text = text + c.getAddress();
        }
        tvip15.setText(text);


        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReject();
            }
        });
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editReject();
                Intent intent = new Intent(getApplicationContext(), RejectActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showReject() {
        dialog = new Dialog(EditRejectActivity.this);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_reject_list);

        gson = new Gson();

        final RecyclerView rv1 = dialog.findViewById(R.id.rvRejectFrag);
        final EditText searchRej = dialog.findViewById(R.id.searchRej);

        final List<Reject> list = editRejectAdapter.getList_item();

        loadData(editRejectAdapter);
        rv1.setAdapter(editRejectAdapter);
        rv1.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv1.setAdapter(editRejectAdapter);

        searchRej.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {
                query = query.toString().toLowerCase();

                final List<Reject> filteredList = new ArrayList<>();

                if (list != null & list.size() > 0) {

                    for (int i = 0; i < list.size(); i++) {
                        final String text = String.valueOf(list.get(i).getName().toLowerCase());
                        if (text.contains(query.toString())) {
                            filteredList.add(list.get(i));
                        }
                    }

                    rv1.setLayoutManager(new LinearLayoutManager(EditRejectActivity.this));
                    editRejectAdapter = new EditRejectAdapter(filteredList, EditRejectActivity.this);
                    rv1.setAdapter(editRejectAdapter);
                    editRejectAdapter.notifyDataSetChanged();
                }

            }
        });

        rv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dialog.show();
    }

    public void loadData(final EditRejectAdapter adapter) {
        if (rejectAdapter != null)
            rejectAdapter.clearAll();

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results) {
            text = text + c.getAddress();


//        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/reject")
            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/reject")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            List<Reject> result = new ArrayList<>();
                            try {
                                Log.e("Reject", response.toString(1));
                                if (result != null)
                                    result.clear();

                                String mesage = response.getString("message");

                                if (mesage.equals("Reject were found")) {
                                    String records = response.getString("data");

                                    JSONArray dataarr = new JSONArray(records);
                                    if (dataarr.length() > 0) {
                                        for (int i = 0; i < dataarr.length(); i++) {
                                            Reject reject = gson.fromJson(dataarr.getJSONObject(i).toString(), Reject.class);
                                            result.add(reject);
                                        }
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter.addAll(result);
                        }

                        @Override
                        public void onError(ANError anError) {

                        }
                    });
        }
    }

    public void editReject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", tvid.getText().toString());
            jsonObject.put("hostHeadEntry", tvhosthed.getText().toString());
            jsonObject.put("docEntry", tvdocentry4.getText().toString());
            jsonObject.put("lineNumber", tvlinenumb0.getText().toString());
            jsonObject.put("rejectCode", tvcodereject0.getText().toString());
            jsonObject.put("rejectName", tvReject3.getText().toString());
            jsonObject.put("rejectQty", tvRejectQty.getText().toString());
        }catch (JSONException e) {
            e.printStackTrace();
        }

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results) {
            text = text + c.getAddress();

//            AndroidNetworking.put(GlobalVars.BASE_IP + "index.php/inputreject?docEntry")
            AndroidNetworking.put(c.getAddress()+ "shopfloor2/index.php/inputreject?docEntry")
                    .addJSONObjectBody(jsonObject)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String message = response.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "JSONExceptions" + e, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(getApplicationContext(), "Data gagal diedit", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
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
    public void btn0Click(View v) {
        insert(0);
    }
    public void btnClearClic(View v) {
        clear1();
    }

    private void clear1() {
        str = "";
        num = 0;
        numtemp = 0;
        tvRejectQty.setText("");
    }



    private void insert(int i) {
        tvRejectQty = findViewById(R.id.tvRejectQty);
        str = str+Integer.toString(i);
        num = Integer.valueOf(str).intValue();
        tvRejectQty.setText(str);
    }
}
