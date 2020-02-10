package com.example.shopfloor.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import com.example.shopfloor.Adapter.InputRejectAdapter;
import com.example.shopfloor.Adapter.RejectAdapter;
import com.example.shopfloor.Models.InputReject;
import com.example.shopfloor.Models.Reject;
import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.RealmHelper;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RespRejectFragActivity extends AppCompatActivity {
    public TextView tvRejectQty;
    public String str ="";
    float i,num,numtemp;
    private Gson gson;
    private List<Reject> list;
    private Button btnReject;
    public static Dialog dialog;
    private RecyclerView rv;

    public static TextView tvReject3;
    public static TextView tvcodereject0;
    private TextView tvdocentry4;
    private TextView btnSimpan;
    private TextView tvlinenumb0;
    private TextView tvmobileid1;
    private TextView tvid6;
    private TextView tvip14;
    private TextView tvtotreject1;
    private TextView tvinqty1;
    private TextView tvoutqty1;
    private TextView tvgrantot;

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;
    SharedPreferences pref, prf;

    private RejectAdapter adapter1;
    private InputRejectAdapter adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resp_reject_frag);

        gson = new Gson();
        adapter1 = new RejectAdapter(this);
        adapter2 = new InputRejectAdapter(this);
        tvReject3 = findViewById(R.id.tvReject3);
        tvcodereject0 = findViewById(R.id.tvcodereject0);
        btnSimpan = findViewById(R.id.btnSimpan);
        tvRejectQty = findViewById(R.id.tvRejectQty);
        tvlinenumb0 = findViewById(R.id.tvlinenumb0);
        tvmobileid1 = findViewById(R.id.tvmobileid1);
        tvid6 = findViewById(R.id.tvid6);
        tvip14 = findViewById(R.id.tvip14);
        tvtotreject1 = findViewById(R.id.tvtotreject1);
        tvinqty1 = findViewById(R.id.tvinqty1);
        tvoutqty1 = findViewById(R.id.tvoutqty1);
        tvgrantot = findViewById(R.id.tvgrantot);

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
        tvip14.setText(text);


        tvmobileid1.setText(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
//        lastId(tvmobileid1.getText().toString());


        TextView tvid = findViewById(R.id.tvid6);
        prf = getSharedPreferences("Id", MODE_PRIVATE);
        tvid.setText(String.valueOf(prf.getString("tvid", null)));

        tvdocentry4 = findViewById(R.id.tvdocentry4);
        TextView tvdocentry = findViewById(R.id.tvdocentry4);
        prf = getSharedPreferences("Docentry", MODE_PRIVATE);
        tvdocentry.setText(prf.getString("tvdocentry", null));

        tvtotreject1 = findViewById(R.id.tvtotreject1);
        prf = getSharedPreferences("Tot", MODE_PRIVATE);
        tvtotreject1.setText(String.valueOf(prf.getString("tvtot", null)));

        TextView outqty = findViewById(R.id.tvoutqty1);
        prf = getSharedPreferences("Outqty", MODE_PRIVATE);
        tvoutqty1.setText(String.valueOf(prf.getString("tvoutqty", null)));

        TextView inqty = findViewById(R.id.tvinqty1);
        prf = getSharedPreferences("Inqty", MODE_PRIVATE);
        tvinqty1.setText(String.valueOf(prf.getString("tvinqty", null)));

//


        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tvRejectQty.length() != 0 && tvcodereject0.length() != 0) {
                    simpanReject();
                    startActivity(new Intent(getApplicationContext(), RejectActivity.class));
//                    Toast.makeText(getApplicationContext(), "Reject Qty atau Reject tidak boleh kosong", Toast.LENGTH_SHORT).show();

                } else {
                    Toasty.error(getApplicationContext(), "Reject Qty atau Reject tidak boleh kosong", Toast.LENGTH_SHORT).show();
//
                }
            }
        });

        btnReject = findViewById(R.id.btnReject);
        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        Integer lastnumb = 1;
        tvlinenumb0.setText(String.valueOf(lastnumb));
        InputReject inputReject = new InputReject();
//        tvlinenumb0.setText(String.valueOf(inputReject.getLineNumber())+1);
//        Log.e("jumlahhhh", "onCreate: ", tvlinenumb0.setText(String.valueOf(inputReject.getLineNumber())));


        loadDatalastReject(tvdocentry4.getText().toString());

    }


