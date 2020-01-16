package com.example.shopfloor.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.shopfloor.Adapter.OpenDocAdapter;
import com.example.shopfloor.Adapter.WorkcenterAdapter;
import com.example.shopfloor.Models.Header;
import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.Models.Workcenter;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.GlobalVars;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmResults;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class DetailOpenDocActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

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
    private TextView tvinqty4;
    private TextView tvworkcenter3;
    private TextView tvdocentry1;
    private TextView tvstatus;
    private TextView tvposted4;
    private TextView tvqcname1;
    private TextView tvusername5;
    private TextView tvcodeshift1;
    private OpenDocAdapter adapter;
    private Header header;
    private Gson gson;
    private WorkcenterAdapter workcenterAdapter;
    private TextView tvnamawc3;
    private TextView tvid1;
    private TextView tvmobileid;
    private Button bthapus;
    private Button btedit;
    private Button btnref;
    private Handler mHandler;
    private String TAG = DetailOpenDocActivity.class.getName();

    public SharedPreferences pref;
    SharedPreferences prf;
    String docentryy;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_open_doc);




        tvNo_doc1 = findViewById(R.id.tvNo_doc1);
        tvNo_prod1 = findViewById(R.id.tvNo_prod1);
        tvprod1 = findViewById(R.id.tvprod1);
        tvNm_prod1 = findViewById(R.id.tvNm_prod1);
        tvRoute_Code1 = findViewById(R.id.tvRoute_Code1);
        tvRoute_Code2 = findViewById(R.id.tvRoute_Code2);
        tvQty_rencProd1 = findViewById(R.id.tvQty_rencProd1);
        tvSts_prod1 = findViewById(R.id.tvSts_prod1);
        tvSquence1 = findViewById(R.id.tvSquence1);
        tvSquence_Qty1 = findViewById(R.id.tvSquence_Qty1);
        tvShift1 = findViewById(R.id.tvShift1);
        tvTgl_mulai1 = findViewById(R.id.tvTgl_mulai1);
        tvJam_mulai1 = findViewById(R.id.tvJam_mulai1);
        tvinqty4 = findViewById(R.id.tvinqty4);
        tvworkcenter3 = findViewById(R.id.tvworkcenter3);
        tvdocentry1 = findViewById(R.id.tvdocentry1);
        tvstatus = findViewById(R.id.tvstatus);
        tvposted4 = findViewById(R.id.tvposted4);
        tvqcname1 = findViewById(R.id.tvqcname1);
        tvusername5 = findViewById(R.id.tvusername5);
        tvcodeshift1 = findViewById(R.id.tvcodeshift1);
        tvnamawc3 = findViewById(R.id.tvnamawc3);
        tvid1 = findViewById(R.id.tvid1);
        tvmobileid = findViewById(R.id.tvmobileid);
        bthapus = findViewById(R.id.bthapus);
        btedit = findViewById(R.id.btedit);
        btnref = findViewById(R.id.btnref);


        adapter = new OpenDocAdapter(this);
        gson = new Gson();
        workcenterAdapter = new WorkcenterAdapter(this);

        //key dari opendocadapter
        header = getIntent().getParcelableExtra("key_opendoc");
        tvNo_doc1.setText(String.valueOf(header.getDocNum()));
        tvNo_prod1.setText(header.getProdNo());
        tvprod1.setText(header.getProdCode());
        tvNm_prod1.setText(header.getProdName());
        tvRoute_Code1.setText(header.getRouteCode());
        tvRoute_Code2.setText(header.getRouteName());
        tvQty_rencProd1.setText(header.getProdPlanQty().replace(".000000",""));
        tvSts_prod1.setText(header.getProdStatus());
        tvSquence1.setText(header.getSequence());
        tvSquence_Qty1.setText(header.getSequenceQty().replace(".000000",""));
        tvShift1.setText(header.getShiftName());
        tvTgl_mulai1.setText(header.getTanggalMulai().substring(0,10));
        tvJam_mulai1.setText(header.getJamMulai());
        tvinqty4.setText(header.getInQty());
        tvworkcenter3.setText(header.getWorkCenter());
        tvdocentry1.setText(String.valueOf(header.getDocEntry()));
        tvstatus.setText(header.getStatus());
        tvposted4.setText(String.valueOf(header.getPosted()));
        tvusername5.setText(header.getUserId());
        tvcodeshift1.setText(header.getShift());
        tvid1.setText(String.valueOf(header.getId()));
        tvmobileid.setText(header.getMobileId());



        TextView tvqcname = findViewById(R.id.tvqcname1);
        prf = getSharedPreferences("Qcname", MODE_PRIVATE);
        tvqcname.setText(prf.getString("tvqcname", null));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Open Document");


        TextView toolbarText = findViewById(R.id.toolbar_text);
        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText(getTitle());
            setSupportActionBar(toolbar);
        }
        Toolbar topToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolbar);

        loadData(tvworkcenter3.getText().toString());

        btedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), EditOpenDocActivity.class);
