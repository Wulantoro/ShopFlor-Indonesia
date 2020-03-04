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
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class SuccessDocActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tvworkcenter4;
    private Button btnPlhtglsuccess;
    private Button btnSmatglsuccess;
    private TextView tvtanggal1success;
    private TextView tvnamawc0;

    private RecyclerView rv;
    private SuccDocAdapter adapter;
    private Gson gson;
    private List<Header> list;
    private Header header;
    private Context context;
    public SharedPreferences pref, prf;
    private TextView tvip11;

    public String str ="";
    Character op = 'q';
    float i,num,numtemp;

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;
    private static final String TAG = Add_DocActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_doc);

        tvworkcenter4 = findViewById(R.id.tvworkcenter4);
        btnPlhtglsuccess = findViewById(R.id.btnPlhtglsuccess);
        tvtanggal1success = findViewById(R.id.tvtanggal1success);
        btnSmatglsuccess = findViewById(R.id.btnSmatglsuccess);
        tvnamawc0 = findViewById(R.id.tvnamawc0);
        tvip11 = findViewById(R.id.tvip11);

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
        tvip11.setText(text);

        TextView tvworlcenter = findViewById(R.id.tvworkcenter4);
        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        tvworlcenter.setText(prf.getString("workcenter1", null));
        Log.e("work vwjbgyg ", prf.getString("workcenter1", null));

        /*************tampil data*********************/
        gson = new Gson();
        list = new ArrayList<>();
        rv = findViewById(R.id.rvSuccDocList);
        adapter = new SuccDocAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
        loadData();

        adapter = new SuccDocAdapter(list, this);
        rv.setAdapter(adapter);
        addTextListener();


        /*******************tanggal *****************/
//        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        /*************************************/
//        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//        TextView docdate = findViewById(R.id.tvtanggal1success);
//        docdate.setText(date);
        /***********************************************/


        btnPlhtglsuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

//        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
//        TextView docdate = findViewById(R.id.tvtanggal1success);
//        docdate.setText(date);
//
//        calendarSuc = Calendar.getInstance();
//        year = calendarSuc.get(Calendar.YEAR);

//        month = calendarSuc.get(Calendar.MONTH);
//        day = calendarSuc.get(Calendar.DAY_OF_MONTH);
//        showDate(year, month + 1, day);
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
                tvtanggal1success.setText(dateFormatter.format(newDate.getTime()));

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void loadData() {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Sedang proses");
        progress.setTitle("Sedang prosess");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        if (adapter != null) {
            adapter.clearAll();

            Realm realm = Realm.getDefaultInstance();
            RealmResults<ServerModel> results1 = realm.where(ServerModel.class).findAll();
            String text = "";
            for (ServerModel c : results1) {
                text = text + c.getAddress();


                prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
                prf.getString("workcenter1", null);
                Log.e("work centot ", prf.getString("workcenter1", null));


//            AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/completeheader?workCenter="+prf.getString("workcenter", null))
                Log.e(TAG, "ip = " + c.getAddress() + "shopfloor2/index.php/completeheader?workCenter=" + prf.getString("workcenter1", null));
                AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/completeheader?workCenter=" + prf.getString("workcenter1", null))
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

                                    if (message.equals("Header complete where found")) {
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
        tvtanggal1success.addTextChangedListener(new TextWatcher() {
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

                for (int i = 0; i < list.size(); i++) {
                    final String text = list.get(i).getDocDate().toLowerCase();
                    if (text.contains(query.toString())) {
                        filteredList.add(list.get(i));
                    }
                }

                rv.setLayoutManager(new LinearLayoutManager(SuccessDocActivity.this));
                adapter = new SuccDocAdapter(filteredList, SuccessDocActivity.this);
                rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });
    }

    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        pref = getSharedPreferences("Workcenter", MODE_PRIVATE);

        String wc = tvworkcenter4.getText().toString();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("workcenter1", wc);
        editor.commit();

//        pref = getSharedPreferences("Namewc", MODE_PRIVATE);
//        String tvnamewc = tvnamawc0.getText().toString();
//        SharedPreferences.Editor editor1 = pref.edit();
//        editor1.putString("tvnamewc", tvnamewc);
//        editor1.commit();

        startActivity(intent);
        finish();
    }

    public void btnclearClicked(View v) {
        clear();
    }

    private void clear() {
        str = "";
        num = 0;
        numtemp = 0;
        tvtanggal1success.setText("");
    }
}