//tidak terpakai
//    public void lastId(String mobile) {
//
//        Realm realm = Realm.getDefaultInstance();
//        RealmResults<ServerModel> results = realm.where(ServerModel.class).findAll();
//        String text = "";
//        for (ServerModel c : results) {
//            text = text + c.getAddress();
//
//
////        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/lastid?mobileId=" + mobile)
//            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/lastid?mobileId=" + mobile)
//                    .build()
//                    .getAsJSONObject(new JSONObjectRequestListener() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            List<LastId> result = new ArrayList<>();
//                            try {
//                                Log.e("tampil last = ", response.toString(1));
//                                String message = response.getString("message");
//
//                                if (message.equals("id ketemu")) {
//                                    String records = response.getString("data");
//
//                                    JSONArray dataArr = new JSONArray(records);
//                                    Log.e("iddd", result.toString());
//
//                                    if (dataArr.length() > 0) {
//                                        for (int i = 0; i < dataArr.length(); i++) {
//                                            LastId lastId = gson.fromJson(dataArr.getJSONObject(i).toString(), LastId.class);
//                                            result.add(lastId);
////                                            tvid6.setText(String.valueOf(lastId.getId()));
//
//                                        }
//                                    }
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//
//                            }
//                        }
//
//                        @Override
//                        public void onError(ANError anError) {
//
//                        }
//                    });
//        }

//    }

    public void showDialog() {

        dialog = new Dialog(RespRejectFragActivity.this);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_reject_list);

        gson = new Gson();

        final RecyclerView rv1 = dialog.findViewById(R.id.rvRejectFrag);
        final EditText searchRej = dialog.findViewById(R.id.searchRej);

        final List<Reject> list = adapter1.getList_item();

        loadData(adapter1);
        rv1.setAdapter(adapter1);
        rv1.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv1.setAdapter(adapter1);

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
                        final String text = String.valueOf(list.get(i).getName()).toLowerCase();
                        if (text.contains(query.toString())) {

                            filteredList.add(list.get(i));
                        }
                    }

                    rv1.setLayoutManager(new LinearLayoutManager(RespRejectFragActivity.this));
                    adapter1 = new RejectAdapter(filteredList, RespRejectFragActivity.this);
                    rv1.setAdapter(adapter1);
                    adapter1.notifyDataSetChanged();
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

    public void loadData(final RejectAdapter adapter) {
        if (adapter1 != null)
            adapter1.clearAll();

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results) {
            text = text + c.getAddress();


//            AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/reject")
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

                                String message = response.getString("message");

                                if (message.equals("Reject were found")) {
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

    public void simpanReject() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("hostHeadEntry", tvdocentry4.getText().toString());
            jsonObject.put("id", tvid6.getText().toString());
            jsonObject.put("lineNumber", tvlinenumb0.getText().toString());
            jsonObject.put("rejectCode", tvcodereject0.getText().toString());
            jsonObject.put("rejectName", tvReject3.getText().toString());
            jsonObject.put("rejectQty", tvRejectQty.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results) {
            text = text + c.getAddress();

            Log.e("ip server = ", c.getAddress());
//            AndroidNetworking.post(GlobalVars.BASE_IP + "index.php/inputreject")
            AndroidNetworking.post(c.getAddress() + "shopfloor2/index.php/inputreject")
                    .addJSONObjectBody(jsonObject)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String message = response.getString("message");
                                Toasty.success(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "JSONEXceptions" + e, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toasty.error(getApplicationContext(), "Gagal menambah data", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public void loadDatalastReject(String docentry) {
        if (adapter2 != null)
            adapter2.clearAll();

        Log.e("docentry == ", "check docentry = " + tvdocentry4.getText().toString());

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results) {
            text = text + c.getAddress();

            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/lastreject?hostHeadEntry=" + docentry)
//            AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/lastreject?hostHeadEntry=" + docentry)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            List<InputReject> result = new ArrayList<>();
                            try {
                                Log.e("Item reject = ", response.toString(1));
                                if (result != null)
                                    result.clear();

                                String message = response.getString("message");
                                if (message.equals("Item reject where found")) {
                                    String records = response.getString("data");

                                    JSONArray dataArr = new JSONArray(records);

                                    if (dataArr.length() > 0) {
                                        for (int i = 0; i < dataArr.length(); i++) {
                                            InputReject inputReject = gson.fromJson(dataArr.getJSONObject(i).toString(), InputReject.class);
                                            result.add(inputReject);
                                            tvlinenumb0.setText(String.valueOf(inputReject.getLineNumber() + 1));

                                        }
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter2.addAll(result);
                        }

                        @Override
                        public void onError(ANError anError) {

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
