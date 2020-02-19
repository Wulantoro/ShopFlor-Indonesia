package com.example.shopfloor.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.OpenDocAdapter;
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
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class Open_DocActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Calendar calendar;
    private int year, month, day;
    private TextView dateView;
    private TextView tvworkcenter0;
    private TextView tvqcname0;
    private TextView tvtanggal1;
    private Button btnPlhtgl;
    private TextView tvnamawc0;

    public String str ="";
    Character op = 'q';
    float i,num,numtemp;

    private RecyclerView rv;
    private OpenDocAdapter adapter;
    private Gson gson;
    private List<Header> list;
    private Header header;
    private Context context;
    private SharedPreferences prf, pref;
    private TextView tvip6;

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open__doc);

        tvworkcenter0 = findViewById(R.id.tvworkcenter0);
        tvqcname0 = findViewById(R.id.tvqcname0);
        btnPlhtgl = findViewById(R.id.btnPlhtgl);
        tvtanggal1 = findViewById(R.id.tvtanggal1);
        tvnamawc0 = findViewById(R.id.tvnamawc0);
        tvip6 = findViewById(R.id.tvip6);

        TextView tvqcname = findViewById(R.id.tvqcname0);
        prf = getSharedPreferences("Qcname", MODE_PRIVATE);
        tvqcname.setText(prf.getString("tvqcname", null));

        TextView tvnamawc = findViewById(R.id.tvnamawc0);
        prf = getSharedPreferences("keynamawc", MODE_PRIVATE);
        tvnamawc.setText(prf.getString("keynamawc1", null));

//        TextView tvipadd = findViewById(R.id.tvip6);
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
        tvip6.setText(text);




        /************workcenter harus selalu berada di bawah biar tetap di baca*****************************/
        TextView tvworkcenter = findViewById(R.id.tvworkcenter0);
        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        tvworkcenter.setText(prf.getString("workcenter1", null));
        /******************************************************/
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        btnPlhtgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        /**************tampil data********************/
        gson = new Gson();
        list = new ArrayList<>();
        rv = findViewById(R.id.rvOpenDocList);
        adapter = new OpenDocAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);

        pref = getSharedPreferences("Qcname", MODE_PRIVATE);
        String tvqcname1 = tvqcname0.getText().toString();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("tvqcname", tvqcname1);
        editor.commit();
        loadData(tvworkcenter0.getText().toString());

        adapter = new OpenDocAdapter(list, this);
        rv.setAdapter(adapter);
        addTextListener();

    }

    private void showDialog() {
        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);


                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                tvtanggal1.setText(dateFormatter.format(newDate.getTime()));

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void loadData(String wccode) {

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Sedang proses");
        progress.setTitle("Sedang Proses");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        if (adapter != null) {
            adapter.clearAll();

//            prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
//            Log.e("ip = ", prf.getString("workcenter", null));

            prf = getSharedPreferences("Ip", MODE_PRIVATE);
            Log.e("ip = ", prf.getString("tvip", null));

            Realm realm = Realm.getDefaultInstance();
            RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
            String text = "";
            for (ServerModel c : results1) {
                text = text + c.getAddress();

//            AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/simpanheader?workCenter="+prf.getString("workcenter", null))
//            AndroidNetworking.get(prf.getString("tvip", null) + "index.php/simpanheader?workCenter="+wccode)
                Log.e("ipppp", c.getAddress() + "shopfloor2/index.php/simpanheader?workCenter=" + wccode);
                AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/simpanheader?workCenter=" + wccode)
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                List<Header> results = new ArrayList<>();
                                try {
                                    Log.e("tampil = ", response.toString(1));

                                    if (results != null)
                                        results.clear();

                                    String message = response.getString("message");

                                    if (message.equals("Header where Found")) {
                                        String records = response.getString("data");

                                        JSONArray dataArr = new JSONArray(records);

                                        if (dataArr.length() > 0) {

                                            for (int i = 0; i < dataArr.length(); i++) {
                                                Header header = gson.fromJson(dataArr.getJSONObject(i).toString(), Header.class);
                                                results.add(header);
                                            }
                                        }
                                    }
                                    progress.dismiss();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                adapter.addAll(results);
                            }

                            @Override
                            public void onError(ANError anError) {
                                progress.dismiss();

                            }
                        });
            }
        }

    }

    public void addTextListener() {
        tvtanggal1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void onTextChanged(CharSequence query, int start, int before, int count) {
                query = query.toString().toLowerCase();

                final List<Header> filteredList = new ArrayList<>();

                for (int i = 0; i <list.size(); i++) {
                    final String text = list.get(i).getDocDate().toLowerCase();
                    if (text.contains(query.toString())) {
                        filteredList.add(list.get(i));
                    }
                }

                rv.setLayoutManager(new LinearLayoutManager(Open_DocActivity.this));
                adapter = new OpenDocAdapter(filteredList, Open_DocActivity.this);
                rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

        });
    }

    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        pref = getSharedPreferences("Workcenter", MODE_PRIVATE);

        String wc = tvworkcenter0.getText().toString();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("tvworkcenter", wc);
        editor.commit();

        pref = getSharedPreferences("Namewc", MODE_PRIVATE);
        String tvnamewc = tvnamawc0.getText().toString();
        SharedPreferences.Editor editor1 = pref.edit();
        editor1.putString("tvnamewc", tvnamewc);
        editor1.commit();

        startActivity(intent);
        finish();
    }

    public void btnclearClicked1(View v) {
        clear();
    }

    private void clear() {
        str = "";
        num = 0;
        numtemp = 0;
        tvtanggal1.setText("");
    }

    }
