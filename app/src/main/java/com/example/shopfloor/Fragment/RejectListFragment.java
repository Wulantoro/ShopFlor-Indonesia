package com.example.shopfloor.Fragment;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.shopfloor.R;


public class RejectListFragment extends Fragment {

    private TextView tvRejectQty;
    public String str ="";
    float i,num,numtemp;
    private TextView tvReject2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reject_list, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }


}
