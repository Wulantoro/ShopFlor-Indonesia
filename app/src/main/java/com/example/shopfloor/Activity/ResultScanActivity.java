package com.example.shopfloor.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.ProductorderAdapter;
import com.example.shopfloor.Adapter.SequenceAdapter;
import com.example.shopfloor.Adapter.SuccDocAdapter;
import com.example.shopfloor.Models.Header;
import com.example.shopfloor.Models.Poscan;
import com.example.shopfloor.Models.Productorder;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ResultScanActivity extends AppCompatActivity {

    private Button btn_Pilihprod;
    private Button btn_Pilihseq;
    public static TextView tvNm_prod1;
    public static Dialog dialog;
    private Gson gson;
    public static TextView tvNo_Prod1;
    public static TextView tvprod1;
    public static TextView tvRoute_Code1;
    public static TextView tvRoute_Code2;
    public static TextView tvQty_rencProd1;
    public static TextView tvSts_Prod1;
    public static TextView tvNo_doc1;
    public static TextView tvSquence1;
    public static TextView tvSquence_Qty1;
    public static TextView tvJam_mulai1;
    public static TextView tvwc1;
    private TextView tvNo_prod1;
    private TextView cobadocnum;
    private TextView dateView;
    private Calendar calendar;
    private int year, month, day;
    private RecyclerView rv, rv2;
    public EditText searchPO;
    public SharedPreferences pref;
    SharedPreferences prf;
    private TextView tvShift1;
    private TextView tvTgl_mulai1;
    private TextView tvusername2;
    private TextView tvdocnum1;
    private String docnum = "";
    private EditText searchSeq;
    private TextView tvcodeshift;
    private SuccDocAdapter adapter3;
    private ProductorderAdapter adapter2;
    private SequenceAdapter adapter1;
    private List<Productorder> list;
    private TextView tvnamawc2;
    private Productorder productorder;
    private Poscan poscan;
    private TextView tvip16;
    private Handler mHandler;

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;

    //barcode
    private static final String TAG = ResultScanActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_scan);

        /**************Toolbar**********************************/
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setTitle(null);
//        Toolbar topToolbar = findViewById(R.id.toolbar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);
        Toolbar topToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolbar);
        /*******************************************************/

        gson = new Gson();
        adapter3 = new SuccDocAdapter(this);
        adapter2 = new ProductorderAdapter(this);
        adapter1 = new SequenceAdapter(this);

        tvNm_prod1 = findViewById(R.id.tvNm_prod1);
        tvNo_Prod1 = findViewById(R.id.tvNo_prod1);
        tvRoute_Code1 = findViewById(R.id.tvRoute_Code1);
        tvQty_rencProd1 = findViewById(R.id.tvQty_rencProd1);
        tvSts_Prod1 = findViewById(R.id.tvSts_prod1);
        tvprod1 = findViewById(R.id.tvprod1);
        tvRoute_Code2 = findViewById(R.id.tvRoute_Code2);
        tvNo_doc1 = findViewById(R.id.tvNo_doc1);
        tvNo_prod1 = findViewById(R.id.tvNo_prod1);
        tvSts_Prod1 = findViewById(R.id.tvSts_prod1);
        tvShift1 = findViewById(R.id.tvShift1);
        tvTgl_mulai1 = findViewById(R.id.tvTgl_mulai1);
        tvJam_mulai1 = findViewById(R.id.tvJam_mulai1);
        tvwc1 = findViewById(R.id.tvwc1);
        tvusername2 = findViewById(R.id.tvusername2);
        tvcodeshift = findViewById(R.id.tvcodeshift);
        tvnamawc2 = findViewById(R.id.tvnamawc2);
        tvSquence1 = findViewById(R.id.tvSquence1);
        tvSquence_Qty1 = findViewById(R.id.tvSquence_Qty1);
        tvip16 = findViewById(R.id.tvip16);

        /*************************BARCODE*******************************/
        String barcode = getIntent().getStringExtra("wccode");
        tvNo_prod1.setText(barcode.substring(0, 8));
        tvSquence1.setText(barcode.substring(9));

        searchBarcode(tvNo_Prod1.getText().toString(), tvSquence1.getText().toString());

        dateView = findViewById(R.id.tvTgl_mulai1);

        //workcenter
        TextView tvwc = findViewById(R.id.tvwc1);
        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        tvwc.setText(prf.getString("workcenter", null));

        TextView tvuserid = findViewById(R.id.tvusername2);
        prf = getSharedPreferences("userId", MODE_PRIVATE);
        tvuserid.setText(prf.getString("tvuserid", null));

        TextView tvnamawc = findViewById(R.id.tvnamawc2);
        prf = getSharedPreferences("Namewc", MODE_PRIVATE);
        tvnamawc.setText(prf.getString("tvnamewc", null));

