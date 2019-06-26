package com.example.shopfloor.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.ProductorderAdapter;
import com.example.shopfloor.Adapter.SequenceAdapter;
import com.example.shopfloor.Models.Productorder;
import com.example.shopfloor.Models.Sequence;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.GlobalVars;
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

public class Add_DocActivity extends AppCompatActivity {

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
    private SequenceAdapter adapter;
    private ProductorderAdapter adapter2;
    private RecyclerView rv, rv2;
    public EditText searchPO;
    public SharedPreferences pref;
    SharedPreferences prf;
    private TextView tvShift1;
    private TextView tvTgl_mulai1;




    private List<Productorder> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__doc);

        /**************Toolbar**********************************/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);
        Toolbar topToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolbar);
        /*******************************************************/

        gson = new Gson();

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


        tvSquence1 = findViewById(R.id.tvSquence1);
        tvSquence_Qty1 = findViewById(R.id.tvSquence_Qty1);

        dateView = findViewById(R.id.tvTgl_mulai1);

        //workcenter
        TextView tvwc = findViewById(R.id.tvwc1);
        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        tvwc.setText(prf.getString("workcenter", null));

        /*******************tanggal mulai***********************/
        /*calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showdate(year, month + 1, day);*/

//        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        TextView tgl_mulai = findViewById(R.id.tvTgl_mulai1);
        tgl_mulai.setText(date);

        /****************end**************************************/

        /*************no dokumen************************/
        int no;
        String S = "S";
        String nodoc = new SimpleDateFormat("yyyyMM", Locale.getDefault()).format(new Date());
        TextView nodoc1 = findViewById(R.id.tvNo_doc1);
        nodoc1.setText(S+nodoc);

        btn_Pilihprod = findViewById(R.id.btn_PilihProd);
        btn_Pilihprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String value = tvWC3.getText().toString();
                Intent intent = new Intent(Add_DocActivity.this, ProductorderListActivity.class);
                intent.putExtra(KEY_WC, value);
                startActivity(intent);*/

                 showDialog2(Add_DocActivity.this);

            }
        });

        btn_Pilihseq = findViewById(R.id.btn_PilihSeq);
        btn_Pilihseq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent iSeq = new Intent(getApplicationContext(), SequenceListActivity.class);
                startActivity(iSeq);*/
                if (tvNo_Prod1.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Pilih Produksi Terlebih dahulu", Toast.LENGTH_LONG).show();

                } else {
                    pref = getSharedPreferences("Docnum", MODE_PRIVATE);
                    String docnum = tvNo_prod1.getText().toString();
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("docnum", docnum);
                    editor.commit();
                    showDialog(Add_DocActivity.this);

                }
            }
        });
    }

    //showDialog
    public void showDialog(Activity activity) {

        dialog = new Dialog(activity);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_sequence_list);

        rv = findViewById(R.id.rvSequenceList);
        rv = dialog.findViewById(R.id.rvSequenceList);
        adapter = new SequenceAdapter(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        loadData();

        rv.setAdapter(adapter);

        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dialog.show();
    }

    //showDialog2
    public void showDialog2(Activity activity) {
        dialog = new Dialog(activity);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_productorder_list);

        gson = new Gson();
        list = new ArrayList<Productorder>();
        rv2 = findViewById(R.id.rvProductorderList);

        searchPO = findViewById(R.id.searchPO);
        rv2 = findViewById(R.id.rvProductorderList);
//        rv2.setHasFixedSize(true);

        rv2 = findViewById(R.id.rvProductorderList);
        rv2 = dialog.findViewById(R.id.rvProductorderList);
        adapter2 = new ProductorderAdapter(this);
        rv2.setAdapter(adapter2);

        rv2.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
       loadData2();

        rv2.setAdapter(adapter2);


        adapter2 = new ProductorderAdapter(this);
        rv2.setAdapter(adapter2);


