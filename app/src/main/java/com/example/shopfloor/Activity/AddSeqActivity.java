package com.example.shopfloor.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.R;
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

import es.dmoral.toasty.Toasty;
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

    private TextView tvstatus;
    private TextView tvposted3;
    private Button btnBack;
    private TextView tvlastdocnum;
    private SuccDocAdapter succDocAdapter;
    private Gson gson;
    private Handler mHandler;
    private TextView tvmobileid;
    private TextView tvid;

    private String workcenter;
    private String username;
    private String noprod;
    private String prodcode;
    private String nmprod;
    private String docnum;
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

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;

    private static final String TAG = AddSeqActivity.class.getName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_seq);

        tvstatus = findViewById(R.id.tvstatus);
        tvposted3 = findViewById(R.id.tvposted3);
        tvlastdocnum = findViewById(R.id.tvlastdocnum);
        tvmobileid = findViewById(R.id.tvmobileid);
        tvid = findViewById(R.id.tvid);

        succDocAdapter = new SuccDocAdapter(this);

        gson = new Gson();

        tvmobileid.setText(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
        lastId(tvmobileid.getText().toString());

        tvInputSeq = findViewById(R.id.tvInputSeq);
        pref = getSharedPreferences("inQty", MODE_PRIVATE);
        prf = getSharedPreferences("Noprod", MODE_PRIVATE);

        pref = getSharedPreferences("Noprod", MODE_PRIVATE);
        noprod = pref.getString("tvnoprod", null);
        Log.e(TAG, "noprod = "+ noprod);

        pref = getSharedPreferences("Itemcode", MODE_PRIVATE);
        prodcode = pref.getString("tvitemcode", null);
        Log.e(TAG, "itemcode = " + prodcode);

        pref = getSharedPreferences("Nmprod", MODE_PRIVATE);
        nmprod = pref.getString("tvnmprod", null);
        Log.e(TAG, "nmprod = " + nmprod);

        pref = getSharedPreferences("docNum", MODE_PRIVATE);
        docnum = pref.getString("tvdocnum", null);
        Log.e(TAG, "docnum = " + docnum);

        pref = getSharedPreferences("ProdPlanQty", MODE_PRIVATE);
        prodplanqty = pref.getString("tvprodplanqty", null);
        Log.e(TAG, "prodplanqty = "+ prodplanqty);

        pref = getSharedPreferences("StsProd", MODE_PRIVATE);
        stsprod = pref.getString("tvstsprod", null);
        Log.e(TAG, "stsprod = " + stsprod);

        pref = getSharedPreferences("RouteCode", MODE_PRIVATE);
        routecode = pref.getString("tvroutecode", null);
        Log.e(TAG, "routrcode = " + routecode);

        pref = getSharedPreferences("RouteName", MODE_PRIVATE);
        routname = pref.getString("tvroutename", null);
        Log.e(TAG, "routename = " + routname);

        pref = getSharedPreferences("Sequence", MODE_PRIVATE);
        sequence = pref.getString("tvsequence", null);
        Log.e(TAG, "sequence = " + sequence);

        pref = getSharedPreferences("SequenceQty", MODE_PRIVATE);
        sequenceqty = pref.getString("tvsequenceqty", null);
        Log.e(TAG, "sequenceqty = "+ sequenceqty);

        pref = getSharedPreferences("TglMulai", MODE_PRIVATE);
        tglmulai = pref.getString("tvtglmulai", null);
        Log.e(TAG, "tglmulai = "+ tglmulai);

        pref = getSharedPreferences("jamMulai", MODE_PRIVATE);
        jammulai = pref.getString("tvjammulai", null);
        Log.e(TAG, "jam mulai = " + jammulai);

        pref = getSharedPreferences("Workcenter", MODE_PRIVATE);
        workcenter = pref.getString("workcenter1", null);
        Log.e(TAG, " workcwnter = " + workcenter);

        pref = getSharedPreferences("userId", MODE_PRIVATE);
        username = pref.getString("tvuserid0", null);
        Log.e(TAG, "username = " + username);

        pref = getSharedPreferences("Shift", MODE_PRIVATE);
        shift = pref.getString("tvshift", null);
        Log.e(TAG, "shifr = " + shift);

        pref = getSharedPreferences("Codeshift", MODE_PRIVATE);
        codeshift = pref.getString("tvcodeshift1", null);
        Log.e(TAG, "codeishifr = " + codeshift);

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
//        tvip5.setText(text);

//        TextView tvstatus = findViewById(R.id.tvstatus);
//        tvstatus.setText("0");

        TextView tvposted = findViewById(R.id.tvposted3);
        tvposted.setText("0");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Input Quantity");
//        Toolbar topToolBar = findViewById(R.id.toolbar);
//        setSupportActionBar(topToolBar);

        TextView toolbarText = findViewById(R.id.toolbar_text);
        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText(getTitle());
            setSupportActionBar(toolbar);
        }


        String S = "S";
        String bulan = new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
        String tahun = new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());
        String hari = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());

