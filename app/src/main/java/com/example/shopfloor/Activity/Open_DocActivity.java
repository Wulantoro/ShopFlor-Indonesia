package com.example.shopfloor.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.OpenDocAdapter;
import com.example.shopfloor.Models.Header;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.GlobalVars;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Open_DocActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    private TextView dateView;
    private TextView tvworkcenter0;
    private TextView tvqcname0;

    private RecyclerView rv;
    private OpenDocAdapter adapter;
    private Gson gson;
    private List<Header> list;
    private Header header;
    private Context context;
    private SharedPreferences prf, pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open__doc);

        tvworkcenter0 = findViewById(R.id.tvworkcenter0);
        tvqcname0 = findViewById(R.id.tvqcname0);

        TextView tvqcname = findViewById(R.id.tvqcname0);
        prf = getSharedPreferences("Qcname", MODE_PRIVATE);
        tvqcname.setText(prf.getString("tvqcname", null));

        TextView tvworkcenter = findViewById(R.id.tvworkcenter0);
        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        tvworkcenter.setText(prf.getString("workcenter", null));

        dateView = (TextView) findViewById(R.id.tvtanggal1);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

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
        loadData();

    }

    private void loadData() {

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Sedang proses");
        progress.setTitle("Sedang Proses");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        if (adapter != null) {
            adapter.clearAll();

            AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/simpanheader?workCenter="+prf.getString("workcenter", null))
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

    public void  setDate(View view) {
        showDialog(999);
        //akan menampilkan teks ketika kalendar muncul setelah menekan tombol
        Toast.makeText(getApplicationContext(), "Pilih tanggal", Toast.LENGTH_SHORT).show();
    }

    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }


    }

