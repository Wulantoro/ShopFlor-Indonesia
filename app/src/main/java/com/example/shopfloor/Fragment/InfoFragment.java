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
    private TextView tvNo_doc_Frag1;
    private TextView tv_noProd_frag1;
    private TextView tv_tglDoc_frag1;
    private TextView tv_prodFrag1;
    private TextView tv_nmProd_frag1;
    private TextView tv_qty_rencana1;
    private TextView tv_sts_prod1;
    private TextView tv_route1;
    private TextView tvroutename3;
    private TextView tv_inQty1;
    private TextView tv_outQty1;
    private TextView tv_seq1;
    private TextView tv_seq_qty1;
    private TextView tv_tglMulai1;
    private TextView tv_tglSelesai1;
    private TextView tv_jamMulai1;
    private TextView tv_jamSelesai1;
    private TextView tv_shift1;
    private TextView tvnmshift;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);

        tvNo_doc_Frag1 = rootView.findViewById(R.id.tvNo_doc_Frag1);
        tv_noProd_frag1 = rootView.findViewById(R.id.tv_noProd_frag1);
        tv_tglDoc_frag1 = rootView.findViewById(R.id.tv_tglDoc_frag1);
        tv_prodFrag1 = rootView.findViewById(R.id.tv_prodFrag1);
        tv_nmProd_frag1 = rootView.findViewById(R.id.tv_nmProd_frag1);
        tv_qty_rencana1 = rootView.findViewById(R.id.tv_qty_rencana1);
        tv_sts_prod1 = rootView.findViewById(R.id.tv_sts_prod1);
        tv_route1 = rootView.findViewById(R.id.tv_route1);
        tvroutename3 = rootView.findViewById(R.id.tvroutename3);
        tv_inQty1 = rootView.findViewById(R.id.tv_inQty1);
        tv_outQty1 = rootView.findViewById(R.id.tv_outQty1);
        tv_seq1 = rootView.findViewById(R.id.tv_seq1);
        tv_seq_qty1 = rootView.findViewById(R.id.tv_seq_qty1);
        tv_tglMulai1 = rootView.findViewById(R.id.tv_tglMulai1);
        tv_tglSelesai1 = rootView.findViewById(R.id.tv_tglSelesai1);
        tv_jamMulai1 = rootView.findViewById(R.id.tv_jamMulai1);
        tv_jamSelesai1 = rootView.findViewById(R.id.tv_jamSelesai1);
        tv_shift1 = rootView.findViewById(R.id.tv_shift1);
        tvnmshift = rootView.findViewById(R.id.tvnmshift);

        adapter = new SuccDocAdapter(this);

        header = getActivity().getIntent().getParcelableExtra("key_succ");
        tvNo_doc_Frag1.setText(String.valueOf(header.getDocNum()));
        tv_noProd_frag1.setText(header.getProdNo());
        tv_tglDoc_frag1.setText(header.getDocDate().substring(0,11));
        tv_prodFrag1.setText(header.getProdCode());
        tv_nmProd_frag1.setText(header.getProdName());
        tv_qty_rencana1.setText(header.getProdPlanQty().replace(".000000",""));
        tv_sts_prod1.setText(header.getProdStatus());
        tv_route1.setText(header.getRouteCode());
        tvroutename3.setText(header.getRouteName());
        tv_inQty1.setText(header.getInQty().replace(".000000",""));
        tv_outQty1.setText(header.getOutQty().replace(".000000",""));
        tv_seq1.setText(header.getSequence());
        tv_seq_qty1.setText(header.getSequenceQty().replace(".000000",""));
        tv_tglMulai1.setText(header.getTanggalMulai().substring(0,11));
        tv_tglSelesai1.setText(header.getTanggalSelesai().substring(0,11));
        tv_jamMulai1.setText(header.getJamMulai());
        tv_jamSelesai1.setText(header.getJamSelesai());
        tv_shift1.setText(header.getShift());
        tvnmshift.setText(header.getShiftName());
        return rootView;
    }
}