//                intent.putExtra("edit_doc", header);
//                startActivity(intent);
                Toasty.info(getApplicationContext(), "Sedang tahap pengembangan", Toasty.LENGTH_LONG).show();
            }
        });

        bthapus.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                deleteHeader(tvdocentry1.getText().toString());
            }
        });

        btnref.setOnClickListener(new View.OnClickListener() {
            private Handler mHandler;

            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(DetailOpenDocActivity.this,DetailOpenDocActivity.class);
//                startActivity(intent);
//                finish();

//                mHandler = new Handler();
//                m_Runnable.run();

                Toasty.info(getApplicationContext(), "Sedang tahap pengembangan", Toasty.LENGTH_LONG).show();
            }
        });


//        this.mHandler = new Handler();
//        m_Runnable.run();

    }

    private void updateTextView() {
        tvShift1.getText().toString();
        tvTgl_mulai1.getText().toString();
        tvcodeshift1.getText().toString();
    }


    private final Runnable m_Runnable = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(getApplicationContext(),"in runnable",Toast.LENGTH_SHORT).show();
//            tvShift1.setText(header.getShiftName());
//            tvTgl_mulai1.setText(header.getTanggalMulai().substring(0,10));
//            tvcodeshift1.setText(header.getShift());
            adapter.clearAll();
//            tvShift1.getText().toString();
//            tvTgl_mulai1.getText().toString();
//            tvcodeshift1.getText().toString();


