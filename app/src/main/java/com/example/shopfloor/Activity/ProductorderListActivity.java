package com.example.shopfloor.Activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.ProductorderAdapter;
import com.example.shopfloor.Models.Productorder;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.GlobalVars;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductorderListActivity extends AppCompatActivity {
    private RecyclerView rv;
    private ProductorderAdapter adapter;
    private Gson gson;
    private List<Productorder> list;
    private Productorder productorder;
    private EditText searchPO;
    private TextView tvWC3;
    public static final String KEY_WC = "workcenter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productorder_list);

        gson = new Gson();
        list = new ArrayList<Productorder>();
        rv = findViewById(R.id.rvProductorderList);
        searchPO = findViewById(R.id.searchPO);
        rv = findViewById(R.id.rvProductorderList);
        rv.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        loadData();

        adapter = new ProductorderAdapter(list, this);
        rv.setAdapter(adapter);

//        addTextListener();
    }

    private void loadData() {

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Sedang Proses");
        progress.setTitle("Sedang Proses");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        if (adapter != null)
            adapter.clearAll();

        /*tvWC3 = findViewById(R.id.tvWC3);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ProductorderListActivity.this);
        String value = getIntent().getExtras().getString(Add_DocActivity.KEY_WC);
        tvWC3.setText(value);

        Log.e("pref ",  preferences.toString());*/

        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/productorder")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Productorder> results = new ArrayList<>();
                        try {
                            Log.e("hasil", response.toString(1));

                            String message = response.getString("message");

                            if (message.equals("Production Order were found")) {
                                String records = response.getString("data");

                                JSONArray dataArr = new JSONArray(records);

                                if (dataArr.length() > 0) {

                                    for (int i = 0; i < dataArr.length(); i++) {
                                        Productorder productorder = gson.fromJson(dataArr.getJSONObject(i).toString(), Productorder.class);
                                        results.add(productorder);

                                    }
                                }
                            }

                            progress.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();

                            progress.dismiss();
                        }

                        adapter.addAll(results);
                    }
                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();

                    }
                });
    }

    //searc product order
    /*public void addTextListener() {
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

                rv.setLayoutManager(new LinearLayoutManager(ProductorderListActivity.this));
                adapter = new ProductorderAdapter(filteredList, ProductorderListActivity.this);
                rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }*/
}
