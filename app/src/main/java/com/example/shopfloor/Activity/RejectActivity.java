package com.example.shopfloor.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import com.example.shopfloor.Adapter.CriteriaAdapter;
import com.example.shopfloor.Adapter.InputCriteriaAdapter;
import com.example.shopfloor.Adapter.InputRejectAdapter;

import com.example.shopfloor.Adapter.OpenDocAdapter;
import com.example.shopfloor.Models.Criteria;
import com.example.shopfloor.Models.Header;
import com.example.shopfloor.Models.InputReject;
import com.example.shopfloor.Models.Productorder;
import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.Models.SincHeader;
import com.example.shopfloor.Models.SincReject;
import com.example.shopfloor.Models.Totreject;
import com.example.shopfloor.Models.Upcriteria;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.GlobalVars;
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
    SharedPreferences prf;
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
    private static String TAG = RejectActivity.class.getSimpleName();


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
        tvdocnum1 = findViewById(R.id.tvdocnum1);
        tvprodcode0 = findViewById(R.id.tvprodcode0);
        tvnoprod1 = findViewById(R.id.tvnoprod1);
        tvnmprod1 = findViewById(R.id.tvnmprod1);
        tvprodplanqty0 = findViewById(R.id.tvprodplanqty0);
        tvprodstatus2 = findViewById(R.id.tvprodstatus2);
        tvroutecode2 = findViewById(R.id.tvroutecode2);
        tvroutename2 = findViewById(R.id.tvroutename2);
        tvsequence1 = findViewById(R.id.tvsequence1);
        tvseqqty1 = findViewById(R.id.tvseqqty1);
        tvdocdate0 = findViewById(R.id.tvdocdate0);
        tvjammulai2 = findViewById(R.id.tvjammulai2);
        tvInputQty1 = findViewById(R.id.tvInputQty1);
        tvOutputQty1 = findViewById(R.id.tvOutputQty1);
        tvworkcenter6 = findViewById(R.id.tvworkcenter6);
        tvtglsel1 = findViewById(R.id.tvtglsel1);
        tvjamsel1 = findViewById(R.id.tvjamsel1);
        tvstatus0 = findViewById(R.id.tvstatus0);
        tvposted7 = findViewById(R.id.tvposted7);
        tvusername8 = findViewById(R.id.tvusername8);
        tvqcname4 = findViewById(R.id.tvqcname4);
        tvshift4 = findViewById(R.id.tvshift4);
        tvcodeshift4 = findViewById(R.id.tvcodeshift4);
//        tvtglmulai2 = findViewById(R.id.tvtglmulai2);
        tvdocnum2 = findViewById(R.id.tvdocnum2);
        tvdocentry7 = findViewById(R.id.tvdocentry7);
        tvnamawc6 = findViewById(R.id.tvnamawc6);
        ibscan = findViewById(R.id.ibscan);
        tvid5 = findViewById(R.id.tvid5);
        tvmobileid0 = findViewById(R.id.tvmobileid0);
        tvip10 = findViewById(R.id.tvip10);
//        tvdocentry01 = findViewById(R.id.tvdocentry01);
        tvid7 = findViewById(R.id.tvid7);
        docsap0 = findViewById(R.id.docsap0);


        openDocAdapter = new OpenDocAdapter(this);
        inputCriteriaAdapter = new InputCriteriaAdapter(this);

        tvmobileid0.setText(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));

        TextView tvdocnum = findViewById(R.id.tvdocnum1);
        prf = getSharedPreferences("Docnum", MODE_PRIVATE);
        tvdocnum.setText(prf.getString("docnum", null));

        TextView tvqcname = findViewById(R.id.tvqcname4);
        prf = getSharedPreferences("Qcname", MODE_PRIVATE);
        tvqcname.setText(prf.getString("tvqcname", null));

        TextView tvusername = findViewById(R.id.tvusername8);
        prf = getSharedPreferences("Username", MODE_PRIVATE);
        tvusername.setText(prf.getString("tvusername", null));


        TextView tvworkcenter = findViewById(R.id.tvworkcenter6);
        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        tvworkcenter.setText(prf.getString("tvworkcenter", null));

        TextView tvjammulai = findViewById(R.id.tvjammulai2);
        prf = getSharedPreferences("Jammulai", MODE_PRIVATE);
        tvjammulai.setText(prf.getString("tvjammulai", null));

        TextView tvdocdate = findViewById(R.id.tvdocdate0);
        prf = getSharedPreferences("Docdate", MODE_PRIVATE);
        tvdocdate.setText(prf.getString("tvdocdate", null));

        TextView tvroutename = findViewById(R.id.tvroutename2);
        prf = getSharedPreferences("Routename", MODE_PRIVATE);
        tvroutename.setText(prf.getString("tvroutename", null));

        TextView tvroutecode = findViewById(R.id.tvroutecode2);
        prf = getSharedPreferences("Routecode", MODE_PRIVATE);
        tvroutecode.setText(prf.getString("tvroutecode", null));

        TextView tvprodstatus = findViewById(R.id.tvprodstatus2);
        prf = getSharedPreferences("Prodstatus", MODE_PRIVATE);
        tvprodstatus.setText(prf.getString("tvprodstatus", null));

        TextView tvprodplanqty = findViewById(R.id.tvprodplanqty0);
        prf = getSharedPreferences("Prodplanqty", MODE_PRIVATE);
        tvprodplanqty.setText(prf.getString("tvprodplanqty", null));

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


