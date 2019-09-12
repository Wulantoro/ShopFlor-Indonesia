package com.example.shopfloor.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.SuccDocAdapter;
import com.example.shopfloor.Models.Header;
import com.example.shopfloor.Models.LastId;
import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.GlobalVars;
import com.example.shopfloor.Utils.RealmHelper;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class AddSeqActivity extends AppCompatActivity {
    private TextView tvInputSeq;
    public SharedPreferences pref;
    public String str ="";
    Character op = 'q';
    float i,num,numtemp;
    SharedPreferences prf;
    private TextView tvcoba;
    private TextView tvNo_doc1;
    private TextView tvNo_prod1;
    private TextView tvprod1;
    private TextView tvNm_prod1;
    private TextView tvRoute_Code1;
    private TextView tvRoute_Code2;
    private TextView tvQty_rencProd1;
    private TextView tvSts_prod1;
    private TextView tvSquence1;
    private TextView tvSquence_Qty1;
    private TextView tvShift1;
    private TextView tvTgl_mulai1;
    private TextView tvJam_mulai1;
    private TextView tvprod7;
    private TextView tvprodcode7;
    private TextView tvnmprod7;
    private TextView tvdocnum7;
    private TextView tvprodplanqty7;
    public static TextView tvstsprod7;
    private TextView tvroutecode7;
    private TextView tvroutename7;
    private TextView tvsequence7;
    private TextView tvsequenceqty7;
    private TextView tvtglmulai7;
    private TextView tvjammulai7;
    private TextView tvwc3;
    private TextView tvstatus;
    private TextView tvposted3;
    private TextView tvusername3;
    private TextView tvshift0;
    private TextView tvcodeshift0;
    private Button btnBack;
    private TextView tvlastdocnum;
    private SuccDocAdapter succDocAdapter;
    private Gson gson;
    private Handler mHandler;
    private TextView tvmobileid;
    private TextView tvid;
    private TextView tvip5;

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_seq);

        tvNo_doc1 = findViewById(R.id.tvNo_doc1);
        tvNo_prod1 = findViewById(R.id.tvNo_prod1);
        tvprod1 = findViewById(R.id.tvprod1);
        tvNm_prod1 = findViewById(R.id.tvNm_prod1);
        tvRoute_Code1 = findViewById(R.id.tvRoute_Code1);
        tvRoute_Code2 = findViewById(R.id.tvRoute_Code2);
        tvQty_rencProd1 = findViewById(R.id.tvQty_rencProd1);
        tvShift1 = findViewById(R.id.tvShift1);
        tvTgl_mulai1 = findViewById(R.id.tvTgl_mulai1);
        tvJam_mulai1 = findViewById(R.id.tvJam_mulai1);
        tvprod7 = findViewById(R.id.tvprod7);
        tvprodcode7 = findViewById(R.id.tvprodcode7);
        tvnmprod7 = findViewById(R.id.tvnmprod7);
        tvdocnum7 = findViewById(R.id.tvdocnum7);
        tvprodplanqty7 = findViewById(R.id.tvprodplanqty7);
        tvstsprod7 = findViewById(R.id.tvstsprod7);
        tvroutecode7 = findViewById(R.id.tvroutecode7);
        tvroutename7 = findViewById(R.id.tvroutename7);
        tvsequence7 = findViewById(R.id.tvsequence7);
        tvsequenceqty7 = findViewById(R.id.tvsequenceqty7);
        tvtglmulai7 = findViewById(R.id.tvtglmulai7);
        tvjammulai7 = findViewById(R.id.tvjammulai7);
        tvwc3 = findViewById(R.id.tvwc3);
        tvstatus = findViewById(R.id.tvstatus);
        tvposted3 = findViewById(R.id.tvposted3);
        tvusername3 = findViewById(R.id.tvusername3);
        tvshift0 = findViewById(R.id.tvshift0);
        tvcodeshift0 = findViewById(R.id.tvcodeshift0);
        tvlastdocnum = findViewById(R.id.tvlastdocnum);
        tvmobileid = findViewById(R.id.tvmobileid);
        tvid = findViewById(R.id.tvid);
        tvip5 = findViewById(R.id.tvip5);
        succDocAdapter = new SuccDocAdapter(this);

        gson = new Gson();

        tvmobileid.setText(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
        lastId(tvmobileid.getText().toString());

        tvInputSeq = findViewById(R.id.tvInputSeq);
        pref = getSharedPreferences("inQty", MODE_PRIVATE);
        prf = getSharedPreferences("Noprod", MODE_PRIVATE);

        TextView tvprod7 = findViewById(R.id.tvprod7);
        prf = getSharedPreferences("Noprod", MODE_PRIVATE);
        tvprod7.setText(prf.getString("tvnoprod", null));

        TextView tvprodcode7 = findViewById(R.id.tvprodcode7);
        prf = getSharedPreferences("Itemcode", MODE_PRIVATE);
        tvprodcode7.setText(prf.getString("tvitemcode", null));

        TextView tvnmprod7 = findViewById(R.id.tvnmprod7);
        prf = getSharedPreferences("Nmprod", MODE_PRIVATE);
        tvnmprod7.setText(prf.getString("tvnmprod", null));

        TextView tvdocnum7 = findViewById(R.id.tvdocnum7);
        prf = getSharedPreferences("docNum", MODE_PRIVATE);
        tvdocnum7.setText(prf.getString("tvdocnum", null));

        TextView tvprodplanqty7 = findViewById(R.id.tvprodplanqty7);
        prf = getSharedPreferences("ProdPlanQty", MODE_PRIVATE);
        tvprodplanqty7.setText(prf.getString("tvprodplanqty", null));

        TextView tvstsprod7 = findViewById(R.id.tvstsprod7);
        prf = getSharedPreferences("StsProd", MODE_PRIVATE);
        tvstsprod7.setText(prf.getString("tvstsprod", null));

        TextView tvroutecode7 = findViewById(R.id.tvroutecode7);
        prf = getSharedPreferences("RouteCode", MODE_PRIVATE);
        tvroutecode7.setText(prf.getString("tvroutecode", null));

        TextView tvroutename7 = findViewById(R.id.tvroutename7);
        prf = getSharedPreferences("RouteName", MODE_PRIVATE);
        tvroutename7.setText(prf.getString("tvroutename", null));

        Log.e("Route name", " " + prf.getString("tvroutename", null));

        TextView tvsequence7 = findViewById(R.id.tvsequence7);
        prf = getSharedPreferences("Sequence", MODE_PRIVATE);
        tvsequence7.setText(prf.getString("tvsequence", null));

        TextView tvsequenceqty7 = findViewById(R.id.tvsequenceqty7);
        prf = getSharedPreferences("SequenceQty", MODE_PRIVATE);
        tvsequenceqty7.setText(prf.getString("tvsequenceqty", null));

        TextView tvtglmulai7 = findViewById(R.id.tvtglmulai7);
        prf = getSharedPreferences("TglMulai", MODE_PRIVATE);
        tvtglmulai7.setText(prf.getString("tvtglmulai", null));

        Log.e("tanggal mulai", prf.getString("tvtglmulai", null));

        TextView tvjammulai7 = findViewById(R.id.tvjammulai7);
        prf = getSharedPreferences("jamMulai", MODE_PRIVATE);
        tvjammulai7.setText(prf.getString("tvjammulai", null));

        TextView tvwc = findViewById(R.id.tvwc3);
        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        tvwc.setText(prf.getString("workcenter", null));

        TextView tvuserid = findViewById(R.id.tvusername3);
        prf = getSharedPreferences("userId", MODE_PRIVATE);
        tvuserid.setText(prf.getString("tvuserid", null));

        TextView tvshift = findViewById(R.id.tvshift0);
        prf = getSharedPreferences("Shift", MODE_PRIVATE);
        tvshift.setText(prf.getString("tvshift", null));

        TextView tvcodeshift1 = findViewById(R.id.tvcodeshift0);
        prf = getSharedPreferences("Codeshift", MODE_PRIVATE);
        tvcodeshift1.setText(prf.getString("tvcodeshift1", null));

//        TextView tvipadd = findViewById(R.id.tvip5);
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
        tvip5.setText(text);

        TextView tvstatus = findViewById(R.id.tvstatus);
        tvstatus.setText("0");

        TextView tvposted = findViewById(R.id.tvposted3);
        tvposted.setText("0");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);
        Toolbar topToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        Log.e("Date 2 " ,tvtglmulai7.getText().toString());

