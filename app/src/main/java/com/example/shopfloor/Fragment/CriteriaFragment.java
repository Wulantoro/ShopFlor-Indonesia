package com.example.shopfloor.Fragment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
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
import com.example.shopfloor.Adapter.InputCriteriaAdapter;
import com.example.shopfloor.Adapter.SuccDocAdapter;
import com.example.shopfloor.Models.Header;
import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.Models.Upcriteria;
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


public class CriteriaFragment extends Fragment {

    private TextView tvdocentry6;
    private InputCriteriaAdapter adapter;
    private List<Upcriteria> list;
    private RecyclerView rv;
    private SuccDocAdapter adapter1;
    private Upcriteria upcriteria;
    private Gson gson;
    private Header header;
    private TextView tvip12;
    private static final String TAG = CriteriaFragment.class.getName();

    Realm realm;
    RealmHelper realmHelper;
    List<ServerModel> serverModels;

//    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_criteria, container, false);

        tvdocentry6 = rootView.findViewById(R.id.tvdocentry6);
        adapter1 = new SuccDocAdapter(this);
        header = getActivity().getIntent().getParcelableExtra("key_succ");
        tvdocentry6.setText(String.valueOf(header.getDocEntry()));

        tvip12 = rootView.findViewById(R.id.tvip12);
        //        Setup Realm
        Realm.init(getContext());
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
        tvip12.setText(text);

        gson = new Gson();
        list = new ArrayList<>();
        rv = rootView.findViewById(R.id.rvActualCrit1);
        adapter = new InputCriteriaAdapter(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        loadData(tvdocentry6.getText().toString());
        rv.setAdapter(adapter);

        return rootView;
    }

    public void loadData(String hostHeadEntry) {
        if (adapter != null)
            adapter.clearAll();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ServerModel> results = realm.where(ServerModel.class).findAll();
        String text = "";
        for (ServerModel c : results) {
            text = text + c.getAddress();


            Log.e("criteria haha = ", tvdocentry6.getText().toString());

//        AndroidNetworking.get(GlobalVars.BASE_IP + "index.php/upcriteria?docEntry="+docentry)
            Log.e(TAG, "ip = " + c.getAddress() + "shopfloor2/index.php/CriteriaSucc?hostHeadEntry=" + hostHeadEntry);
            AndroidNetworking.get(c.getAddress() + "shopfloor2/index.php/CriteriaSucc?hostHeadEntry=" + hostHeadEntry)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            List<Upcriteria> result = new ArrayList<>();
                            try {
                                Log.e("criteria = ", response.toString(1));
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
