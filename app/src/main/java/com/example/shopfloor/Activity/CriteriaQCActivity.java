package com.example.shopfloor.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.CriteriaAdapter;
import com.example.shopfloor.Adapter.InputCriteriaAdapter;
import com.example.shopfloor.Models.Criteria;
import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.Models.Upcriteria;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.GlobalVars;
import com.example.shopfloor.Utils.RealmHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

public class CriteriaQCActivity extends AppCompatActivity {

    SharedPreferences prf, pref;
    TextView tvtglsel1;
    TextView tvjamsel1;
    TextView tvInputQty3;
    TextView tvdocentry3;
    private TextView tvdocnum5;
    private TextView tvnoprod0;
    private TextView tvprodcode;
    private TextView tvprodplanqty2;
    private TextView tvprodstatus1;
    private TextView tvroutecode1;
    private TextView tvroutename1;
    private TextView tvtglmulai1;
    private TextView tvjammulai1;
    private TextView tvnmprod1;

    private TextView tvseqqty1;
    private TextView tvOutputQty1;
    private TextView tvWorkcenter;
    private TextView tvstatus2;
    private TextView tvdocsts;
    private TextView tvposted6;
    private TextView tvqcname3;
    private TextView tvusername7;
    private TextView tvdocsts1;
    //    private EditText etactual1;
    private TextView tvshift2;
    private TextView tvcodeshift3;
    private String docnum = "";
    private RecyclerView rv;
    private CriteriaAdapter adapter;
    private InputCriteriaAdapter adapter1;
    private Gson gson;
    private Criteria criteria;
    private List<Criteria> list;
//    private static final String TAG = "MyActivity";
private static String TAG = CriteriaQCActivity.class.getSimpleName();
    private TextView tvsequence1;
    private TextView tvnamawc5;
    private TextView tvid4;
    private Button tvCrit;
    private TextView tvip9;

    private InputCriteriaAdapter inputCriteriaAdapter;


    private String docnum1;
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
    private String inqty;

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;

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
        tvnoprod0 = findViewById(R.id.tvnoprod0);
        tvprodcode = findViewById(R.id.tvprodcode);
        tvnmprod1 = findViewById(R.id.tvnmprod1);
        tvseqqty1 = findViewById(R.id.tvseqqty1);
        tvOutputQty1 = findViewById(R.id.tvOutputQty1);
        tvWorkcenter = findViewById(R.id.tvWorkcenter);
        tvstatus2 = findViewById(R.id.tvstatus2);
        tvdocsts = findViewById(R.id.tvdocsts);
        tvposted6 = findViewById(R.id.tvposted6);
        tvcriteria1 = findViewById(R.id.tvcriteria1);
        tvdocsts1 = findViewById(R.id.tvdocsts1);
        tvsequence1 = findViewById(R.id.tvsequence1);
        tvid4 = findViewById(R.id.tvid4);
        tvCrit = findViewById(R.id.tvCrit);


        inputCriteriaAdapter = new InputCriteriaAdapter(this);


        /*************************************************************/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Input Criteria");

        TextView toolbarText = findViewById(R.id.toolbar_text);
        if(toolbarText!=null && toolbar!=null) {
            toolbarText.setText(getTitle());
            setSupportActionBar(toolbar);
        }