/***************reload otomatis****************/
        this.mHandler = new Handler();
        m_Runnable.run();

    }

    private final Runnable m_Runnable = new Runnable() {
        @Override
        public void run() {
//            Toast.makeText(getApplicationContext(),"in runnable",Toast.LENGTH_SHORT).show();
            lastDocnum();
            AddSeqActivity.this.mHandler.postDelayed(m_Runnable, 2000);
        }
    };
/*****************************************************************************************/
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_seq, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_upHeader && tvInputSeq.length() != 0) {
            upHeader();
            startActivity(new Intent(getApplicationContext(), Add_DocActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void lastId(String mobile) {

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c:results1) {
            text = text + c.getAddress();

//        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/lastid?mobileId=" + mobile)
            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/lastid?mobileId=" + mobile)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            List<LastId> result = new ArrayList<>();
                            try {

                                Log.e("tampil last = ", response.toString(1));
                                String message = response.getString("message");

                                if (message.equals("id ketemu")) {
                                    String records = response.getString("data");

                                    JSONArray dataArr = new JSONArray(records);
                                    Log.e("idddddd", result.toString());

                                    if (dataArr.length() > 0) {
                                        for (int i = 0; i < dataArr.length(); i++) {

                                            LastId lastId = gson.fromJson(dataArr.getJSONObject(i).toString(), LastId.class);
                                            result.add(lastId);

                                            tvid.setText(String.valueOf(lastId.getId() + 1));
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

    public void lastDocnum() {

        if (succDocAdapter != null)
            succDocAdapter.clearAll();

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c:results1) {
            text = text + c.getAddress();

//        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/lastdocnum")
            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/lastdocnum")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            List<Header> results = new ArrayList<>();
                            try {
                                Log.e("tampil last = ", response.toString(1));

                                if (results != null)
                                    results.clear();

                                String message = response.getString("message");

                                if (message.equals("data ketemu")) {
                                    String records = response.getString("data");

                                    JSONArray dataArr = new JSONArray(records);

                                    if (dataArr.length() > 0) {
                                        for (int i = 0; i < dataArr.length(); i++) {
                                            Header header = gson.fromJson(dataArr.getJSONObject(i).toString(), Header.class);
                                            results.add(header);

//                                        String S = "S";
//                                        String nodoc = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
//                                        String docnum = header.getDocNum().substring(9);
//                                        String AN = "" + (Integer.parseInt(docnum) + 1);
//                                        String Nol = "";
//
//                                        if (AN.length() == 1) {
//                                            Nol = "00";
//                                        } else if (AN.length() == 2) {
//                                            Nol = "0";
//                                        } else if (AN.length() == 3) {
//                                            Nol = "";
//                                        }
//
//                                        tvlastdocnum.setText(S+ nodoc + Nol + AN);

                                            String S = "S";
                                            String bulan = new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
                                            String tahun = new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());
                                            String hari = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
                                            String docnum = header.getDocNum().substring(9);
                                            String bulankmrn1 = header.getDocNum().substring(5);
                                            String bulankmrn2 = bulankmrn1.substring(0, 2);
                                            String AN = "" + (Integer.parseInt(docnum) + 1);
                                            Log.e("aaannn", AN);
                                            String Nol = "";

                                            if (bulan.equals(bulankmrn2)) {
                                                if (AN.length() == 1) {
                                                    Nol = "00";

                                                } else if (AN.length() == 2) {
                                                    Nol = "0";
                                                } else if (AN.length() == 3) {
                                                    Nol = "";
                                                }

                                                tvlastdocnum.setText(S + tahun + bulan + hari + Nol + AN);
                                            } else if (bulan != bulankmrn2) {
                                                String AN1 = "001";
                                                tvlastdocnum.setText(S + tahun + bulan + hari + Nol + AN1);
                                            }

//                                        tvNo_doc1.setText(S + tahun + bulan + hari + Nol + AN);
//                                        tvblnkmrn.setText(bulankmrn1);
//                                        tvblnkmrn0.setText(bulankmrn2);
//                                        tvblnskrg.setText(bulan);
                                        }
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            succDocAdapter.addAll(results);
                        }

                        @Override
                        public void onError(ANError anError) {

                        }
                    });
        }
    }


    private void upHeader() {
        JSONObject jsonObject = new JSONObject();

        try {
            JSONArray newArr = new JSONArray();
            jsonObject.put("id", tvid.getText().toString());
            jsonObject.put("mobileId", tvmobileid.getText().toString());
            jsonObject.put("docNum", tvlastdocnum.getText().toString());
            jsonObject.put("prodNo", tvprod7.getText().toString());
            jsonObject.put("prodCode", tvprodcode7.getText().toString());
            jsonObject.put("prodName", tvnmprod7.getText().toString());
            jsonObject.put("prodPlanQty", tvprodplanqty7.getText().toString());
            jsonObject.put("prodStatus", tvstsprod7.getText().toString());
            jsonObject.put("routeCode", tvroutecode7.getText().toString());
            jsonObject.put("routeName", tvroutename7.getText().toString());
            jsonObject.put("sequence", tvsequence7.getText().toString());
            jsonObject.put("sequenceQty", tvsequenceqty7.getText().toString());
            jsonObject.put("shiftName", tvshift0.getText().toString());
            jsonObject.put("shift", tvcodeshift0.getText().toString());
            jsonObject.put("docDate", tvtglmulai7.getText().toString());
            jsonObject.put("tanggalMulai", tvtglmulai7.getText().toString());
            jsonObject.put("jamMulai", tvjammulai7.getText().toString());
            jsonObject.put("inQty", tvInputSeq.getText().toString());
            jsonObject.put("workCenter", tvwc3.getText().toString());
//            jsonObject.put("status", tvstatus.getText().toString());
            jsonObject.put("posted", tvposted3.getText().toString());
            jsonObject.put("userId", tvusername3.getText().toString());

            Log.e("Username 1===========" ,tvusername3.getText().toString());
            newArr.put(jsonObject);
            Log.e("coba input = ", newArr.toString(1));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        prf = getSharedPreferences("Ip", MODE_PRIVATE);
        Log.e("ip = ", prf.getString("tvip", null));

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c:results1) {
            text = text + c.getAddress();

//        AndroidNetworking.post(GlobalVars.BASE_IP +"index.php/simpanheader")
//        AndroidNetworking.post(prf.getString("tvip", null) +"index.php/simpanheader")
            AndroidNetworking.post(c.getAddress() + "shopfloor2/index.php/simpanheader")
                    .addJSONObjectBody(jsonObject)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.e("Date 1===========", tvtglmulai7.getText().toString());

                                String message = response.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "JSONExceptions" + e, Toast.LENGTH_SHORT).show();
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
       tvInputSeq.setText("");
    }

    private void insert(int i) {
        str = str+Integer.toString(i);
        num = Integer.valueOf(str).intValue();
        tvInputSeq.setText(str);
    }

}
