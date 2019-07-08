package com.example.shopfloor.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
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
import com.example.shopfloor.Adapter.CriteriaAdapter;
import com.example.shopfloor.Models.Criteria;
import com.example.shopfloor.Models.Reject;
import com.example.shopfloor.R;
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

public class CriteriaQCActivity extends AppCompatActivity {

    SharedPreferences prf, pref;
    TextView tvtglsel1;
    TextView tvjamsel1;
    TextView tvInputQty3;
    TextView tvdocentry3;
    private TextView tvdocnum5;
    private TextView tvnoprod1;
    private TextView tvprodcode;
    private TextView tvprodplanqty2;
    private TextView tvprodstatus1;
    private TextView tvroutecode1;
    private TextView tvroutename1;
    private TextView tvtglmulai1;
    private TextView tvjammulai1;
    private TextView tvnmprod1;
    private TextView tvsequence1;
    private TextView tvseqqty1;
    private TextView tvOutputQty1;
    private TextView tvWorkcenter;
    private TextView tvstatus2;
    private TextView tvdocsts;
    private TextView tvposted6;
    private TextView tvqcname3;
    private TextView tvusername7;
    private TextView tvdocsts1;
    private RecyclerView rv;
    private CriteriaAdapter adapter;
    private Gson gson;
    private Criteria criteria;
    private List<Criteria> list;

    private static final String TAG = "MyActivity";

