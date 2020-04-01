package com.example.shopfloor.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.InputCriteriaAdapter;
import com.example.shopfloor.Adapter.InputRejectAdapter;

import com.example.shopfloor.Adapter.OpenDocAdapter;
import com.example.shopfloor.Models.Header;
import com.example.shopfloor.Models.InputReject;
import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.Models.SincReject;
import com.example.shopfloor.Models.TotalReject;
import com.example.shopfloor.Models.Upcriteria;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.RealmHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RejectActivity extends AppCompatActivity {
    SharedPreferences prf, pref;
    private Button btnFrag;
    private TextView tvdocentry0;
    private TextView tvdocnum1;
    private TextView tvprodcode0;
    private TextView tvnoprod1;
    private TextView tvnmprod1;
    private TextView tvprodplanqty0;
    private TextView tvprodstatus2;
    private TextView tvroutecode2;
    private TextView tvroutename2;
    private TextView tvsequence1;
    private TextView tvseqqty1;
    private TextView tvdocdate0;
    private TextView tvjammulai2;
    private TextView tvInputQty1;
    private TextView tvOutputQty1;
    private TextView tvworkcenter6;
    private TextView tvtglsel1;
    private TextView tvjamsel1;
    private TextView tvstatus0;
    private TextView tvposted7;
    private TextView tvusername8;
    private TextView tvqcname4;
    private TextView tvshift4;
    private TextView tvcodeshift4;
    private TextView tvtglmulai2;
    private TextView tvid5;
    private TextView tvmobileid0;
    private TextView tvtotreject;

    private TextView tvdocentry7;
    private TextView tvdocnum2;
    private Gson gson;
    private InputRejectAdapter adapter;
    private OpenDocAdapter openDocAdapter;
    private List<InputReject> list;
    private RecyclerView rv;
    private TextView tvnamawc6;
    private TextView tvPRDSPECD2;
    private ImageButton ibscan;
    private TextView tvip10;
    private TextView tvdocentry01;
    private TextView tvid7;
    private InputCriteriaAdapter inputCriteriaAdapter;
    private TextView docsap0;
    private TextView tvtotok;
    private Handler mHandler;
    private static String TAG = RejectActivity.class.getSimpleName();

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
    String statusup = "Completed";


    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reject);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Input Reject");

        TextView toolbarText = findViewById(R.id.toolbar_text);
        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText(getTitle());
            setSupportActionBar(toolbar);
        }

        Toolbar topToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        tvdocentry0 = findViewById(R.id.tvdocentry0);
        tvprodcode0 = findViewById(R.id.tvprodcode0);
        tvnoprod1 = findViewById(R.id.tvnoprod1);
        tvnmprod1 = findViewById(R.id.tvnmprod1);
        tvsequence1 = findViewById(R.id.tvsequence1);
        tvseqqty1 = findViewById(R.id.tvseqqty1);
        tvInputQty1 = findViewById(R.id.tvInputQty1);
        tvOutputQty1 = findViewById(R.id.tvOutputQty1);
        tvworkcenter6 = findViewById(R.id.tvworkcenter6);
        tvtglsel1 = findViewById(R.id.tvtglsel1);
        tvjamsel1 = findViewById(R.id.tvjamsel1);
        tvposted7 = findViewById(R.id.tvposted7);
        ibscan = findViewById(R.id.ibscan);
        tvid5 = findViewById(R.id.tvid5);
        tvmobileid0 = findViewById(R.id.tvmobileid0);
        tvid7 = findViewById(R.id.tvid7);
        docsap0 = findViewById(R.id.docsap0);
        tvtotreject = findViewById(R.id.tvtotreject);
        tvtotok = findViewById(R.id.tvtotok);


        openDocAdapter = new OpenDocAdapter(this);
        inputCriteriaAdapter = new InputCriteriaAdapter(this);

        tvmobileid0.setText(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));

        pref = getSharedPreferences("docNum", MODE_PRIVATE);
        docnum = pref.getString("tvnodoc", null);
        Log.e(TAG, "docnum2 = " + docnum);

        pref = getSharedPreferences("Username", MODE_PRIVATE);
        username = pref.getString("tvusername", null);
        Log.e(TAG, "username = " + username);


        TextView tvworkcenter = findViewById(R.id.tvworkcenter6);
        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        tvworkcenter.setText(prf.getString("tvworkcenter", null));

        pref = getSharedPreferences("jamMulai", MODE_PRIVATE);
        jammulai = pref.getString("tvjammulai", null);
        Log.e(TAG, "jam mulai = " + jammulai);

        pref = getSharedPreferences("tanggalMulai", MODE_PRIVATE);
        tglmulai = pref.getString("tvtglmulai", null);
        Log.e(TAG, "tgkmulai = " + tglmulai);

        pref = getSharedPreferences("routeName", MODE_PRIVATE);
        routname = pref.getString("tvroutename", null);
        Log.e(TAG, "routename = " + routname);

        pref = getSharedPreferences("routeCode", MODE_PRIVATE);
        routecode = pref.getString("tvroutecode", null);
        Log.e(TAG, "routecode = " + routecode);

        pref = getSharedPreferences("prodStatus", MODE_PRIVATE);
        stsprod = pref.getString("tvprodstatus", null);
        Log.e(TAG, "stsprod = " + stsprod);

        pref = getSharedPreferences("prodPlanQty", MODE_PRIVATE);
        prodplanqty = pref.getString("tvprodplanqty", null);
        Log.e(TAG, "Prodplanqty = " + prodplanqty);

        TextView tvprodcode = findViewById(R.id.tvprodcode0);
        prf = getSharedPreferences("prodCode", MODE_PRIVATE);
        tvprodcode.setText(prf.getString("tvprodcode", null));

        TextView tvdocentry = findViewById(R.id.tvdocentry0);
        prf = getSharedPreferences("Docentry", MODE_PRIVATE);
        tvdocentry.setText(String.valueOf(prf.getString("tvdocentry", null)));
        Log.e("docemtry", prf.getString("tvdocentry", null));

        TextView tvidd = findViewById(R.id.tvid7);
        prf = getSharedPreferences("Id", MODE_PRIVATE);
        tvidd.setText(String.valueOf(prf.getString("tvid", null)));

        /*****************qtyin**********************/
        TextView inqty = findViewById(R.id.tvInputQty1);
        prf = getSharedPreferences("inQty", MODE_PRIVATE);
        inqty.setText(prf.getString("inqty", null));

        /*****************************qtyout***************************/
        TextView outqty = findViewById(R.id.tvOutputQty1);
        prf = getSharedPreferences("outQty", MODE_PRIVATE);
        outqty.setText(prf.getString("outqty", null));

        /************************workcenter*******************************/
        TextView workcenter = findViewById(R.id.tvWorkcenter);
        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        workcenter.setText(prf.getString("workcenter", null));

        /*********************noprod****************************************/
        TextView tvnoprod = findViewById(R.id.tvnoprod1);
        prf = getSharedPreferences("prodNo", MODE_PRIVATE);
        tvnoprod.setText(prf.getString("tvprodno", null));

        /********************************nmprod*******************************/
        TextView tvnmprod = findViewById(R.id.tvnmprod1);
        prf = getSharedPreferences("Namaprod", MODE_PRIVATE);
        tvnmprod.setText(prf.getString("tvnamaprod", null));

        /****************************sequence************************************/
        TextView tvsequence = findViewById(R.id.tvsequence1);
        prf = getSharedPreferences("Sequence", MODE_PRIVATE);
        tvsequence.setText(prf.getString("tvsequence", null));

        /***************************sequenceqty**********************************/
        TextView tvsequenceqty = findViewById(R.id.tvseqqty1);
        prf = getSharedPreferences("SequenceQty", MODE_PRIVATE);
        tvsequenceqty.setText(prf.getString("tvsequenceqty", null));
        Log.e(TAG, "sequemce qty >> " + prf.getString("tvsequenceqty", null));

        /*******************tglsel*************************************************/
        TextView tglsel = findViewById(R.id.tvtglsel1);
        prf = getSharedPreferences("Tglsel", MODE_PRIVATE);
        tglsel.setText(prf.getString("tvtglsel", null));

        /******************************jamsel*********************************/
        TextView jamsel = findViewById(R.id.tvjamsel1);
        prf = getSharedPreferences("Jamsel", MODE_PRIVATE);
        jamsel.setText(String.valueOf(prf.getString("tvjamsel", null)));
        Log.e("jam selesai = ", String.valueOf(prf.getString("jamsel", null)));

        TextView tvdocsts = findViewById(R.id.tvdocsts2);
        prf = getSharedPreferences("Docsts", MODE_PRIVATE);
        tvdocsts.setText(prf.getString("tvdocsts", null));
        Log.e(TAG, "docsts >> " + prf.getString("tvdocsts", null));

        TextView tvinqty = findViewById(R.id.tvInputQty1);
        prf = getSharedPreferences("Inqty", MODE_PRIVATE);
        tvinqty.setText(prf.getString("tvinqty", null));

        TextView tvoutqty = findViewById(R.id.tvOutputQty1);
        prf = getSharedPreferences("Outqty", MODE_PRIVATE);
        tvoutqty.setText(prf.getString("tvoutqty", null));

        pref = getSharedPreferences("Shift", MODE_PRIVATE);
        shift = pref.getString("tvshift", null);
        Log.e(TAG, "shift = " + shift );

        pref = getSharedPreferences("Codeshift", MODE_PRIVATE);
        codeshift = pref.getString("tvcodeshift", null);
        Log.e(TAG, "code shifr = " + codeshift);