//        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        TextView tgl_mulai = findViewById(R.id.tvTgl_mulai1);
        tgl_mulai.setText(date);

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
        tvip16.setText(text);

        /***************reload otomatis****************/
        this.mHandler = new Handler();
        m_Runnable.run();

    }

    private final Runnable m_Runnable = new Runnable() {
        @Override
        public void run() {
//            Toast.makeText(getApplicationContext(),"in runnable",Toast.LENGTH_SHORT).show();
            LastDocnum();
            ResultScanActivity.this.mHandler.postDelayed(m_Runnable, 2000);
        }
    };

    //ketika klik back loncat ke home activity
    public void onBackPressed() {
        pref = getSharedPreferences("Workcenter", MODE_PRIVATE);
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        String wc = tvwc1.getText().toString();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("tvworkcenter", wc);
        editor.commit();

        startActivity(intent);
    }

    /*********************************toolbar************************/
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_seq) {
            /*************jam mulaui************************/
            String jam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            TextView jam_mulai = findViewById(R.id.tvJam_mulai1);
            jam_mulai.setText(jam);

            /*********shift***********************/
            Calendar c = Calendar.getInstance();

            SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE");
            String getCurentDay = sdf1.format(c.getTime());

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String getCurentTime = sdf.format(c.getTime());
            String getTestTime = "07:00";
            String getTestTime1 = "15:00";
            String getTestTime2 = "23:00";

            //nama shift
            TextView shift = findViewById(R.id.tvShift1);
            if (getCurentTime.compareTo(getTestTime) > 0 && getTestTime1.compareTo(getCurentTime) > 0) {
                shift.setText(getCurentDay + " " + "-" + " " + "Shift 1");
            } else if (getCurentTime.compareTo(getTestTime1) > 0 && getTestTime2.compareTo(getCurentTime) > 0) {
                shift.setText(getCurentDay + " " + "-" + " " + "Shift 2");
            } else if (getCurentTime.compareTo(getTestTime2) > 0) {
                shift.setText(getCurentDay + "-" + " " + "Shift 3");
            }

            //Code shift
            TextView codeshitf = findViewById(R.id.tvcodeshift);
            if (shift.getText().toString().equalsIgnoreCase("Senin - Shift 1")) {
                codeshitf.setText("SS1");
            } else if (shift.getText().toString().equalsIgnoreCase("Senin - Shift 2")) {
                codeshitf.setText("SS2");
            } else if (shift.getText().toString().equalsIgnoreCase("Senin - Shift 3")) {
                codeshitf.setText("SS3");
            } else if (shift.getText().toString().equalsIgnoreCase("Selasa - Shift 1")) {
                codeshitf.setText("SlS1");
            } else if (shift.getText().toString().equalsIgnoreCase("Selasa - Shift 2")) {
                codeshitf.setText("SlS2");
            } else if (shift.getText().toString().equalsIgnoreCase("Selasa - Shift 3")) {
                codeshitf.setText("SlS3");
            } else if (shift.getText().toString().equalsIgnoreCase("Rabu - Shift 1")) {
                codeshitf.setText("RbS1");
            } else if (shift.getText().toString().equalsIgnoreCase("Rabu - Shift 2")) {
                codeshitf.setText("RbS2");
            } else if (shift.getText().toString().equalsIgnoreCase("Rabu - Shift 3")) {
                codeshitf.setText("RbS3");
            } else if (shift.getText().toString().equalsIgnoreCase("Kamis - Shift 1")) {
                codeshitf.setText("KmS1");
            } else if (shift.getText().toString().equalsIgnoreCase("Kamis - Shift 2")) {
                codeshitf.setText("KmS2");
            } else if (shift.getText().toString().equalsIgnoreCase("Kamis - Shift 3")) {
                codeshitf.setText("KmS3");
            } else if (shift.getText().toString().equalsIgnoreCase("Jumat - Shift 1")) {
                codeshitf.setText("JmS1");
            } else if (shift.getText().toString().equalsIgnoreCase("Jumat - Shift 2")) {
                codeshitf.setText("JmS1");
            } else if (shift.getText().toString().equalsIgnoreCase("Jumat - Shift 3")) {
                codeshitf.setText("JmS3");
            } else if (shift.getText().toString().equalsIgnoreCase("Sabtu - Shift 1")) {
                codeshitf.setText("SbS1");
            } else if (shift.getText().toString().equalsIgnoreCase("Sabtu - Shift 2")) {
                codeshitf.setText("SbS2");
            } else if (shift.getText().toString().equalsIgnoreCase("Sabtu - Shift 3")) {
                codeshitf.setText("SbS3");
            } else if (shift.getText().toString().equalsIgnoreCase("Minggu - Shift 1")) {
                codeshitf.setText("MgS1");
            } else if (shift.getText().toString().equalsIgnoreCase("Minggu - Shift 2")) {
                codeshitf.setText("MgS2");
            } else if (shift.getText().toString().equalsIgnoreCase("Minggu - Shift 3")) {
                codeshitf.setText("MgS3");
            }

        }

        if (id == R.id.action_seq) {
            pref = getSharedPreferences("Noprod", MODE_PRIVATE);  //prodno
            String tvnoprod = tvNo_Prod1.getText().toString();
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("tvnoprod", tvnoprod);
            editor.commit();

            pref = getSharedPreferences("Nmprod", MODE_PRIVATE);
            String tvnmprod = tvNm_prod1.getText().toString();
            SharedPreferences.Editor editor1 = pref.edit();
            editor1.putString("tvnmprod", tvnmprod);
            editor1.commit();

            pref = getSharedPreferences("StsProd", MODE_PRIVATE);
            String tvstsprod = tvSts_Prod1.getText().toString();
            SharedPreferences.Editor editor2 = pref.edit();
            editor2.putString("tvstsprod", tvstsprod);
            editor2.commit();

            pref = getSharedPreferences("docNum", MODE_PRIVATE);
            String tvdocnum = tvNo_doc1.getText().toString();
            SharedPreferences.Editor editor3 = pref.edit();
            editor3.putString("tvdocnum", tvdocnum);
            editor3.commit();

            pref = getSharedPreferences("Itemcode", MODE_PRIVATE);
            String tvitemcode = tvprod1.getText().toString();
            SharedPreferences.Editor editor4 = pref.edit();
            editor4.putString("tvitemcode", tvitemcode);
            editor4.commit();

            pref = getSharedPreferences("RouteCode", MODE_PRIVATE);
            String tvroutecode = tvRoute_Code1.getText().toString();
            SharedPreferences.Editor editor5 = pref.edit();
            editor5.putString("tvroutecode", tvroutecode);
            editor5.commit();

            pref = getSharedPreferences("RouteName", MODE_PRIVATE);
            String tvroutename = tvRoute_Code2.getText().toString();
            SharedPreferences.Editor editor6 = pref.edit();
            editor6.putString("tvroutename", tvroutename);
            editor6.commit();

            pref = getSharedPreferences("ProdPlanQty", MODE_PRIVATE);
            String tvprodplanqty = tvQty_rencProd1.getText().toString();
            SharedPreferences.Editor editor7 = pref.edit();
            editor7.putString("tvprodplanqty", tvprodplanqty);
            editor7.commit();

            pref = getSharedPreferences("Sequence", MODE_PRIVATE);
            String tvsequence = tvSquence1.getText().toString();
            SharedPreferences.Editor editor8 = pref.edit();
            editor8.putString("tvsequence", tvsequence);
            editor8.commit();

            pref = getSharedPreferences("SequenceQty", MODE_PRIVATE);
            String tvsequenceqty = tvSquence_Qty1.getText().toString();
            SharedPreferences.Editor editor9 = pref.edit();
            editor9.putString("tvsequenceqty", tvsequenceqty);
            editor9.commit();

            pref = getSharedPreferences("TglMulai", MODE_PRIVATE);
            String tvtglmulai = tvTgl_mulai1.getText().toString();
            SharedPreferences.Editor editor10 = pref.edit();
            editor10.putString("tvtglmulai", tvtglmulai);
            editor10.commit();

            pref = getSharedPreferences("jamMulai", MODE_PRIVATE);
            String tvjammulai = tvJam_mulai1.getText().toString();
            SharedPreferences.Editor editor11 = pref.edit();
            editor11.putString("tvjammulai", tvjammulai);
            editor11.commit();

            pref = getSharedPreferences("Workcenter", MODE_PRIVATE);
            String tvwc = tvwc1.getText().toString();
            SharedPreferences.Editor editor12 = pref.edit();
            editor12.putString("workcenter", tvwc);
            editor12.commit();

            pref = getSharedPreferences("userId", MODE_PRIVATE);
            String tvuserid = tvusername2.getText().toString();
            SharedPreferences.Editor editor13 = pref.edit();
            editor13.putString("tvuserid", tvuserid);
            editor13.commit();

            pref = getSharedPreferences("Shift", MODE_PRIVATE);
            String tvshift = tvShift1.getText().toString();
            SharedPreferences.Editor editor14 = pref.edit();
            editor14.putString("tvshift", tvshift);
            editor14.commit();

            pref = getSharedPreferences("Codeshift", MODE_PRIVATE);
            String tvcodeshift1 = tvcodeshift.getText().toString();
            SharedPreferences.Editor editor15 = pref.edit();
            editor15.putString("tvcodeshift1", tvcodeshift1);
            editor15.commit();


            startActivity(new Intent(getApplicationContext(), AddSeqActivity.class));

        } else {
            Toast.makeText(getApplicationContext(), "Pilih Productorder dan Sequence dahulu", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void LastDocnum() {
        if (adapter3 != null)
            adapter3.clearAll();

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

//                                            String S = "S";
//                                            String nodoc = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
//                                            String docnum = header.getDocNum().substring(9);
//                                            String AN = "" + (Integer.parseInt(docnum) + 1);
//                                            Log.e("aaannn", AN);
//                                            String Nol = "";
//
//                                            if (AN.length() == 1) {
//                                                Nol = "00";
//                                            } else if (AN.length() == 2) {
//                                                Nol = "0";
//                                            } else if (AN.length() == 3) {
//                                                Nol = "";
//                                            }
//
//                                            tvNo_doc1.setText(S + nodoc + Nol + AN);

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

//                                        if (AN.length() == 1) {
//                                                Nol = "00";
//                                            } else if (AN.length() == 2) {
//                                                Nol = "0";
//                                            } else if (AN.length() == 3) {
//                                                Nol = "";
//                                            }


//                                        if (AN.length() == 1 ) {
//                                            Nol = "00";
//
//                                        } else if (AN.length() == 2) {
//                                            Nol = "0";
//                                        } else if (AN.length() == 3) {
//                                            Nol = "";
//                                        }

                                            //masih salah

                                            if (bulan.equals(bulankmrn2)) {
                                                if (AN.length() == 1) {
                                                    Nol = "00";

                                                } else if (AN.length() == 2) {
                                                    Nol = "0";
                                                } else if (AN.length() == 3) {
                                                    Nol = "";
                                                }

                                                tvNo_doc1.setText(S + tahun + bulan + hari + Nol + AN);
                                            } else if (bulan != bulankmrn2) {
                                                String AN1 = "001";
                                                tvNo_doc1.setText(S + tahun + bulan + hari + Nol + AN1);
                                            }



                                        }
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter3.addAll(results);
                        }

                        @Override
                        public void onError(ANError anError) {

                        }
                    });
        }
    }

    //    barcode
    private void searchBarcode(final String docnum1, final String seq1) {

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results1) {
            text = text + c.getAddress();

            Log.e("docnumbaru", tvNo_Prod1.getText().toString());
            Log.e("seq11", tvSquence1.getText().toString());
            prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
            prf.getString("workcenter", null);
            Log.e("workcenter1111 = ", prf.getString("workcenter", null));

//        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/poscan?wccode=" + prf.getString("workcenter", null) + "&DocNum=" + docnum1 + "&seq=" + seq1)
            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/poscan?wccode=" + prf.getString("workcenter", null) + "&DocNum=" + docnum1 + "&seq=" + seq1)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            List<Poscan> result = new ArrayList<>();
                            try {
                                Log.e("scanbarcode", response.toString(1));

                                if (result != null)
                                    result.clear();

                                String message = response.getString("message");

                                if (message.equals("Scan were found")) {
                                    String records = response.getString("data");

                                    JSONArray dataArr = new JSONArray(records);

                                    if (dataArr.length() > 0) {
                                        for (int i = 0; i < dataArr.length(); i++) {
                                            Poscan poscan = gson.fromJson(dataArr.getJSONObject(i).toString(), Poscan.class);
                                            result.add(poscan);
//                                        tvNo_prod1.setText(String.valueOf(productorder.getDocNum()));
                                            tvprod1.setText(poscan.getItemCode());
                                            tvNm_prod1.setText(poscan.getItemName());
                                            tvRoute_Code1.setText(poscan.getCode());
                                            tvRoute_Code2.setText(poscan.getName());
                                            tvQty_rencProd1.setText(poscan.getPlannedQty().replace(".000000", ""));
                                            tvSts_Prod1.setText(poscan.getStatus());
                                            tvSquence_Qty1.setText(poscan.getUQuantity().replace(".000000", ""));
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
}