//        TextView tvdocentry1 = findViewById(R.id.tvdocentry01);
//        prf = getSharedPreferences("Docentry", MODE_PRIVATE);
//        tvdocentry1.setText(String.valueOf(prf.getString("tvdocentry", null)));

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
        prf = getSharedPreferences("Noprod", MODE_PRIVATE);
        tvnoprod.setText(prf.getString("tvnoprod", null));

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

        TextView tvshift = findViewById(R.id.tvshift4);
        prf = getSharedPreferences("Shift", MODE_PRIVATE);
        tvshift.setText(prf.getString("tvshift", null));

        TextView tvcodesh = findViewById(R.id.tvcodeshift4);
        prf = getSharedPreferences("Codeshift", MODE_PRIVATE);
        tvcodesh.setText(prf.getString("tvcodeshift", null));

        TextView tvnamawc = findViewById(R.id.tvnamawc6);
        prf = getSharedPreferences("Namawc", MODE_PRIVATE);
        tvnamawc.setText(prf.getString("tvnamawc", null));

        TextView tvid = findViewById(R.id.tvid5);
        prf = getSharedPreferences("Id", MODE_PRIVATE);
        tvid.setText(String.valueOf(prf.getString("tvid", null)));

        TextView tvstatus = findViewById(R.id.tvstatus0);
        tvstatus.setText("Completed");

        TextView tvposted = findViewById(R.id.tvdocsts2);
        tvposted.setText("Pending1");

