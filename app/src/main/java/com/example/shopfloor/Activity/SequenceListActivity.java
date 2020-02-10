package com.example.shopfloor.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.SequenceAdapter;
import com.example.shopfloor.Models.Sequence;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.GlobalVars;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SequenceListActivity extends AppCompatActivity {

    private RecyclerView rv;
    private SequenceAdapter adapter;
    private Gson gson;
    private List<Sequence> list;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequence_list);

        gson = new Gson();
        list = new ArrayList<>();
        rv = findViewById(R.id.rvSequenceList);
        adapter = new SequenceAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("sedang proses");
        progress.setTitle("sedang proses");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();

        if (adapter != null)
            adapter.clearAll();

        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/sequence")
                .setPriority(Priority.MEDIUM)
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
                            progress.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();

                            progress.dismiss();
                        }
                        adapter.addAll(result);
                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();

                    }
                });
    }
}
