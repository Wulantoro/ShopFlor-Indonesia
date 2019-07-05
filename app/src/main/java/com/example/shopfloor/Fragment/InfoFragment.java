package com.example.shopfloor.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shopfloor.Adapter.SuccDocAdapter;
import com.example.shopfloor.Models.Header;
import com.example.shopfloor.R;


public class InfoFragment extends Fragment {

    private Header header;
    private SuccDocAdapter adapter;
    private TextView tvNo_doc_Frag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);

        tvNo_doc_Frag = (TextView) rootView.findViewById(R.id.tvNo_doc_Frag);
        adapter = new SuccDocAdapter(this);
//        header = getIntent().getParcelableExtra("key_succ");
        tvNo_doc_Frag.setText(header.getDocNum());
        return rootView;



//


    }


}