//        load criteria yang sudah terisi
        loadCriteriaIsi(tvdocentry0.getText().toString());
        Log.e("hostentry", tvdocentry0.getText().toString());

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
        tvip10.setText(text);


        btnFrag = findViewById(R.id.btnFrag);
        btnFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Integer.parseInt(String.valueOf(tvInputQty1.getText())) == Integer.parseInt(String.valueOf(tvOutputQty1.getText()))) {
                    Toast.makeText(getApplicationContext(), "Tidak perlu", Toast.LENGTH_SHORT).show();
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
            jsonObject.put("prodStatus", tvprodstatus2.getText().toString());
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

        if (id == R.id.uptemp) {
            TextView tvposted1 = findViewById(R.id.tvposted7);
            tvposted1.setText("1");
            editHeader();

        } else if (id == R.id.upsap1) {
            TextView tvposted1 = findViewById(R.id.tvposted7);
            tvposted1.setText("1");

//            TextView tvstatus = findViewById(R.id.tvstatus0);
//            tvstatus.setText("Complete");

//            uploadSapHeader();
            loadHeaderStem(tvworkcenter6.getText().toString(), tvmobileid0.getText().toString());
            Log.e("workcenter", tvworkcenter6.getText().toString());
            Log.e("mobile", tvmobileid0.getText().toString());

            JSONObject jsonObject = new JSONObject();

            try {
//                JSONArray newArr = new JSONArray();
//                jsonObject.put("docEntry", tvdocentry0.getText().toString());
                jsonObject.put("docNum", tvdocnum1.getText().toString());
                jsonObject.put("prodNo", tvnoprod1.getText().toString());
                jsonObject.put("prodCode", tvprodcode0.getText().toString());
                jsonObject.put("prodName", tvnmprod1.getText().toString());
                jsonObject.put("prodPlanQty", tvprodplanqty0.getText().toString());
                jsonObject.put("prodStatus", tvprodstatus2.getText().toString());
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
                jsonObject.put("status", tvstatus0.getText().toString());
                jsonObject.put("posted", tvposted7.getText().toString());
//            jsonObject.put("UploadTime", tvjamsel1.getText().toString()); muncul otomatis
//            jsonObject.put("QcName", tvqcname4.getText().toString());
                jsonObject.put("userId", tvusername8.getText().toString());
                jsonObject.put("id", tvid5.getText().toString());
                jsonObject.put("mobileId", tvmobileid0.getText().toString());

//                newArr.put(jsonObject);
//                Log.e(TAG, "coba input put put = " + newArr.toString(1));
//                simpanSincreject(newArr);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            /*******************Upload Criteria ke Sap***********************************/
            String element1 = gson.toJson(
                    inputCriteriaAdapter.getData(),
                    new TypeToken<ArrayList<Upcriteria>>() {

                    }.getType());

            try {
                JSONArray array = new JSONArray(element1);
                Log.e("arrraaayyyy = ", array.toString(1));

                JSONArray newArr = new JSONArray();

                for (int i = 0; i < array.length(); i++) {
                    Upcriteria upcriteria = gson.fromJson(array.getJSONObject(i).toString(), Upcriteria.class);

                    JSONObject object = new JSONObject();
//                    object.put("hostHeadEntry", upcriteria.getHostHeadEntry());
                    object.put("id", tvid5.getText().toString());
                    object.put("criteria", upcriteria.getCriteria());
                    object.put("criteriaDesc", upcriteria.getCriteriaDesc());
                    object.put("standard", upcriteria.getStandard());
                    object.put("lineNumber", upcriteria.getLineNumber());
                    object.put("actualResult", upcriteria.getActualResult());
                    object.put("valueType", upcriteria.getValueType());

                    newArr.put(object);
                }
                Log.e("input crit ", newArr.toString(1));
                jsonObject.put("detail1", newArr);
//                simpanSincCriteria(newArr);
//                simpanSincreject(newArr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
//
//
//            /*******************Upload Reject ke Sap***********************************/
            String element = gson.toJson(

                    adapter.getData(),
                    new TypeToken<ArrayList<SincReject>>() {
                    }.getType());

            try {
                JSONArray array = new JSONArray(element);
                Log.e("arrraaayyyy = ", array.toString(1));

                JSONArray newArr = new JSONArray();

                for (int i = 0; i < array.length(); i++) {
                    InputReject inputReject = gson.fromJson(array.getJSONObject(i).toString(), InputReject.class);

                    JSONObject object = new JSONObject();
//                    object.put("hostHeadEntry", tvdocentry0.getText().toString());
                    object.put("lineNumber", inputReject.getLineNumber());
                    object.put("rejectName", inputReject.getRejectName());
                    object.put("rejectQty", inputReject.getRejectQty());
                    object.put("rejectCode", inputReject.getRejectCode());
                    object.put("id", inputReject.getId());

                    newArr.put(object);
                }
                Log.e(TAG, "coba sinc reject = " + newArr.toString(1));
                jsonObject.put("detail", newArr);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Realm realm = Realm.getDefaultInstance();
            RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
            String text = "";
            for (ServerModel c : results1) {
                text = text + c.getAddress();

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
                                    Toasty.success(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "JSONEXceptions" + e, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
//                                Toasty.error(getApplicationContext(), "Gagal Synchron SAP", Toast.LENGTH_SHORT).show();
                                Toasty.success(getApplicationContext(), "Berhasil Synchron SAP", Toast.LENGTH_SHORT).show();

                            }
                        });
            }

            startActivity(new Intent(getApplicationContext(), Open_DocActivity.class));



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



    public void uploadSapHeader() {
        JSONObject jsonObject = new JSONObject();

        try {
            JSONArray newArr = new JSONArray();
            jsonObject.put("docEntry", tvdocentry0.getText().toString());
            jsonObject.put("docNum", tvdocnum1.getText().toString());
            jsonObject.put("prodNo", tvnoprod1.getText().toString());
            jsonObject.put("prodCode", tvprodcode0.getText().toString());
            jsonObject.put("prodName", tvnmprod1.getText().toString());
            jsonObject.put("prodPlanQty", tvprodplanqty0.getText().toString());
            jsonObject.put("prodStatus", tvprodstatus2.getText().toString());
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
            jsonObject.put("status", tvstatus0.getText().toString());
            jsonObject.put("posted", tvposted7.getText().toString());
//            jsonObject.put("UploadTime", tvjamsel1.getText().toString()); muncul otomatis
//            jsonObject.put("QcName", tvqcname4.getText().toString());
            jsonObject.put("userId", tvusername8.getText().toString());
            jsonObject.put("id", tvid5.getText().toString());
            jsonObject.put("mobileId", tvmobileid0.getText().toString());

            newArr.put(jsonObject);
            Log.e(TAG, "coba input put put = "+ newArr.toString(1));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results1) {
            text = text + c.getAddress();

//            AndroidNetworking.post(GlobalVars.BASE_IP + "index.php/UploadSap")
            AndroidNetworking.post(c.getAddress() + "shopfloor2/index.php/UploadSap")
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
                            Toasty.error(getApplicationContext(), "Gagal synchron data", Toast.LENGTH_SHORT).show();

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