        Toolbar topToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolbar);

        TextView tvinqty = findViewById(R.id.tvInputQty3);
        prf = getSharedPreferences("inQty", MODE_PRIVATE);
        tvinqty.setText(prf.getString("tvinqty", null));


        TextView tvwc = findViewById(R.id.tvWorkcenter);
        prf = getSharedPreferences("workCenter", MODE_PRIVATE);
        tvwc.setText(String.valueOf(prf.getString("tvworkcenter", null)));

        TextView tvnoprod = findViewById(R.id.tvnoprod0);
        prf = getSharedPreferences("Noprod", MODE_PRIVATE);
        tvnoprod.setText(String.valueOf(prf.getString("tvnoprod", null)));

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
        Log.e(TAG, "seqqty >> " + prf.getString("tvseqqty", null));

        TextView tvdocentry = findViewById(R.id.tvdocentry3);
        prf = getSharedPreferences("docEntry", MODE_PRIVATE);
        tvdocentry.setText(prf.getString("tvdocentry", null));

        loadCriteriaIsi(tvdocentry3.getText().toString());

        pref = getSharedPreferences("docNum", MODE_PRIVATE);
        docnum = pref.getString("tvnodoc", null);
        Log.e(TAG, "docnum2 = " + docnum);

        TextView tvprodcode = findViewById(R.id.tvprodcode);
        prf = getSharedPreferences("prodCode", MODE_PRIVATE);
        tvprodcode.setText(prf.getString("tvprodcode", null));

        pref = getSharedPreferences("prodPlanQty", MODE_PRIVATE);
        prodplanqty = pref.getString("tvprodplanqty", null);
        Log.e(TAG, "prodplan qty = " + prodplanqty);

        pref = getSharedPreferences("prodStatus", MODE_PRIVATE);
        stsprod = pref.getString("tvprodstatus", null);
        Log.e(TAG, "stsprod = " + stsprod);

        pref = getSharedPreferences("routeCode", MODE_PRIVATE);
        routecode = pref.getString("tvroutecode", null);
        Log.e(TAG, "routecode = " + routecode);

        pref = getSharedPreferences("routeName", MODE_PRIVATE);
        routname = pref.getString("tvroutename", null);
        Log.e(TAG, "routename = " + routname);

        pref = getSharedPreferences("tanggalMulai", MODE_PRIVATE);
        tglmulai = pref.getString("tvtglmulai", null);
        Log.e(TAG, "tgkmulai = " + tglmulai);

        pref = getSharedPreferences("jamMulai", MODE_PRIVATE);
        jammulai = pref.getString("tvjammulai", null);
        Log.e(TAG, "jam mulai = " + jammulai);

        pref = getSharedPreferences("Username", MODE_PRIVATE);
        username = pref.getString("tvusername", null);
        Log.e(TAG, "username = " + username);

        pref = getSharedPreferences("Shift", MODE_PRIVATE);
        shift = pref.getString("tvshift", null);
        Log.e(TAG, "shift = " + shift );

        pref = getSharedPreferences("Codeshift", MODE_PRIVATE);
        codeshift = pref.getString("tvcodeshift", null);
        Log.e(TAG, "code shifr = " + codeshift);

        TextView tvid = findViewById(R.id.tvid4);
        prf = getSharedPreferences("Id", MODE_PRIVATE);
        tvid.setText(String.valueOf(prf.getString("tvid", null)));

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

        tvCrit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String element = gson.toJson(

                        adapter.getData(),
                        new TypeToken<ArrayList<Upcriteria>>() {
                        }.getType());

                try {
                    JSONArray array = new JSONArray(element);
                    Log.e("arrraaayyyy = ", array.toString(1));

                    JSONArray newArr = new JSONArray();

                    for (int i = 0; i < array.length(); i++) {
                        Criteria criteria = gson.fromJson(array.getJSONObject(i).toString(), Criteria.class);

                        JSONObject object = new JSONObject();
                        object.put("hostHeadEntry", tvdocentry3.getText().toString());
                        object.put("id", tvid4.getText().toString());
                        object.put("criteria", criteria.getUCriteria());
                        object.put("criteriaDesc", criteria.getUCriteriaName());
                        object.put("standard", criteria.getUStandard());
                        object.put("lineNumber", i + 1);
                        object.put("actualResult", criteria.getActualResult());
                        object.put("valueType", criteria.getUValueType());

                        newArr.put(object);
                    }
                    Log.e("coba input = ", newArr.toString(1));

                    SimpanCriteria(newArr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        /*******************Ambil data criteria************************/
        gson = new Gson();
        list = new ArrayList<>();
        rv = findViewById(R.id.rvActualCrit);
        adapter = new CriteriaAdapter(this);
        adapter1 = new InputCriteriaAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
        loadData(tvWorkcenter.getText().toString(), tvnoprod0.getText().toString(), tvsequence1.getText().toString());
        /*******************************************************************/
    }

    private void loadData(String wccode, String docnum, String sequence) {

        if (adapter != null)
            adapter.clearAll();

        prf = getSharedPreferences("workCenter", MODE_PRIVATE);
        String.valueOf(prf.getString("tvworkcenter", null));
        Log.e("wccode = ", prf.getString("tvworkcenter", null));

        prf = getSharedPreferences("Noprod", MODE_PRIVATE);
        String.valueOf(prf.getString("tvnoprod", null));
        Log.e("docnum = ", prf.getString("tvnoprod", null));

        Log.e("workcenter69 = ", tvWorkcenter.getText().toString());
        Log.e("Docnum69 = ", tvnoprod0.getText().toString());
        Log.e("Sequence69", tvsequence1.getText().toString());

        Log.e("Sequence = ", GlobalVars.BASE_IP + "index.php/criteria?wccode=" + wccode + "&docNum=" + docnum + "&U_sequence=" + sequence + "");

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results1) {
            text = text + c.getAddress();


//            AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/criteria?wccode="+wccode + "&docNum="+docnum + "&seq="+sequence)
            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/criteria?wccode=" + wccode + "&docNum=" + docnum + "&seq=" + sequence)
                    .setTag(this)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            List<Criteria> results = new ArrayList<>();
                            Log.e(TAG, "onResponse11111111 = " +response);
                            try {
//                            Log.e(TAG,"onResponse = "+ response.toString());

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
                                            Log.e("onResponseeeeeeee ", dataArr.getJSONObject(i).toString());

                                            if (criteria.getUCriteriaName() == null) {
                                                tvCrit.setVisibility(tvCrit.GONE);


                                            }
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
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        List<Criteria> data = adapter.getData();
        EditText etactual1;
        TextView tvlinenumber;
        tvlinenumber = findViewById(R.id.tvlinenumber);
        etactual1 = (EditText) findViewById(R.id.etactual1);

        if (data != null) {
            for (Criteria x : data) {
                Log.e(TAG, "respone: " + x.getUCriteria() + " " + x.getUCriteriaName() + " " + x.getUStandard() + " " + x.getUValueType() + " " + x.getActualResult());

                if (x.getActualResult() != null) {
//                    JSONObject jsonObject = new JSONObject();
//                    try {
//                        jsonObject.put("docEntry", tvdocentry3.getText().toString());
//                        jsonObject.put("criteria", x.getUCriteria());
//                        jsonObject.put("criteriaDesc", x.getUCriteriaName().toString());
//                        jsonObject.put("standard", x.getUStandard());
//                        jsonObject.put("lineNumber", tvlinenumber.getText());
//                        jsonObject.put("actualResult", etactual1.getText());
//                        jsonObject.put("valueType", x.getUValueType());


                    if (id == R.id.action_seq && etactual1.getText().toString().equalsIgnoreCase("") ) {

                        Toasty.error(getApplicationContext(), "Criteria belum diisi", Toasty.LENGTH_LONG).show();
//                        Toast.makeText(this, "Criteria blm diisi", Toast.LENGTH_SHORT).show();

                    } else if(x.getUCriteriaName() == null) {
                        Toasty.error(getApplicationContext(), "Criteria Tidak ada", Toasty.LENGTH_LONG).show();

                    }else {

                        String element = gson.toJson(

                                adapter.getData(),
                                new TypeToken<ArrayList<Upcriteria>>() {
                                }.getType());

                        try {
                            JSONArray array = new JSONArray(element);
                            Log.e("arrraaayyyy = ", array.toString(1));

                            JSONArray newArr = new JSONArray();

                            for (int i = 0; i < array.length(); i++) {
                                Criteria criteria = gson.fromJson(array.getJSONObject(i).toString(), Criteria.class);

                                JSONObject object = new JSONObject();
                                object.put("hostHeadEntry", tvdocentry3.getText().toString());
                                object.put("id", tvid4.getText().toString());
                                object.put("criteria", criteria.getUCriteria());
                                object.put("criteriaDesc", criteria.getUCriteriaName());
                                object.put("standard", criteria.getUStandard());
                                object.put("lineNumber", i + 1);
                                object.put("actualResult", criteria.getActualResult());
                                object.put("valueType", criteria.getUValueType());

                                newArr.put(object);
                            }
                            Log.e("coba input = ", newArr.toString(1));

//                SimpanCriteria(newArr);
                        } catch (JSONException e) {
                            e.printStackTrace();
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

//                        pref = getSharedPreferences("Docnum", MODE_PRIVATE);
////                        String tvdocnum = tvdocnum5.getText().toString();
//                        docnum = pref.getString("docnum", null);
//                        SharedPreferences.Editor editor7 = pref.edit();
//                        editor7.putString("docnum", docnum);
//                        editor7.commit();
                        pref = getSharedPreferences("docNum", MODE_PRIVATE);
                        docnum = pref.getString("tvnodoc", null);
                        SharedPreferences.Editor editor7 = pref.edit();
                        editor7.putString("tvnodoc", docnum);
                        editor7.commit();

                        pref = getSharedPreferences("prodCode", MODE_PRIVATE);
                        String tvprodcode1 = tvprodcode.getText().toString();
                        SharedPreferences.Editor editor8 = pref.edit();
                        editor.putString("tvprodcode", tvprodcode1);
                        editor8.commit();

                        pref = getSharedPreferences("prodPlanQty", MODE_PRIVATE);
//                        String tvprodplanqty = tvprodplanqty2.getText().toString();
                        prodplanqty = pref.getString("tvprodplanqty", null);
                        SharedPreferences.Editor editor9 = pref.edit();
                        editor9.putString("tvprodplanqty", prodplanqty);
                        editor9.commit();

                        pref = getSharedPreferences("prodStatus", MODE_PRIVATE);
//                        String tvprodststus = tvprodstatus1.getText().toString();
                        stsprod = pref.getString("tvprodstatus", null);
                        SharedPreferences.Editor editor10 = pref.edit();
                        editor10.putString("tvprodstatus", stsprod);
                        editor10.commit();

                        pref = getSharedPreferences("routeCode", MODE_PRIVATE);
//                        String tvroutecode = tvroutecode1.getText().toString();
                        routecode = pref.getString("tvroutecode", null);
                        SharedPreferences.Editor editor11 = pref.edit();
                        editor11.putString("tvroutecode", routecode);
                        editor11.commit();

                        pref = getSharedPreferences("routeName", MODE_PRIVATE);
//                        String tvroutename = tvroutename1.getText().toString();
                        routname = pref.getString("tvroutename", null);
                        SharedPreferences.Editor editor12 = pref.edit();
                        editor12.putString("tvroutename", routname);
                        editor12.commit();

                        pref = getSharedPreferences("tanggalMulai", MODE_PRIVATE);
                        tglmulai = pref.getString("tvtglmulai", null);
                        SharedPreferences.Editor editor13 = pref.edit();
                        editor13.putString("tvdocdate", jammulai);
                        editor13.commit();

                        pref = getSharedPreferences("jamMulai", MODE_PRIVATE);
//                        String tvjammulai = tvjammulai1.getText().toString();
                        jammulai = pref.getString("tvjammulai", null);
                        SharedPreferences.Editor editor14 = pref.edit();
                        editor14.putString("tvjammulai", jammulai);
                        editor14.commit();

                        pref = getSharedPreferences("Workcenter", MODE_PRIVATE);
                        String tvworkcenter = tvWorkcenter.getText().toString();
                        SharedPreferences.Editor editor15 = pref.edit();
                        editor15.putString("tvworkcenter", tvworkcenter);
                        editor15.commit();

                        pref = getSharedPreferences("Status", MODE_PRIVATE);
                        String tvstatus = tvstatus2.getText().toString();
                        SharedPreferences.Editor editor16 = pref.edit();
                        editor16.putString("tvstatus", tvstatus);
                        editor16.commit();

                        pref = getSharedPreferences("Username", MODE_PRIVATE);
//                        String tvusername = tvusername7.getText().toString();
                        username = pref.getString("tvusername", null);
                        SharedPreferences.Editor editor17 = pref.edit();
                        editor17.putString("tvusername", username);
                        editor17.commit();

                        pref = getSharedPreferences("Shift", MODE_PRIVATE);
//                        String tvshift = tvshift2.getText().toString();
                        shift = pref.getString("tvshift", null);
                        SharedPreferences.Editor editor19 = pref.edit();
                        editor19.putString("tvshift", shift);
                        editor19.commit();

                        pref = getSharedPreferences("Codeshift", MODE_PRIVATE);
//                        String tvcodesh = tvcodeshift3.getText().toString();
                        codeshift = pref.getString("tvcodeshift", null);
                        SharedPreferences.Editor editor20 = pref.edit();
                        editor20.putString("tvcodeshift", codeshift);
                        editor20.commit();

                        pref = getSharedPreferences("Id", MODE_PRIVATE);
                        String tvid = tvid4.getText().toString();
                        SharedPreferences.Editor editor22 = pref.edit();
                        editor22.putString("tvid", tvid);
                        editor22.commit();

                        pref = getSharedPreferences("SequenceQty", MODE_PRIVATE);
                        String tvseqqty = tvseqqty1.getText().toString();
                        SharedPreferences.Editor editor23 = pref.edit();
                        editor23.putString("tvsequenceqty", tvseqqty);
                        editor23.commit();

//            Intent intent = new Intent(getApplicationContext(), RejectActivity.class);
                        startActivity(new Intent(getApplicationContext(), RejectActivity.class));

                    }

                }
            }


        }
//

        return super.onOptionsItemSelected(item);
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
                                Log.e(TAG,"criteria = "+ response.toString(1));

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

                                            if (upcriteria.getActualResult() == null) {

                                                prf = getSharedPreferences("workCenter", MODE_PRIVATE);
                                                String.valueOf(prf.getString("tvworkcenter", null));
                                                Log.e(TAG+"wccode22 = ", prf.getString("tvworkcenter", null));

                                                prf = getSharedPreferences("Noprod", MODE_PRIVATE);
                                                String.valueOf(prf.getString("tvnoprod", null));
                                                Log.e(TAG+"docnum22 = ", prf.getString("tvnoprod", null));

                                                TextView tvsequence = findViewById(R.id.tvsequence1);
                                                prf = getSharedPreferences("Sequence", MODE_PRIVATE);
                                                tvsequence.setText(prf.getString("tvsequence", null));
                                                Log.e(TAG, "seqyence if " + prf.getString("tvsequence", null));

                                            } else {
                                                Toasty.info(CriteriaQCActivity.this, "Criteria Sudah di input, Lanjut saja", Toast.LENGTH_SHORT).show();
                                                tvCrit.setVisibility(tvCrit.GONE);
//                                                String sudah ada
                                            }
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



    public void SimpanCriteria(JSONArray jsonArray) {

        List<Criteria> data = adapter.getData();
        EditText etactual1;
        TextView tvlinenumber;
        tvlinenumber = findViewById(R.id.tvlinenumber);
        etactual1 = findViewById(R.id.etactual1);

//        if (data != null) {
//            for (Criteria x : data) {
//                Log.e(TAG, "respone: " + x.getUCriteria() + " " + x.getUCriteriaName() + " " + x.getUStandard() + " " + x.getUValueType() + " " + x.getActualResult());
//                if (x.getActualResult() != null) {
//                    JSONObject jsonObject = new JSONObject();
//                    try {
//                        jsonObject.put("docEntry", tvdocentry3.getText().toString());
//                        jsonObject.put("criteria", x.getUCriteria());
//                        jsonObject.put("criteriaDesc", x.getUCriteriaName().toString());
//                        jsonObject.put("standard", x.getUStandard());
//                        jsonObject.put("lineNumber", tvlinenumber.getText());
//                        jsonObject.put("actualResult", etactual1.getText());
//                        jsonObject.put("valueType", x.getUValueType());
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }


        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results1) {
            text = text + c.getAddress();


//                    AndroidNetworking.post(GlobalVars.BASE_IP + "index.php/upcriteria")
            AndroidNetworking.post(c.getAddress() + "shopfloor2/index.php/upcriteria")
                    .addJSONArrayBody(jsonArray)
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
                                Toast.makeText(getApplicationContext(), "JSONEXceptions" + e, Toast.LENGTH_SHORT).show();
                            }
                        }


                        @Override
                        public void onError(ANError anError) {
                            Toasty.error(getApplicationContext(), "Gagal menambah Criteria", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
}



