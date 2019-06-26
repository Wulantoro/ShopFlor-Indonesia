package com.example.shopfloor.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.RejectAdapter;
import com.example.shopfloor.Models.Reject;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.GlobalVars;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RespRejectFragActivity extends AppCompatActivity {
    public TextView tvRejectQty;
    public String str ="";
    float i,num,numtemp;
    private Gson gson;
    private List<Reject> list;
    private Button btnReject;
    public static Dialog dialog;
    private RecyclerView rv;
    private RejectAdapter adapter;
    public static TextView tvReject3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resp_reject_frag);

        gson = new Gson();
        tvReject3 = findViewById(R.id.tvReject3);

        btnReject = findViewById(R.id.btnReject);
        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(RespRejectFragActivity.this);
            }
        });

    }

    public void showDialog(Activity activity) {

        dialog = new Dialog(activity);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_reject_list);

        rv = findViewById(R.id.rvRejectFrag);
        rv = dialog.findViewById(R.id.rvRejectFrag);
        adapter = new RejectAdapter(this);
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

    public void loadData() {
        if (adapter != null)
            adapter.clearAll();

        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/reject")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Reject> result = new ArrayList<>();
                        try {
                            Log.e("Reject", response.toString(1));
                            if (result != null)
                                result.clear();

                            String message = response.getString("message");

                            if (message.equals("Reject were found")) {
                                String records = response.getString("data");

                                JSONArray dataarr = new JSONArray(records);
                                if (dataarr.length() > 0) {
                                    for (int i = 0; i < dataarr.length(); i ++) {
                                        Reject reject = gson.fromJson(dataarr.getJSONObject(i).toString(), Reject.class);
                                        result.add(reject);
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

    public void btn1Clicked(View v) {
        insert(1);
    }
    public void btn2Clicked(View v) {
        insert(2);
    }
    public void btn3Clicked(View v) {
        insert(3);
    }
    public void btn4Clicked(View v) {
        insert(4);
    }
    public void btn5Clicked(View v) {
        insert(5);
    }
    public void btn6Clicked(View v) {
        insert(6);
    }
    public void btn7Clicked(View v) {
        insert(7);
    }
    public void btn8Clicked(View v) {
        insert(8);
    }
    public void btn9Clicked(View v) {
        insert(9);
    }
    public void btn0Click(View v) {
        insert(0);
    }
    public void btnClearClic(View v) {
        clear1();
    }

    private void clear1() {
        str = "";
        num = 0;
        numtemp = 0;
        tvRejectQty.setText("");
    }



    private void insert(int i) {
        tvRejectQty = findViewById(R.id.tvRejectQty);
        str = str+Integer.toString(i);
        num = Integer.valueOf(str).intValue();
        tvRejectQty.setText(str);
    }
}