//        TextView tvnamawc = findViewById(R.id.tvnamawc6);
//        prf = getSharedPreferences("Namawc", MODE_PRIVATE);
//        tvnamawc.setText(prf.getString("tvnamawc", null));

        TextView tvid = findViewById(R.id.tvid5);
        prf = getSharedPreferences("Id", MODE_PRIVATE);
        tvid.setText(String.valueOf(prf.getString("tvid", null)));
        Log.e(TAG, "tvid5 = " + String.valueOf(prf.getString("tvid", null)));

//        TextView tvstatus = findViewById(R.id.tvstatus0);
//        tvstatus.setText("Completed");


        TextView tvposted = findViewById(R.id.tvdocsts2);
        tvposted.setText("Pending1");

        int totOk = Integer.parseInt(String.valueOf(tvInputQty1.getText())) - Integer.parseInt(String.valueOf(tvOutputQty1.getText()));
        tvtotok.setText(String.valueOf(totOk));
        Log.e(TAG, "total ok = " + totOk);

//        load criteria yang sudah terisi
        loadCriteriaIsi(tvdocentry0.getText().toString());
        Log.e("hostentry", tvdocentry0.getText().toString());

//        load total rejecr
        totalReject(tvdocentry0.getText().toString());
//        Log.e("hostreject", tvdocentry0.getText().toString());

        //        Setup Realm
        Realm.init(getApplicationContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        serverModels = new ArrayList<>();

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results1) {
            text = text + c.getAddress();
        }