//        addTextListener();

        rv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dialog.show();

    }

    public void loadData2() {
        if (adapter2 != null)
            adapter2.clearAll();

        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        prf.getString("workcenter", null);
        Log.e("workcenter = ", prf.getString("workcenter", null));

        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/productorder?wccode="+prf.getString("workcenter", null))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Productorder> result = new ArrayList<>();
                        try {
                            Log.e("resp2", response.toString(1));

                            if (result != null)
                                result.clear();

                            String message = response.getString("message");

                            if (message.equals("Production Order were found")){
                                String records = response.getString("data");

                                JSONArray dataArr = new JSONArray(records);

                                if (dataArr.length() > 0) {
                                    for (int i = 0; i < dataArr.length(); i++) {
                                        Productorder productorder = gson.fromJson(dataArr.getJSONObject(i).toString(), Productorder.class);
                                        result.add(productorder);
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

    public void addTextListener() {
        searchPO.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<Productorder> filteredList = new ArrayList<>();

                for (int i = 0; i < list.size(); i++) {
                    final String text = String.valueOf(list.get(i).getDocNum());
                    if (text.contains(query.toString())) {

                        filteredList.add(list.get(i));
                    }
                }

                rv2.setLayoutManager(new LinearLayoutManager(Add_DocActivity.this));
                adapter2 = new ProductorderAdapter(filteredList, Add_DocActivity.this);
                rv2.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
            }
        });
    }

    public void loadData() {
        if (adapter != null)
            adapter.clearAll();

        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        prf.getString("workcenter", null);

//        cobadocnum = findViewById(R.id.cobadocnum);
//        TextView docnum = findViewById(R.id.cobadocnum);
//        prf = getSharedPreferences("Docnum", MODE_PRIVATE);
//        docnum.setText(prf.getString("docnum", null));
//        Log.e("docnum = ", prf.getString("docnum", null));

       AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/sequence?wccode="+prf.getString("workcenter", null))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Sequence> result = new ArrayList<>();
                        try {
                            Log.e("resp", response.toString(1));

                            if (result != null)
                                result.clear();

                            String message = response.getString("message");

                            if (message.equals("Sequence were found")) {
                                String records = response.getString("data");

                                JSONArray dataArr = new JSONArray(records);

                                if (dataArr.length() > 0) {
                                    for (int i = 0; i < dataArr.length(); i++) {
                                        Sequence sequence = gson.fromJson(dataArr.getJSONObject(i).toString(), Sequence.class);
                                        result.add(sequence);
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

    //showDate
    public void showdate(int year, int month, int day) {
//        dateView.setText(new StringBuilder().append(day).append("-").append(month).append("-").append(year));

        //no dokumen
        tvNo_doc1.setText(new StringBuilder().append("S").append(year).append(month));
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

//            if (jam >= String.valueOf(jam("07:00") )) {
//                TextView shift = findViewById(R.id.tvShift1);
//                shift.setText("Shift 1");
//            }
        }

        if (id == R.id.action_seq && tvSquence_Qty1.length() != 0 && tvNo_Prod1.length() != 0) {
            pref = getSharedPreferences("Noprod",MODE_PRIVATE);  //prodno
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

            /*pref = getSharedPreferences("Nmprod", MODE_PRIVATE); //prodname
            pref = getSharedPreferences("Sequence", MODE_PRIVATE);
            pref = getSharedPreferences("SequenceQty", MODE_PRIVATE);
            pref = getSharedPreferences("Itemcode", MODE_PRIVATE);
            pref = getSharedPreferences("docNum", MODE_PRIVATE);
            pref = getSharedPreferences("ProdPlanQty", MODE_PRIVATE);
            pref = getSharedPreferences("StsProd", MODE_PRIVATE);
            pref = getSharedPreferences("RouteCode", MODE_PRIVATE);
            pref = getSharedPreferences("RouteName", MODE_PRIVATE);
            pref = getSharedPreferences("TglMulai", MODE_PRIVATE);

            String tvnmprod = tvNm_prod1.getText().toString();
            String tvnoprod = tvNo_Prod1.getText().toString();
            String tvsequence = tvSquence1.getText().toString();
            String tvsequenceqty = tvSquence_Qty1.getText().toString();
            String tvitemcode = tvprod1.getText().toString();
            String tvdocnum = tvNo_doc1.getText().toString();
            String tvprodplanqty = tvQty_rencProd1.getText().toString();
            String tvstsprod = tvSts_Prod1.getText().toString();
            String tvroutecode = tvRoute_Code1.getText().toString();
            String tvroutename = tvRoute_Code2.getText().toString();
            String tvtglmulai = tvTgl_mulai1.getText().toString();


            SharedPreferences.Editor editor10 = pref.edit();
            editor.putString("tvnoprod", tvnoprod);
            editor.putString("tvnmprod", tvnmprod);
            editor.putString("tvsequence", tvsequence);
            editor.putString("tvsequenceqty", tvsequenceqty);
            editor.putString("tvitemcode", tvitemcode);
            editor.putString("tvdocnum", tvdocnum);
            editor.putString("tvprodplanqty", tvprodplanqty);
            editor.putString("tvstsprod", tvstsprod);
            editor.putString("tvroutecode", tvroutecode);
            editor.putString("tvroutename", tvroutename);
            editor10.putString("tvtglmulai", tvtglmulai);
            editor10.commit();*/
            startActivity(new Intent(getApplicationContext(), AddSeqActivity.class));

        } else {
            Toast.makeText(Add_DocActivity.this, "Pilih Productorder dan Sequence dahulu", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}

