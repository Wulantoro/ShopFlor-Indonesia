package com.example.shopfloor.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.InputRejectAdapter;
import com.example.shopfloor.Models.Header;
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

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ResultScanRejectActivity extends AppCompatActivity {
    private TextView tvcodereject0;
    private TextView tvReject3;
    private TextView tvdocentry4;
    private TextView btnSimpan;
    private TextView tvlinenumb0;
    public TextView tvRejectQty;
    private Gson gson;
    SharedPreferences pref, prf;
    public String str ="";
    float i,num,numtemp;
    private InputRejectAdapter adapter2;
    private TextView tvip17;

    private TextView tvmobileid1;
    private TextView tvid6;

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;

    private static final String TAG = AddSeqActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_scan_reject);

        tvcodereject0 = findViewById(R.id.tvcodereject0);
        tvReject3 = findViewById(R.id.tvReject3);
        btnSimpan = findViewById(R.id.btnSimpan);
        tvRejectQty = findViewById(R.id.tvRejectQty);
        tvlinenumb0 = findViewById(R.id.tvlinenumb0);
        tvip17 = findViewById(R.id.tvip17);
        tvmobileid1 = findViewById(R.id.tvmobileid1);
        tvid6 = findViewById(R.id.tvid6);
        gson = new Gson();
        adapter2 = new InputRejectAdapter(this);

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
        tvip17.setText(text);

        tvmobileid1.setText(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
//        lastId(tvdocentry4.getText().toString());
//        Log.e("docentryyyyyyy", String.valueOf(tvdocentry4.getText().toString()));

        TextView tvid = findViewById(R.id.tvid6);
        prf = getSharedPreferences("Id", MODE_PRIVATE);
        tvid.setText(String.valueOf(prf.getString("tvid", null)));

        tvdocentry4 = findViewById(R.id.tvdocentry4);
        TextView tvdocentry = findViewById(R.id.tvdocentry4);
        prf = getSharedPreferences("Docentry", MODE_PRIVATE);
        tvdocentry.setText(prf.getString("tvdocentry", null));
        Log.e(TAG, "docentru = " + prf.getString("tvdocentry", null));

        lastId(tvdocentry4.getText().toString());


        /****************BARCODE******************************/
        String barcode = getIntent().getStringExtra("reject");
        tvcodereject0.setText(barcode);
        /********************************************************/
        loadReject(tvcodereject0.getText().toString());

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tvRejectQty.length() != 0 && tvcodereject0.length() != 0) {
                    simpanReject();
//                    startActivity(new Intent(getApplicationContext(), RejectActivity.class));

                } else {
                    Toast.makeText(getApplicationContext(), "Reject Qty atau Reject tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Integer lastnumb = 1;
        tvlinenumb0.setText(String.valueOf(lastnumb));
        InputReject inputReject = new InputReject();
//        tvlinenumb0.setText(String.valueOf(inputReject.getLineNumber())+1);
//        Log.e("jumlahhhh", "onCreate: ", tvlinenumb0.setText(String.valueOf(inputReject.getLineNumber())));


        loadDatalastReject(tvdocentry4.getText().toString());
    }

    public void lastId(String docentry) {

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results) {
            text = text + c.getAddress();


//        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/lastid?mobileId=" + mobile)
            Log.e(TAG, "lastid = " + c.getAddress() + "shopfloor2/index.php/lastid?docEntry=" + docentry);
            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/lastid?docEntry=" + docentry)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            List<Header> result = new ArrayList<>();
                            try {
                                Log.e("tampil last = ", response.toString(1));
                                String message = response.getString("message");

                                if (message.equals("id ketemu")) {
                                    String records = response.getString("data");

                                    JSONArray dataArr = new JSONArray(records);
                                    Log.e("iddd", result.toString());

                                    if (dataArr.length() > 0) {
                                        for (int i = 0; i < dataArr.length(); i++) {
                                            Header header = gson.fromJson(dataArr.getJSONObject(i).toString(), Header.class);
                                            result.add(header);
                                            tvid6.setText(String.valueOf(header.getId()));


                                        }
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();

                            }
                        }

                        @Override
                        public void onError(ANError anError) {

                        }
                    });
        }

    }

    public void loadReject(final String codeReject) {

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c:results1) {
            text = text + c.getAddress();

//        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/ScanReject?code="+codeReject)
            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/ScanReject?code=" + codeReject)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            List<Reject> result = new ArrayList<>();
                            try {
                                Log.e("scanreject = ", response.toString(1));

                                if (result != null) {
                                    result.clear();

                                    String message = response.getString("message");

                                    if (message.equals("ScanReject were found")) {
                                        String records = response.getString("data");

                                        JSONArray dataArr = new JSONArray(records);

                                        if (dataArr.length() > 0) {
                                            for (int i = 0; i < dataArr.length(); i++) {
                                                Reject reject = gson.fromJson(dataArr.getJSONObject(i).toString(), Reject.class);
                                                result.add(reject);
                                                tvReject3.setText(reject.getName());
                                            }
                                        }
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {

                        }
                    });
        }
    }

    public void loadDatalastReject(String docentry) {
        if (adapter2 != null)
            adapter2.clearAll();

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c:results1) {
            text = text + c.getAddress();

//        AndroidNetworking.get(GlobalVars.BASE_IP + "shopfloor2/index.php/lastreject?docEntry="+docentry)
            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/lastreject?hostHeadEntry=" + docentry)
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



    public void simpanReject() {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray newArr = new JSONArray();
            jsonObject.put("hostHeadEntry", tvdocentry4.getText().toString());
            jsonObject.put("id", tvid6.getText().toString());
            jsonObject.put("lineNumber", tvlinenumb0.getText().toString());
            jsonObject.put("rejectCode", tvcodereject0.getText().toString());
            jsonObject.put("rejectName", tvReject3.getText().toString());
            jsonObject.put("rejectQty", tvRejectQty.getText().toString());

            newArr.put(jsonObject);
            Log.e(TAG,"coba input = "+ newArr.toString(1));

        }catch (JSONException e) {
            e.printStackTrace();
        }

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c:results1) {
            text = text + c.getAddress();

//        AndroidNetworking.post(GlobalVars.BASE_IP + "shopfloor2/index.php/inputreject")
            AndroidNetworking.post(c.getAddress() + "shopfloor2/index.php/inputreject")
                    .addJSONObjectBody(jsonObject)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String message = response.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), RejectActivity.class));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "JSONEXceptions" + e, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(getApplicationContext(), "Gagal menambah data", Toast.LENGTH_SHORT).show();

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
