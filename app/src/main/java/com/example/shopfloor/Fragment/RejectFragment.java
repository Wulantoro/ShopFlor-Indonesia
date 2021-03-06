package com.example.shopfloor.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Adapter.InputRejectAdapter;
import com.example.shopfloor.Adapter.SuccDocAdapter;
import com.example.shopfloor.Models.Header;
import com.example.shopfloor.Models.InputReject;
import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.RealmHelper;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class RejectFragment extends Fragment {

    private Gson gson;
    private InputRejectAdapter adapter;
    private List<InputReject> list;
    private RecyclerView rv;
    private TextView tvdocentry5;
    private SuccDocAdapter adapter1;
    private Header header;
    private TextView tvip13;

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reject, container, false);



        tvdocentry5 = rootView.findViewById(R.id.tvdocentry5);
        tvip13 = rootView.findViewById(R.id.tvip13);

        Realm.init(getContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        serverModels = new ArrayList<>();

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c:results) {
            text = text + c.getAddress();
        }
        tvip13.setText(text);

        adapter1 = new SuccDocAdapter(this);

         header = getActivity().getIntent().getParcelableExtra("key_succ");
        tvdocentry5.setText(String.valueOf(header.getDocEntry()));

        gson = new Gson();
        list = new ArrayList<>();
        rv = rootView.findViewById(R.id.rvInputReject);
        adapter = new InputRejectAdapter(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        loadData(tvdocentry5.getText().toString());
        rv.setAdapter(adapter);

        return rootView;
    }

    public void loadData(String hostHeadEntry) {

        if (adapter != null)
            adapter.clearAll();

        Log.e("docentry3000 == ", "check docentry = " + tvdocentry5.getText().toString());

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results) {
            text = text + c.getAddress();


//        AndroidNetworking.get(GlobalVars.BASE_IP+ "index.php/inputreject?hostHeadEntry="+hostHeadEntry)
            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/succreject?hostHeadEntry=" + hostHeadEntry)
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

}