    /***************criteria********/
    public static TextView tvcriteria1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criteria_qc);

        tvtglsel1 = findViewById(R.id.tvtglsel1);
        tvjamsel1 = findViewById(R.id.tvjamsel1);
        tvInputQty3 = findViewById(R.id.tvInputQty3);
        tvdocentry3 = findViewById(R.id.tvdocentry3);
        tvdocnum5 = findViewById(R.id.tvdocnum5);
        tvnoprod1 = findViewById(R.id.tvnoprod1);
        tvprodcode = findViewById(R.id.tvprodcode);
        tvprodplanqty2 = findViewById(R.id.tvprodplanqty2);
        tvprodstatus1 = findViewById(R.id.tvprodstatus1);
        tvroutecode1 = findViewById(R.id.tvroutecode1);
        tvroutename1 = findViewById(R.id.tvroutename1);
        tvtglmulai1 = findViewById(R.id.tvtglmulai1);
        tvjammulai1 = findViewById(R.id.tvjammulai1);
        tvnmprod1 = findViewById(R.id.tvnmprod1);
        tvsequence1 = findViewById(R.id.tvsequence1);
        tvseqqty1 = findViewById(R.id.tvseqqty1);
        tvOutputQty1 = findViewById(R.id.tvOutputQty1);
        tvWorkcenter = findViewById(R.id.tvWorkcenter);
        tvstatus2 = findViewById(R.id.tvstatus2);
        tvdocsts = findViewById(R.id.tvdocsts);
        tvposted6 = findViewById(R.id.tvposted6);
        tvqcname3 = findViewById(R.id.tvqcname3);
        tvusername7 = findViewById(R.id.tvusername7);
        tvcriteria1 = findViewById(R.id.tvcriteria1);
        tvdocsts1 = findViewById(R.id.tvdocsts1);
        /*************************************************************/

        /*******************Ambil data criteria************************/
        gson = new Gson();
        list = new ArrayList<>();
        rv = findViewById(R.id.rvActualCrit);
        adapter = new CriteriaAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
        loadData();
        /*******************************************************************/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);
        Toolbar topToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolbar);

        TextView tvinqty = findViewById(R.id.tvInputQty3);
        prf = getSharedPreferences("inQty", MODE_PRIVATE);
        tvinqty.setText(prf.getString("tvinqty", null));

        TextView tvnoprod = findViewById(R.id.tvnoprod1);
        prf = getSharedPreferences("Noprod", MODE_PRIVATE);
        tvnoprod.setText(prf.getString("tvnoprod", null));

        TextView tvoutqty = findViewById(R.id.tvOutputQty1);
        prf = getSharedPreferences("outQty", MODE_PRIVATE);
        tvoutqty.setText(prf.getString("tvoutqty", null));

        TextView tvprodname = findViewById(R.id.tvnmprod1);
        prf = getSharedPreferences("prodName", MODE_PRIVATE);
        tvprodname.setText(prf.getString("tvprodname", null));

        TextView tvsequence = findViewById(R.id.tvsequence1);
        prf = getSharedPreferences("Sequence", MODE_PRIVATE);
        tvsequence.setText(prf.getString("tvsequence", null));

        TextView tvseqqty = findViewById(R.id.tvseqqty1);
        prf = getSharedPreferences("SequenceQty", MODE_PRIVATE);
        tvseqqty.setText(prf.getString("tvseqqty", null));

        TextView tvwc = findViewById(R.id.tvWorkcenter);
        prf = getSharedPreferences("workCenter", MODE_PRIVATE);
        tvwc.setText(prf.getString("tvworkcenter", null));

        TextView tvdocentry = findViewById(R.id.tvdocentry3);
        prf = getSharedPreferences("docEntry", MODE_PRIVATE);
        tvdocentry.setText(prf.getString("tvdocentry", null));

        TextView tvdocnum = findViewById(R.id.tvdocnum5);
        prf = getSharedPreferences("docNum", MODE_PRIVATE);
        tvdocnum.setText(prf.getString("tvdocnum", null));

        TextView tvprodcode = findViewById(R.id.tvprodcode);
        prf = getSharedPreferences("prodCode", MODE_PRIVATE);
        tvprodcode.setText(prf.getString("tvprodcode", null));

        TextView tvprodplanqty = findViewById(R.id.tvprodplanqty2);
        prf = getSharedPreferences("prodPlanQty", MODE_PRIVATE);
        tvprodplanqty.setText(prf.getString("tvprodplanqty", null));

        TextView tvprodstatus = findViewById(R.id.tvprodstatus1);
        prf = getSharedPreferences("prodStatus", MODE_PRIVATE);
        tvprodstatus.setText(prf.getString("tvprodstatus", null));

        TextView tvroutecode = findViewById(R.id.tvroutecode1);
        prf = getSharedPreferences("routeCode", MODE_PRIVATE);
        tvroutecode.setText(prf.getString("tvroutecode", null));

        TextView tvroutename = findViewById(R.id.tvroutename1);
        prf = getSharedPreferences("routeName", MODE_PRIVATE);
        tvroutename.setText(prf.getString("tvroutename", null));

        TextView tvtglmulai = findViewById(R.id.tvtglmulai1);
        prf = getSharedPreferences("tanggalMulai", MODE_PRIVATE);
        tvtglmulai.setText(prf.getString("tvtglmulai", null));

        TextView tvjammulai = findViewById(R.id.tvjammulai1);
        prf = getSharedPreferences("jamMulai", MODE_PRIVATE);
        tvjammulai.setText(prf.getString("tvjammulai", null));

        TextView tvqcname = findViewById(R.id.tvqcname3);
        prf = getSharedPreferences("Qcname", MODE_PRIVATE);
        tvqcname.setText(prf.getString("tvqcname", null));

        TextView tvusername = findViewById(R.id.tvusername7);
        prf = getSharedPreferences("Username", MODE_PRIVATE);
        tvusername.setText(prf.getString("tvusername", null));

        TextView tvstatus = findViewById(R.id.tvstatus2);
        tvstatus.setText("Completed");

        TextView tvposted = findViewById(R.id.tvdocsts1);
        tvposted.setText("Pending1");

        TextView tvposted1 = findViewById(R.id.tvposted6);
        tvposted1.setText("1");


        //tanggal selesai
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        TextView tglsel = findViewById(R.id.tvtglsel1);
        tglsel.setText(date);

        //jam selesai
        String jam = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        TextView jamsel = findViewById(R.id.tvjamsel1);
        jamsel.setText(jam);


}

    public void editHeader() {  //kurang shift
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("docEntry", tvdocentry3.getText().toString());
            jsonObject.put("docNum", tvdocnum5.getText().toString());
            jsonObject.put("prodNo", tvnoprod1.getText().toString());
            jsonObject.put("prodCode", tvprodcode.getText().toString());
            jsonObject.put("prodName", tvnmprod1.getText().toString());
            jsonObject.put("prodPlanQty", tvprodplanqty2.getText().toString());
            jsonObject.put("prodStatus", tvprodstatus1.getText().toString());
            jsonObject.put("routeCode", tvroutecode1.getText().toString());
            jsonObject.put("routeName", tvroutename1.getText().toString());
            jsonObject.put("sequence", tvsequence1.getText().toString());
            jsonObject.put("sequenceQty", tvseqqty1.getText().toString());
//            jsonObject.put("shift", tvShift1.getText().toString());
            jsonObject.put("docDate", tvtglmulai1.getText().toString()); //masalah
            jsonObject.put("tanggalMulai", tvtglmulai1.getText().toString()); //masalah
            jsonObject.put("jamMulai", tvjammulai1.getText().toString());
            jsonObject.put("inQty", tvInputQty3.getText().toString());
            jsonObject.put("outQty", tvOutputQty1.getText().toString());
            jsonObject.put("workCenter", tvWorkcenter.getText().toString());
            jsonObject.put("tanggalSelesai",tvtglsel1.getText().toString());
            jsonObject.put("jamSelesai", tvjamsel1.getText().toString());
            jsonObject.put("status", tvstatus2.getText().toString());
            jsonObject.put("posted", tvposted6.getText().toString());
            jsonObject.put("UploadTime", tvjamsel1.getText().toString());
            jsonObject.put("QcName", tvqcname3.getText().toString());
            jsonObject.put("userId", tvusername7.getText().toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.put(GlobalVars.BASE_IP +"index.php/simpanheader?docEntry")
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
                            Toast.makeText(getApplicationContext(), "JSONExceptions"+ e, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), "Gagal menambah data", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void loadData() {
        if (adapter != null)
            adapter.clearAll();

        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/criteria")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Criteria> results = new ArrayList<>();
                        try {
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

    public void upCriteria() {
        JSONObject jsonObject = new JSONObject();
        CriteriaAdapter criteriaAdapter = new CriteriaAdapter(this);
        List<Criteria> data = adapter.getData();

        try {
            jsonObject.put("docEntry", tvdocentry3.getText().toString());

            jsonObject.put("criteria", tvcriteria1.toString());
            jsonObject.put("criteriaDesc", criteria.getUCriteriaName());
            jsonObject.put("valueType", criteria.getUValueType());
            jsonObject.put("standard", criteria.getUStandard());

        }catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(GlobalVars.BASE_IP +"index/criteria")
                .addJSONObjectBody(jsonObject)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String message = response.getString("message");
                            Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "JSONExceptions"+e, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), "Gagal menambah criteria", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_header, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.update_header) {
//            editHeader();
//            upCriteria();

            List<Criteria> data = adapter.getData();

            if (data != null) {
                for (Criteria x: data) {
                    Log.e(TAG,"onOptionsItemSelected: " + x.getUCriteria() + x.getUCriteriaName() + x.getUValueType() + x.getUStandard() );
                    JSONObject jsonObject = new JSONObject();
                    List<Criteria> crit = adapter.getData();
//                    try {
//                        jsonObject.put("docEntry", tvdocentry3.getText().toString());
//                        jsonObject.put("criteria", criteria.getUCriteria());
//                        jsonObject.put("criteria", tvcriteria1.toString());
//                        jsonObject.put("criteriaDesc", criteria.getUCriteriaName());
//                        jsonObject.put("valueType", criteria.getUValueType());
//                        jsonObject.put("standard", criteria.getUStandard());
//
//                    }catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                    AndroidNetworking.post(GlobalVars.BASE_IP +"index/criteria")
                            .addJSONObjectBody(jsonObject)
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        String message = response.getString("message");
//                                        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(getApplicationContext(), "JSONExceptions"+e, Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onError(ANError anError) {
                                    Toast.makeText(getApplicationContext(), "Gagal menambah criteria", Toast.LENGTH_SHORT).show();
                                }
                            });

                }
            }
            pref = getSharedPreferences("Docentry", MODE_PRIVATE);
            String tvdocentry = tvdocentry3.getText().toString();
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("tvdocentry", tvdocentry);
            editor.commit();

            pref = getSharedPreferences("Jamsel", MODE_PRIVATE);
            String tvjamsel = tvjamsel1.getText().toString();
            SharedPreferences.Editor editor1 = pref.edit();
            editor1.putString("tvjamsel", tvjamsel);
            editor1.commit();

            pref = getSharedPreferences("Tglsel", MODE_PRIVATE);
            String tvtglsel = tvtglsel1.getText().toString();
            SharedPreferences.Editor editor2 = pref.edit();
            editor2.putString("tvtglsel", tvtglsel);
            editor2.commit();

            pref = getSharedPreferences("Namaprod", MODE_PRIVATE);
            String tvnamaprod = tvnmprod1.getText().toString();
            SharedPreferences.Editor editor3 = pref.edit();
            editor3.putString("tvnamaprod", tvnamaprod);
            editor3.commit();

            pref = getSharedPreferences("Docsts", MODE_PRIVATE);
            String tvdocsts = tvdocsts1.getText().toString();
            SharedPreferences.Editor editor4 = pref.edit();
            editor4.putString("tvdocsts", tvdocsts);
            editor4.commit();

            pref = getSharedPreferences("Inqty", MODE_PRIVATE);
            String tvinqty = tvInputQty3.getText().toString();
            SharedPreferences.Editor editor5 = pref.edit();
            editor5.putString("tvinqty", tvinqty);
            editor5.commit();

            pref = getSharedPreferences("Outqty", MODE_PRIVATE);
            String tvoutqty = tvOutputQty1.getText().toString();
            SharedPreferences.Editor editor6 = pref.edit();
            editor6.putString("tvoutqty", tvoutqty);
            editor6.commit();

//            Intent intent = new Intent(getApplicationContext(), RejectActivity.class);
            startActivity(new Intent(getApplicationContext(), RejectActivity.class));
        }
    return super.onOptionsItemSelected(item);
    }

}
