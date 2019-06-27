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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.SuccDocAdapter;
import com.example.shopfloor.Models.Header;
import com.example.shopfloor.Models.User;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.GlobalVars;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SuccessDocActivity extends AppCompatActivity {

    private DatePicker datePickerSuc;
    private Calendar calendarSuc;
    private int year, month, day;
    private TextView dateViewSuc;
    private Button btnCoba;
    private TextView tvworkcenter4;

    private RecyclerView rv;
    private SuccDocAdapter adapter;
    private Gson gson;
    private List<Header> list;
    private Header header;
    private Context context;
    public SharedPreferences pref, prf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_doc);

        tvworkcenter4 = findViewById(R.id.tvworkcenter4);

        TextView tvworlcenter = findViewById(R.id.tvworkcenter4);
        prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
        tvworlcenter.setText(prf.getString("workcenter", null));
        Log.e("work vwjbgyg ", prf.getString("workcenter", null));

        /*************tampil data*********************/
        gson = new Gson();
        list = new ArrayList<>();
        rv = findViewById(R.id.rvSuccDocList);
        adapter = new SuccDocAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
        loadData();


        /*******************tanggal *****************/
        dateViewSuc = (TextView) findViewById(R.id.tvtanggal1success);
        calendarSuc = Calendar.getInstance();
        year = calendarSuc.get(Calendar.YEAR);

        month = calendarSuc.get(Calendar.MONTH);
        day = calendarSuc.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
    }

    private void loadData() {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Sedang proses");
        progress.setTitle("Sedang prosess");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        if (adapter != null) {
            adapter.clearAll();

            prf = getSharedPreferences("Workcenter", MODE_PRIVATE);
            prf.getString("workcenter", null);
            Log.e("work vwjbgyg ", prf.getString("workcenter", null));


            AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/completeheader?workCenter="+prf.getString("workcenter", null))
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

    public void setDate(View view) {
        showDialog(999);
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
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            showDate(year, month + 1, dayOfMonth);
        }
    };

    private void showDate(int year, int month, int day) {
        dateViewSuc.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }

}

