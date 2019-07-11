package com.example.shopfloor.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.InputRejectAdapter;
import com.example.shopfloor.Models.InputReject;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.GlobalVars;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RejectFragment extends Fragment {

    private Gson gson;
    private InputRejectAdapter adapter;
    private List<InputReject> list;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reject, container, false);

        gson = new Gson();
        rv = rootView.findViewById(R.id.rvInputReject);
        adapter = new InputRejectAdapter(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        loadData();
        rv.setAdapter(adapter);

        return rootView;
    }

    public void loadData() {
//        final ProgressDialog progress = new ProgressDialog(this);
        if (adapter != null)
            adapter.clearAll();

        AndroidNetworking.get(GlobalVars.BASE_IP+ "index.php/inputreject")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<InputReject> result = new ArrayList<>();
                        try {
                            Log.e("item rejectttt = ", response.toString(1));
                            if (result != null)
                                result.clear();

                            String message = response.getString("message");
                            if (message.equals("Item reject were found")) {
                                String records = response.getString("data");

                                JSONArray dataArr = new JSONArray(records);
                                if (dataArr.length() > 0) {
                                    for (int i = 0; i < dataArr.length(); i++) {
                                        InputReject inputReject = gson.fromJson(dataArr.getJSONObject(i).toString(), InputReject.class);
                                        result.add(inputReject);
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

}