//        tvNo_doc1.setText(S + tahun + bulan + hari + Nol + AN);

        tvlastdocnum.setText(S+tahun+bulan+hari+"001");

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
//            pref = getSharedPreferences("Workcenter", MODE_PRIVATE);
//            workcenter = pref.getString("workcenter1", null);
//            SharedPreferences.Editor editor15 = pref.edit();
//            editor15.putString("workcenter", workcenter);
//            editor15.commit();
            upHeader();
//            startActivity(new Intent(getApplicationContext(), Add_DocActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void lastId(String mobile) {

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c:results1) {
            text = text + c.getAddress();

            Log.e("Sequence = ", c.getAddress() + "shopfloor2/index.php/LastIdheader?mobileId=" + mobile);

//        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/lastid?mobileId=" + mobile)
            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/LastIdheader?mobileId=" + mobile)
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
                                    Log.e("idddddd", result.toString());

                                    if (dataArr.length() > 0) {
                                        for (int i = 0; i < dataArr.length(); i++) {

                                            Header header = gson.fromJson(dataArr.getJSONObject(i).toString(), Header.class);
                                            result.add(header);

                                            tvid.setText(String.valueOf(header.getId() + 1));
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
                                            } else {
                                                tvlastdocnum.setText(S + tahun + bulan + hari + "001");
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
            jsonObject.put("prodNo", noprod);
            jsonObject.put("prodCode", prodcode);
            jsonObject.put("prodName", nmprod);
            jsonObject.put("prodPlanQty", prodplanqty);
            jsonObject.put("prodStatus", stsprod);
            jsonObject.put("routeCode", routecode);
            jsonObject.put("routeName", routname);
            jsonObject.put("sequence", sequence);
            jsonObject.put("sequenceQty", sequenceqty);
            jsonObject.put("shiftName", shift);
            jsonObject.put("shift", codeshift);
            jsonObject.put("docDate", tglmulai);
            jsonObject.put("tanggalMulai", tglmulai);
            jsonObject.put("jamMulai", jammulai);
            jsonObject.put("inQty", tvInputSeq.getText().toString());
            jsonObject.put("workCenter", workcenter);
//            jsonObject.put("status", tvstatus.getText().toString());
            jsonObject.put("posted", tvposted3.getText().toString());
            jsonObject.put("userId", username);

            newArr.put(jsonObject);
            Log.e(TAG,"coba input = "+ newArr.toString(1));

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
                                String message = response.getString("message");
                                Toasty.success(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                                pref = getSharedPreferences("Workcenter", MODE_PRIVATE);
                                workcenter = pref.getString("workcenter1", null);
                                SharedPreferences.Editor editor15 = pref.edit();
                                editor15.putString("workcenter1", workcenter);
                                editor15.commit();

                                startActivity(new Intent(getApplicationContext(), Open_DocActivity.class));
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
