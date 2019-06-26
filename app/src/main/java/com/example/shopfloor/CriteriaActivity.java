package com.example.shopfloor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Activity.RejectActivity;
import com.example.shopfloor.Adapter.CriteriaAdapter;
import com.example.shopfloor.Adapter.ProductorderAdapter;
import com.example.shopfloor.Models.Criteria;
import com.example.shopfloor.Utils.GlobalVars;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CriteriaActivity extends AppCompatActivity {

    SharedPreferences prf, pref;
    private  TextView tvnoprod1;
    private RecyclerView rv;
    private Gson gson;
    private List<Criteria> list;
    private Criteria criteria;
    private CriteriaAdapter adapter;
    private TextView tvtglsel1;
    private TextView tvjamsel1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criteria);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);
        Toolbar topToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        gson = new Gson();
        list = new ArrayList<>();
        rv = findViewById(R.id.rvActualCrit);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        loadData();
        adapter = new CriteriaAdapter(this);
        rv.setAdapter(adapter);

        tvtglsel1 = findViewById(R.id.tvtglsel1);
        tvjamsel1 = findViewById(R.id.tvjamsel1);

        /************************************************/
        prf = getSharedPreferences("Itemcode", MODE_PRIVATE);

        /************qtyin****************************/
        TextView inqty = findViewById(R.id.tvInputQty1);
        prf = getSharedPreferences("inQty", MODE_PRIVATE);
        inqty.setText(prf.getString("inqty", null));

        /***************qtyout*****************************/
        TextView outqty = findViewById(R.id.tvOutputQty1);
        prf = getSharedPreferences("outQty", MODE_PRIVATE);
        outqty.setText(prf.getString("outqty", null));

        /****************workcenter************************/
        TextView workcenter = findViewById(R.id.tvWorkcenter);
        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        workcenter.setText(prf.getString("workcenter", null));

        /*****************noprod***********************************/
        tvnoprod1 = findViewById(R.id.tvnoprod1);
        TextView tvnoprod = findViewById(R.id.tvnoprod1);
        prf = getSharedPreferences("Noprod", MODE_PRIVATE);
        tvnoprod.setText(prf.getString("tvnoprod", null));

        /******************nmprod***********************************/
        TextView tvnmprod = findViewById(R.id.tvnmprod1);
        prf = getSharedPreferences("Nmprod", MODE_PRIVATE);
        tvnmprod.setText(prf.getString("tvnmprod", null));

        /*************************sequence******************************/
        TextView tvsequence = findViewById(R.id.tvsequence1);
        prf = getSharedPreferences("Sequence", MODE_PRIVATE);
        tvsequence.setText(prf.getString("tvsequence", null));

        /********************sequenceqty********************************/
        TextView tvsequenceqty = findViewById(R.id.tvseqqty1);
        prf = getSharedPreferences("SequenceQty", MODE_PRIVATE);
        tvsequenceqty.setText(prf.getString("tvsequenceqty", null));

        /**********************tanggal selesai*******************************/
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        TextView tgl = findViewById(R.id.tvtglsel1);
        tgl.setText(date);

        /***********************jam selesai****************************************/
        String jam = new SimpleDateFormat("hh:mm", Locale.getDefault()).format(new Date());
        TextView jam_selesai = findViewById(R.id.tvjamsel1);
        jam_selesai.setText(jam);
    }

    private void loadData() {

        if (adapter != null)
            adapter.clearAll();

        prf = getSharedPreferences("Noprod", MODE_PRIVATE);
        Log.e("noprod krityeria = ", prf.getString("tvnoprod", null));

        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        Log.e("workcenter kriteria = ", prf.getString("workcenter", null));

        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/criteria")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Criteria> results = new ArrayList<>();
                        try {
                            Log.e("resp", response.toString(1));

                            if (results != null)
                                results.clear();

                            String message = response.getString("message");

                            if (message.equals("Criteria were found")) {
                                String records = response.getString("data");

                                JSONArray dataArr = new JSONArray(records);

                                if (dataArr.length() > 0) {
                                    for (int i = 0; i < dataArr.length(); i++) {
                                        Criteria criteria = gson.fromJson(dataArr.getJSONObject(i).toString(), Criteria.class);
                                        results.add(criteria);
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.addAll(results);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private void coba() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("tanggalSelesai", tvtglsel1.getText().toString());
        }catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(GlobalVars.BASE_IP +"indes.php/simpanheader")
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
                            Toast.makeText(getApplicationContext(), "JSONExceptions" +e, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), "Gagal menambah data", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_criteria, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_crit) {


            pref = getSharedPreferences("Jamsel", MODE_PRIVATE);
            pref = getSharedPreferences("Tglsel", MODE_PRIVATE);

            String jamsel = tvjamsel1.getText().toString();
            String tglsel = tvtglsel1.getText().toString();

            SharedPreferences.Editor editor = pref.edit();
            editor.putString("tglsel", tglsel);
            editor.putString("jamsel", jamsel);
            editor.commit();
            startActivity(new Intent(getApplicationContext(), RejectActivity.class));
//            Toast.makeText(getApplicationContext(), "Criteria tidak boleh kosong", Toast.LENGTH_LONG).show();

        }
        return super.onOptionsItemSelected(item);
    }

}