//        tvip10.setText(text);


        btnFrag = findViewById(R.id.btnFrag);
        btnFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               int totOk = Integer.parseInt(String.valueOf(tvInputQty1.getText())) - Integer.parseInt(String.valueOf(tvOutputQty1.getText()));
               Log.e(TAG, "total ok = " + totOk);

                if (Integer.parseInt(String.valueOf(tvInputQty1.getText())) == Integer.parseInt(String.valueOf(tvOutputQty1.getText()))) {
                    Toast.makeText(getApplicationContext(), "Tidak perlu", Toast.LENGTH_SHORT).show();
//                } else if () {

                } else {
                    SharedPreferences prf, pref;
                    Intent intent = new Intent(RejectActivity.this, RespRejectFragActivity.class);

                    pref = getSharedPreferences("Id", MODE_PRIVATE);
                    String tvid = tvid7.getText().toString();
                    SharedPreferences.Editor editor22 = pref.edit();
                    editor22.putString("tvid", tvid);
                    editor22.commit();


                    pref = getSharedPreferences("Inqty", MODE_PRIVATE);
                    String tvin = tvInputQty1.getText().toString();
                    SharedPreferences.Editor editor1 = pref.edit();
                    editor1.putString("tvinqty", tvin);
                    editor1.commit();

                    pref = getSharedPreferences("Outqty", MODE_PRIVATE);
                    String tvout = tvOutputQty1.getText().toString();
                    SharedPreferences.Editor editor2 = pref.edit();
                    editor2.putString("tvoutqty", tvout);
                    editor2.commit();

                    startActivity(intent);
                }

            }
        });


        ibscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScanRejectActivity.class);
                startActivity(intent);
            }
        });

        gson = new Gson();
        rv = findViewById(R.id.rvInputReject);
        adapter = new InputRejectAdapter(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        loadData(tvdocentry0.getText().toString());
        rv.setAdapter(adapter);


        this.mHandler = new Handler();
        m_Runnable.run();
    }


    private final Runnable m_Runnable = new Runnable() {
        @Override
        public void run() {
//            Toast.makeText(getApplicationContext(),"in runnable",Toast.LENGTH_SHORT).show();
            totalReject(tvdocentry0.getText().toString());
            Log.e("hostreject", tvdocentry0.getText().toString());
            RejectActivity.this.mHandler.postDelayed(m_Runnable, 2000);
        }
    };

    public void totalReject(String host) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results1) {
            text = text + c.getAddress();

            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/Totreject?hostHeadEntry=" + host)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            List<TotalReject> results = new ArrayList<>();
                            try {
                                Log.e(TAG, "totReject " + response.toString(1));

                                if (results != null)
                                    results.clear();

                                String message = response.getString("message");

                                if (message.equals("tot ketemu")) {
                                    String records = response.getString("data");

                                    JSONArray dataArr = new JSONArray(records);

                                    if (dataArr.length() > 0) {
                                        for (int i = 0; i < dataArr.length(); i++) {
                                            TotalReject totalReject = gson.fromJson(dataArr.getJSONObject(i).toString(), TotalReject.class);
                                            results.add(totalReject);
                                            int g = 0;
                                            if (totalReject.getTotReject() == null) {
                                                tvtotreject.setText(String.valueOf(0));
                                            } else {
                                                tvtotreject.setText(totalReject.getTotReject().replace(".000000", ""));
                                                Log.e(TAG, "total rejecr = " + totalReject.getTotReject());
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


    public void loadData(String docentry) {
        final ProgressDialog progres = new ProgressDialog(this);
        progres.setMessage("Sedang Prosess");
        progres.setTitle("sedang proses");
        progres.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progres.show();

        if (adapter != null)
            adapter.clearAll();

        Log.e("docnum3000 == ", "check docnum = " + tvdocentry0.getText().toString());

//        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/inputreject?docEntry="+ prf.getString("tvdocentry", null))
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results1) {
            text = text + c.getAddress();

            Log.e("ip server = ", c.getAddress());
//        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/inputreject?hostHeadEntry="+ docentry)
            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/inputreject?hostHeadEntry=" + docentry)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            List<InputReject> result = new ArrayList<>();
                            try {
                                Log.e("item reject = ", response.toString(1));
                                if (result != null)
                                    result.clear();

                                String message = response.getString("message");
                                if (message.equals("Item reject were found")) {
                                    String records = response.getString("data");

                                    JSONArray dataArr = new JSONArray(records);

                                    if (dataArr.length() > 0) {
                                        for (int i = 0; i < dataArr.length(); i++) {
                                            InputReject inputReject = gson.fromJson(dataArr.getJSONObject(i).toString(), InputReject.class);
                                            result.add(inputReject);
                                        }
                                    }
                                }
                                progres.dismiss();

                            } catch (JSONException e) {
                                e.printStackTrace();

                                progres.dismiss();
                            }
                            adapter.addAll(result);
                        }

                        @Override
                        public void onError(ANError anError) {
                            progres.dismiss();
                        }
                    });
        }
    }

    public void editHeader() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("docEntry", tvdocentry0.getText().toString());
            jsonObject.put("docNum", tvdocnum1.getText().toString());
            jsonObject.put("prodNo", tvnoprod1.getText().toString());
            jsonObject.put("prodCode", tvprodcode0.getText().toString());
            jsonObject.put("prodName", tvnmprod1.getText().toString());
            jsonObject.put("prodPlanQty", tvprodplanqty0.getText().toString());
            jsonObject.put("prodStatus", stsprod);
            jsonObject.put("routeCode", tvroutecode2.getText().toString());
            jsonObject.put("routeName", tvroutename2.getText().toString());
            jsonObject.put("sequence", tvsequence1.getText().toString());
            jsonObject.put("sequenceQty", tvseqqty1.getText().toString());
            jsonObject.put("shiftName", tvshift4.getText().toString());
            jsonObject.put("shift", tvcodeshift4.getText().toString());
            jsonObject.put("docDate", tvdocdate0.getText().toString());
            jsonObject.put("tanggalMulai", tvdocdate0.getText().toString());
            jsonObject.put("jamMulai", tvjammulai2.getText().toString());
            jsonObject.put("inQty", tvInputQty1.getText().toString());
            jsonObject.put("outQty", tvOutputQty1.getText().toString());
            jsonObject.put("workCenter", tvworkcenter6.getText().toString());
            jsonObject.put("tanggalSelesai", tvtglsel1.getText().toString());
            jsonObject.put("jamSelesai", tvjamsel1.getText().toString());
//            jsonObject.put("status", tvstatus0.getText().toString());
            jsonObject.put("posted", tvposted7.getText().toString());
//            jsonObject.put("UploadTime", tvjamsel1.getText().toString()); muncul otomatis
//            jsonObject.put("QcName", tvqcname4.getText().toString());
            jsonObject.put("userId", tvusername8.getText().toString());
            jsonObject.put("id", tvid5.getText().toString());
            jsonObject.put("mobileId", tvmobileid0.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results1) {
            text = text + c.getAddress();
            Log.e("ip address = ", c.getAddress());
            AndroidNetworking.put(c.getAddress() + "shopfloor2/index.php/simpanheader?docEntry")
//        AndroidNetworking.put(GlobalVars.BASE_IP + "index.php/simpanheader?docEntry")
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
                                Toast.makeText(getApplicationContext(), "JSONExceptions" + e, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toasty.error(getApplicationContext(), "Gagal menambah data", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void loadCriteriaIsi(String hostHeadEntry) {

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results1) {
            Log.e(TAG, "host" + hostHeadEntry);

            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/upcriteria?hostHeadEntry=" + hostHeadEntry)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            List<Upcriteria> result = new ArrayList<>();
                            try {
                                Log.e("criteria = ", response.toString(1));
                                if (result != null)
                                    result.clear();

                                String message = response.getString("message");

                                if (message.equals("Criteria were found")) {
                                    String records = response.getString("data");

                                    JSONArray dataArr = new JSONArray(records);

                                    if (dataArr.length() > 0) {
                                        for (int i = 0; i < dataArr.length(); i++) {
                                            Upcriteria upcriteria = gson.fromJson(dataArr.getJSONObject(i).toString(), Upcriteria.class);
                                            result.add(upcriteria);
                                        }
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            inputCriteriaAdapter.addAll(result);

                        }

                        @Override
                        public void onError(ANError anError) {

                        }
                    });
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.upsap, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        int totOk = Integer.parseInt(String.valueOf(tvInputQty1.getText())) - Integer.parseInt(String.valueOf(tvOutputQty1.getText()));
        tvtotok.setText(String.valueOf(totOk));
        Log.e(TAG, "total ok = " + totOk);

        int totNG = Integer.parseInt(tvtotreject.getText().toString());

//            if (id == R.id.upsap1 && totOk == totNG) {
        if (id == R.id.upsap1) {
            TextView tvposted1 = findViewById(R.id.tvposted7);
            tvposted1.setText("1");

            loadHeaderStem(tvworkcenter6.getText().toString(), tvmobileid0.getText().toString());
            Log.e("workcenter", tvworkcenter6.getText().toString());
            Log.e("mobile", tvmobileid0.getText().toString());

            JSONObject jsonObject = new JSONObject();

            try {
                //ntar arrau nya diilangin lagi ya
//                JSONArray newArr = new JSONArray();

                jsonObject.put("docNum", docnum);
                jsonObject.put("prodNo", tvnoprod1.getText().toString());
                jsonObject.put("prodCode", tvprodcode0.getText().toString());
                jsonObject.put("prodName", tvnmprod1.getText().toString());
                jsonObject.put("prodPlanQty", prodplanqty);
                jsonObject.put("prodStatus", stsprod);
                jsonObject.put("routeCode", routecode);
                jsonObject.put("routeName", routname);
                jsonObject.put("sequence", tvsequence1.getText().toString());
                jsonObject.put("sequenceQty", tvseqqty1.getText().toString());
                jsonObject.put("shiftName", shift);
                jsonObject.put("shift", codeshift);
                jsonObject.put("docDate", tglmulai);
                jsonObject.put("tanggalMulai", tglmulai);
                jsonObject.put("jamMulai", jammulai);
                jsonObject.put("inQty", tvInputQty1.getText().toString());
                jsonObject.put("outQty", tvOutputQty1.getText().toString());
                jsonObject.put("workCenter", tvworkcenter6.getText().toString());
                jsonObject.put("tanggalSelesai", tvtglsel1.getText().toString());
                jsonObject.put("jamSelesai", tvjamsel1.getText().toString());
                jsonObject.put("status", statusup);
                jsonObject.put("posted", tvposted7.getText().toString());
                jsonObject.put("userId", username);
                jsonObject.put("id", tvid5.getText().toString());
                jsonObject.put("mobileId", tvmobileid0.getText().toString());

//                newArr.put(jsonObject);
//                Log.e(TAG, "coba input put put = " + newArr.toString(1));

            } catch (JSONException e) {
                e.printStackTrace();
            }


            /*******************Upload Criteria ke Sap***********************************/
            //ini yang dipakai*****************************************************************************************
            String element1 = gson.toJson(
                    inputCriteriaAdapter.getData(),
                    new TypeToken<ArrayList<Upcriteria>>() {

                    }.getType());

            try {
                JSONArray array = new JSONArray(element1);
                Log.e("arrraaayyyy = ", array.toString(1));

                JSONArray newArr1 = new JSONArray();

                for (int i = 0; i < array.length(); i++) {
                    Upcriteria upcriteria = gson.fromJson(array.getJSONObject(i).toString(), Upcriteria.class);

                    JSONObject object = new JSONObject();
                    object.put("hostHeadEntry", upcriteria.getHostHeadEntry());
//                    object.put("id", tvid5.getText().toString());
                    object.put("id", upcriteria.getId());
                    object.put("criteria", upcriteria.getCriteria());
                    object.put("criteriaDesc", upcriteria.getCriteriaDesc());
                    object.put("standard", upcriteria.getStandard());
                    object.put("lineNumber", upcriteria.getLineNumber());
                    object.put("actualResult", upcriteria.getActualResult());
                    object.put("valueType", upcriteria.getValueType());
                    object.put("actualRemarks", upcriteria.getActualRemarks());

                    newArr1.put(object);
                }
                Log.e("input crit ", newArr1.toString(1));
                jsonObject.put("detail1", newArr1);
//                simpanSincCriteria(newArr);
//                simpanSincreject(newArr);
            } catch (JSONException e) {
                e.printStackTrace();
            }



            String element = gson.toJson(

                    adapter.getData(),
                    new TypeToken<ArrayList<SincReject>>() {
                    }.getType());

            try {
                JSONArray array = new JSONArray(element);

                JSONArray newArr = new JSONArray();

                for (int i = 0; i < array.length(); i++) {

                    InputReject inputReject = gson.fromJson(array.getJSONObject(i).toString(), InputReject.class);

                        JSONObject object = new JSONObject();
                        object.put("hostHeadEntry", inputReject.getHostHeadEntry());
                        object.put("lineNumber", inputReject.getLineNumber());
                        object.put("rejectName", inputReject.getRejectName());
                        object.put("rejectQty", inputReject.getRejectQty());
                        object.put("rejectCode", inputReject.getRejectCode());
                        object.put("id", inputReject.getId());
                        newArr.put(object);
                }

                if (array.length() > 0) {
                    jsonObject.put("detail", newArr);
                    Log.e(TAG, "coba sinc reject4 = " + newArr.toString(1));
                    Log.e(TAG, "array 4 = " + newArr.length());
                } else {
                    JSONArray newArr2 = new JSONArray();
                    JSONObject object1 = new JSONObject();

                        object1.put("lineNumber", "0");
                        object1.put("rejectName", "");
                        object1.put("rejectQty", "0");
                        object1.put("rejectCode", "");
                        object1.put("id", tvid5.getText().toString());
                        newArr2.put(object1);
                    jsonObject.put("detail", newArr2);
                    Log.e(TAG, "coba sinc reject3 = " + newArr2.toString(1));
                    Log.e(TAG, "array 3 = " + newArr2.length());

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Realm realm = Realm.getDefaultInstance();
            RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
            String text = "";
            for (ServerModel c : results1) {
                text = text + c.getAddress();


                //ini yang dibapaki
//        AndroidNetworking.post(GlobalVars.BASE_IP + "index.php/SincReject")
                AndroidNetworking.post(c.getAddress() + "shopfloor2/index.php/UploadSap")
                        .addJSONObjectBody(jsonObject)
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String message = response.getString("message");
                                    Toasty.success(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                    editStatusHeader();
                                    startActivity(new Intent(getApplicationContext(), Open_DocActivity.class));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "JSONEXceptions" + e, Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                Toasty.error(getApplicationContext(), "Gagal Synchron SAP", Toast.LENGTH_LONG).show();
                                //Toasty.success(getApplicationContext(), "Berhasil Synchron SAP", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
//                editHeader();

//            startActivity(new Intent(getApplicationContext(), Open_DocActivity.class));



        } else{
            Toasty.error(getApplicationContext(), "Total Reject dan Quantity tidak sama", Toasty.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Open_DocActivity.class);
        startActivity(intent);
        finish();
    }

    public void loadHeaderStem(String wccode, String mobil) {

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results1) {
            text = text + c.getAddress();

            Log.e("ip docsap", c.getAddress() + "shopfloor2/index.php/DocentriSap?workCenter="+wccode + "&mobileId=" + mobil);
            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/DocentriSap?workCenter="+wccode + "&mobileId=" + mobil)
                    .setTag(this)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            List<Header> result = new ArrayList<>();

                            try {
                                if (result != null)
                                    result.clear();

                                Log.e("hasil = ", String.format("%s", response.toString(1)));

                                String message = response.getString("message");
                                if (message.equals("Docentrysap were found")) {
                                    String records = response.getString("data");
                                    JSONArray dataArr = new JSONArray(records);

                                    if (dataArr.length() > 0) {
                                        for (int i = 0; i < dataArr.length(); i++) {
                                            Header header = gson.fromJson(dataArr.getJSONObject(i).toString(), Header.class);
                                            result.add(header);
                                            docsap0.setText(String.valueOf(header.getDocEntry()));
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



    public void editStatusHeader() {

        JSONObject jsonObject = new JSONObject();

        try {
            //ntar arrau nya diilangin lagi ya
                JSONArray newArr = new JSONArray();

            jsonObject.put("docEntry", tvdocentry0.getText().toString());
            jsonObject.put("docNum", docnum);
            jsonObject.put("prodNo", tvnoprod1.getText().toString());
            jsonObject.put("prodCode", tvprodcode0.getText().toString());
            jsonObject.put("prodName", tvnmprod1.getText().toString());
            jsonObject.put("prodPlanQty", prodplanqty);
            jsonObject.put("prodStatus", stsprod);
            jsonObject.put("routeCode", routecode);
            jsonObject.put("routeName", routname);
            jsonObject.put("sequence", tvsequence1.getText().toString());
            jsonObject.put("sequenceQty", tvseqqty1.getText().toString());
            jsonObject.put("shiftName", shift);
            jsonObject.put("shift", codeshift);
            jsonObject.put("docDate", tglmulai);
            jsonObject.put("tanggalMulai", tglmulai);
            jsonObject.put("jamMulai", jammulai);
            jsonObject.put("inQty", tvInputQty1.getText().toString());
            jsonObject.put("outQty", tvOutputQty1.getText().toString());
            jsonObject.put("workCenter", tvworkcenter6.getText().toString());
            jsonObject.put("tanggalSelesai", tvtglsel1.getText().toString());
            jsonObject.put("jamSelesai", tvjamsel1.getText().toString());
            jsonObject.put("status", statusup);
            jsonObject.put("posted", 2);
            jsonObject.put("userId", username);
            jsonObject.put("id", tvid5.getText().toString());
            jsonObject.put("mobileId", tvmobileid0.getText().toString());


//            jsonObject.put("docEntry", tvdocentry0.getText().toString());
//            jsonObject.put("docNum", tvdocnum1.getText().toString());
//            jsonObject.put("prodNo", tvnoprod1.getText().toString());
//            jsonObject.put("prodCode", tvprodcode0.getText().toString());
//            jsonObject.put("prodName", tvnmprod1.getText().toString());
//            jsonObject.put("prodPlanQty", tvprodplanqty0.getText().toString());
//            jsonObject.put("prodStatus", tvprodstatus2.getText().toString());
//            jsonObject.put("routeCode", tvroutecode2.getText().toString());
//            jsonObject.put("routeName", tvroutename2.getText().toString());
//            jsonObject.put("sequence", tvsequence1.getText().toString());
//            jsonObject.put("sequenceQty", tvseqqty1.getText().toString());
//            jsonObject.put("shiftName", tvshift4.getText().toString());
//            jsonObject.put("shift", tvcodeshift4.getText().toString());
//            jsonObject.put("docDate", tvdocdate0.getText().toString());
//            jsonObject.put("tanggalMulai", tvdocdate0.getText().toString());
//            jsonObject.put("jamMulai", tvjammulai2.getText().toString());
//            jsonObject.put("inQty", tvInputQty1.getText().toString());
//            jsonObject.put("outQty", tvOutputQty1.getText().toString());
//            jsonObject.put("workCenter", tvworkcenter6.getText().toString());
//            jsonObject.put("tanggalSelesai", tvtglsel1.getText().toString());
//            jsonObject.put("jamSelesai", tvjamsel1.getText().toString());
////            jsonObject.put("status", tvstatus0.getText().toString());
//            jsonObject.put("posted", 2);
////            jsonObject.put("UploadTime", tvjamsel1.getText().toString()); muncul otomatis
////            jsonObject.put("QcName", tvqcname4.getText().toString());
//            jsonObject.put("userId", tvusername8.getText().toString());
//            jsonObject.put("id", tvid5.getText().toString());
//            jsonObject.put("mobileId", tvmobileid0.getText().toString());

                newArr.put(jsonObject);
                Log.e(TAG, "coba edit sts header = " + newArr.toString(1));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        /*******************Upload Criteria ke Sap***********************************/
//        String element1 = gson.toJson(
//                inputCriteriaAdapter.getData(),
//                new TypeToken<ArrayList<Upcriteria>>() {
//
//                }.getType());
//
//        try {
//            JSONArray array = new JSONArray(element1);
//            Log.e("arrraaayyyy = ", array.toString(1));
//
//            JSONArray newArr = new JSONArray();
//
//            for (int i = 0; i < array.length(); i++) {
//                Upcriteria upcriteria = gson.fromJson(array.getJSONObject(i).toString(), Upcriteria.class);
//
//                JSONObject object = new JSONObject();
//                object.put("hostHeadEntry", upcriteria.getHostHeadEntry());
//                object.put("id", upcriteria.getId());
//                object.put("criteria", upcriteria.getCriteria());
//                object.put("criteriaDesc", upcriteria.getCriteriaDesc());
//                object.put("standard", upcriteria.getStandard());
//                object.put("lineNumber", upcriteria.getLineNumber());
//                object.put("actualResult", upcriteria.getActualResult());
//                object.put("valueType", upcriteria.getValueType());
//
//                newArr.put(object);
//            }
//
//            jsonObject.put("detail1", newArr);
//            Log.e("input crit ", newArr.toString(1));
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        String element = gson.toJson(
//
//                adapter.getData(),
//                new TypeToken<ArrayList<SincReject>>() {
//                }.getType());
//
//        try {
//            JSONArray array = new JSONArray(element);
//
//            JSONArray newArr = new JSONArray();
//
//            for (int i = 0; i < array.length(); i++) {
//
//                InputReject inputReject = gson.fromJson(array.getJSONObject(i).toString(), InputReject.class);
//
//                JSONObject object = new JSONObject();
//                object.put("hostHeadEntry", inputReject.getHostHeadEntry());
//                object.put("lineNumber", inputReject.getLineNumber());
//                object.put("rejectName", inputReject.getRejectName());
//                object.put("rejectQty", inputReject.getRejectQty());
//                object.put("rejectCode", inputReject.getRejectCode());
//                object.put("id", inputReject.getId());
//                newArr.put(object);
//            }
//
//            if (array.length() > 0) {
//                jsonObject.put("detail", newArr);
//                Log.e(TAG, "coba sinc reject4 = " + newArr.toString(1));
//                Log.e(TAG, "array 4 = " + newArr.length());
//            } else {
//                JSONArray newArr2 = new JSONArray();
//                JSONObject object1 = new JSONObject();
//
//                object1.put("lineNumber", "0");
//                object1.put("rejectName", "");
//                object1.put("rejectQty", "0");
//                object1.put("rejectCode", "");
//                object1.put("id", tvid5.getText().toString());
//                newArr2.put(object1);
//                jsonObject.put("detail", newArr2);
//                Log.e(TAG, "coba sinc reject3 = " + newArr2.toString(1));
//                Log.e(TAG, "array 3 = " + newArr2.length());
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results1) {
            text = text + c.getAddress();

//        AndroidNetworking.post(GlobalVars.BASE_IP + "index.php/SincReject")
            AndroidNetworking.put(c.getAddress() + "shopfloor2/index.php/simpanheader?docEntry")
//            AndroidNetworking.post(c.getAddress() + "shopfloor2/index.php/UploadSap")
                    .addJSONObjectBody(jsonObject)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String message = response.getString("message");
                                Toasty.success(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "JSONExceptions" + e, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toasty.error(getApplicationContext(), "Gagal menambah data", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }



    public void simpanSincreject(JSONArray jsonArray) {

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results1) {
            text = text + c.getAddress();

//        AndroidNetworking.post(GlobalVars.BASE_IP + "index.php/SincReject")
            AndroidNetworking.post(c.getAddress() + "shopfloor2/index.php/UploadSap")
                    .addJSONArrayBody(jsonArray)
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
                            Toasty.error(getApplicationContext(), "Gagal Simpan Reject", Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }

    public void simpanSincCriteria(JSONArray jsonArray) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results) {
            text = text + c.getAddress();
            AndroidNetworking.post(c.getAddress() + "shopfloor2/index.php/sinccriteria")
                    .addJSONArrayBody(jsonArray)
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
                            Toasty.error(getApplicationContext(), "Gagal Sync Criteria", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
