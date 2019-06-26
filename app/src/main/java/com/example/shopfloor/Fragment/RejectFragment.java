package com.example.shopfloor.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopfloor.R;


public class RejectFragment extends Fragment {

    public static  RejectFragment newInstance() {
        // Required empty public constructor
        return new RejectFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reject, container, false);

        return rootView;
    }

}