//            adapter = new OpenDocAdapter(this);
//            gson = new Gson();
////            workcenterAdapter = new WorkcenterAdapter(this);
//
//            //key dari opendocadapter
//            header = getIntent().getParcelableExtra("key_opendoc");
//            tvNo_doc1.setText(String.valueOf(header.getDocNum()));
//            tvNo_prod1.setText(header.getProdNo());
//            tvprod1.setText(header.getProdCode());
//            tvNm_prod1.setText(header.getProdName());
//            tvRoute_Code1.setText(header.getRouteCode());
//            tvRoute_Code2.setText(header.getRouteName());
//            tvQty_rencProd1.setText(header.getProdPlanQty().replace(".000000",""));
//            tvSts_prod1.setText(header.getProdStatus());
//            tvSquence1.setText(header.getSequence());
//            tvSquence_Qty1.setText(header.getSequenceQty().replace(".000000",""));
//            tvShift1.setText(header.getShiftName());
//            tvTgl_mulai1.setText(header.getTanggalMulai().substring(0,10));
//            tvJam_mulai1.setText(header.getJamMulai());
//            tvinqty4.setText(header.getInQty());
//            tvworkcenter3.setText(header.getWorkCenter());
//            tvdocentry1.setText(String.valueOf(header.getDocEntry()));
//            tvstatus.setText(header.getStatus());
//            tvposted4.setText(String.valueOf(header.getPosted()));
//            tvusername5.setText(header.getUserId());
//            tvcodeshift1.setText(header.getShift());
//            tvid1.setText(String.valueOf(header.getId()));
//            tvmobileid.setText(header.getMobileId());

           mHandler.postDelayed(m_Runnable, 2000);
        }
    };

    public void deleteHeader(final String docentry) {

        new AlertDialog.Builder(DetailOpenDocActivity.this)
                .setTitle("Hapus")
                .setMessage("Anda yakin ingin menghapus?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        Realm realm = Realm.getDefaultInstance();
                        RealmResults<ServerModel> results = realm.where(ServerModel.class).findAll();
                        String text = "";
                        for (ServerModel c : results) {
                            text = text + c.getAddress();

                            TextView docentot = findViewById(R.id.tvdocentry1);
//                                    prf = getSharedPreferences("docentry", MODE_PRIVATE);
                            docentot.setText(String.valueOf(header.getDocEntry()));
                            Log.e(TAG, "docentot = " + docentot);
                            Log.e(TAG, "ip delete = " + c.getAddress() + "shopfloor2/index.php/simpanheader?docEntry=" + docentry );

                            AndroidNetworking.delete(c.getAddress() + "shopfloor2/index.php/simpanheader?docEntry=" +  docentry)
                                    .setPriority(Priority.MEDIUM)
                                    .build()
                                    .getAsJSONObject(new JSONObjectRequestListener() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                String message = response.getString("message");
                                                Toasty.success(getApplicationContext(), message, Toasty.LENGTH_LONG).show();
//                                                Intent intent = new Intent(getApplicationContext(), Open_DocActivity.class);
//
//                                               intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
//                                                startActivity(intent);
                                                finish();

                                            }catch (JSONException e) {
                                                e.printStackTrace();
                                                Toasty.error(getApplicationContext(), "JSONExceptions", Toasty.LENGTH_LONG).show();

                                            }
                                        }

                                        @Override
                                        public void onError(ANError anError) {
                                            Toasty.error(getApplicationContext(), "Gagal menghapus reject", Toast.LENGTH_LONG).show();

                                        }
                                    });

                        }


                    }
                }).show();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        pref = getSharedPreferences("docNum", MODE_PRIVATE);
        String tvnodoc = tvNo_doc1.getText().toString();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("tvnodoc", tvnodoc);
        editor.commit();

        pref = getSharedPreferences("inQty", MODE_PRIVATE);
        String tvinqty = tvinqty4.getText().toString();
        SharedPreferences.Editor editor1 = pref.edit();
        editor1.putString("tvinqty", tvinqty);
        editor1.commit();

        pref = getSharedPreferences("prodNo", MODE_PRIVATE);
        String tvprodno = tvNo_prod1.getText().toString();
        SharedPreferences.Editor editor2 = pref.edit();
        editor2.putString("tvprodno", tvprodno);
        editor2.commit();

        pref = getSharedPreferences("Sequence", MODE_PRIVATE);
        String tvsequence = tvSquence1.getText().toString();
        SharedPreferences.Editor editor3 = pref.edit();
        editor3.putString("tvsequence", tvsequence);
        editor3.commit();

        pref = getSharedPreferences("SequenceQty", MODE_PRIVATE);
        String tvseqqty = tvSquence_Qty1.getText().toString();
        SharedPreferences.Editor editor4 = pref.edit();
        editor4.putString("tvseqqty", tvseqqty);
        editor4.commit();

        pref = getSharedPreferences("prodName", MODE_PRIVATE);
        String tvprodname = tvNm_prod1.getText().toString();
        SharedPreferences.Editor editor5 = pref.edit();
        editor5.putString("tvprodname", tvprodname);
        editor5.commit();

        pref = getSharedPreferences("workCenter", MODE_PRIVATE);
        String tvworkcenter = tvworkcenter3.getText().toString();
        SharedPreferences.Editor editor6 = pref.edit();
        editor6.putString("tvworkcenter", tvworkcenter);
        editor6.commit();

        pref  = getSharedPreferences("docEntry", MODE_PRIVATE);
        String tvdocentry = tvdocentry1.getText().toString();
        SharedPreferences.Editor editor7 = pref.edit();
        editor7.putString("tvdocentry", tvdocentry);
        editor7.commit();

        pref = getSharedPreferences("prodCode", MODE_PRIVATE);
        String tvprodcode = tvprod1.getText().toString();
        SharedPreferences.Editor editor8 = pref.edit();
        editor8.putString("tvprodcode", tvprodcode);
        editor8.commit();

        pref = getSharedPreferences("prodPlanQty", MODE_PRIVATE);
        String tvprodplanqty = tvQty_rencProd1.getText().toString();
        SharedPreferences.Editor editor9 = pref.edit();
        editor9.putString("tvprodplanqty", tvprodplanqty);
        editor9.commit();

        pref = getSharedPreferences("prodStatus", MODE_PRIVATE);
        String tvprodstatus = tvSts_prod1.getText().toString();
        SharedPreferences.Editor editor10 = pref.edit();
        editor10.putString("tvprodstatus", tvprodstatus);
        editor10.commit();

        pref = getSharedPreferences("routeCode", MODE_PRIVATE);
        String tvroutecode = tvRoute_Code1.getText().toString();
        SharedPreferences.Editor editor11 = pref.edit();
        editor11.putString("tvroutecode", tvroutecode);
        editor11.commit();

        pref = getSharedPreferences("routeName", MODE_PRIVATE);
        String tvroutename = tvRoute_Code2.getText().toString();
        SharedPreferences.Editor editor12 = pref.edit();
        editor12.putString("tvroutename", tvroutename);
        editor12.commit();

        pref = getSharedPreferences("tanggalMulai", MODE_PRIVATE);
        String tvtglmulai = tvTgl_mulai1.getText().toString();
        SharedPreferences.Editor editor13 = pref.edit();
        editor13.putString("tvtglmulai", tvtglmulai);
        editor13.commit();

        pref = getSharedPreferences("jamMulai", MODE_PRIVATE);
        String tvjammulai = tvJam_mulai1.getText().toString();
        SharedPreferences.Editor editor14 = pref.edit();
        editor14.putString("tvjammulai", tvjammulai);
        editor14.commit();

        pref = getSharedPreferences("status", MODE_PRIVATE);
        String tvstatusp = tvstatus.getText().toString();
        SharedPreferences.Editor editor15 = pref.edit();
        editor15.putString("tvstatus", tvstatusp);
        editor15.commit();

        pref = getSharedPreferences("posted", MODE_PRIVATE);
        String tvposted = tvposted4.getText().toString();
        SharedPreferences.Editor editor16 = pref.edit();
        editor16.putString("tvposted", tvposted);
        editor16.commit();

        pref = getSharedPreferences("Qcname", MODE_PRIVATE);
        String tvqcname = tvqcname1.getText().toString();
        SharedPreferences.Editor editor17 = pref.edit();
        editor17.putString("tvqcname", tvqcname);
        editor17.commit();

        pref = getSharedPreferences("Username", MODE_PRIVATE);
        String tvusername = tvusername5.getText().toString();
        SharedPreferences.Editor editor18 = pref.edit();
        editor18.putString("tvusername", tvusername);
        editor18.commit();

        pref = getSharedPreferences("Shift", MODE_PRIVATE);
        String tvshift = tvShift1.getText().toString();
        SharedPreferences.Editor editor19 = pref.edit();
        editor19.putString("tvshift", tvshift);
        editor19.commit();

        pref = getSharedPreferences("Codeshift", MODE_PRIVATE);
        String tvcodesh = tvcodeshift1.getText().toString();
        SharedPreferences.Editor editor20 = pref.edit();
        editor20.putString("tvcodeshift", tvcodesh);
        editor20.commit();

        pref = getSharedPreferences("Namawc", MODE_PRIVATE);
        String tvnamawc = tvnamawc3.getText().toString();
        SharedPreferences.Editor editor21 = pref.edit();
        editor21.putString("tvnamawc", tvnamawc);
        editor21.commit();

        pref = getSharedPreferences("Id", MODE_PRIVATE);
        String tvid = tvid1.getText().toString();
        SharedPreferences.Editor editor22 = pref.edit();
        editor22.putString("tvid", tvid);
        editor22.commit();

//        startActivity(new Intent(getApplicationContext(), OutQtyDetActivity.class));
        startActivity(new Intent(getApplicationContext(), AddSeq1Activity.class));
        return true;
    }

    private void loadData(String codewc) {

        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/Namawc?code="+codewc)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Workcenter> result = new ArrayList<>();

                        try {
                            Log.e(" namaworkcenter = ",  response.toString(1));

                            String message = response.getString("message");

                            if (message.equals("namawc ketemu")) {
                                String records = response.getString("data");

                                JSONArray dataArr = new JSONArray(records);

                                if (dataArr.length() > 0) {
                                    for (int i = 0; i < dataArr.length(); i++) {
                                       Workcenter workcenter =  gson.fromJson(dataArr.getJSONObject(i).toString(), Workcenter.class);
                                       result.add(workcenter);

                                        tvnamawc3.setText(workcenter.getName());
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
