package com.example.shopfloor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shopfloor.Activity.DetailOpenDocActivity;
import com.example.shopfloor.Models.Header;
import com.example.shopfloor.R;

import java.util.ArrayList;
import java.util.List;

public class OpenDocAdapter extends RecyclerView.Adapter<OpenDocAdapter.ViewHolder> {
    private Context context;
    private List<Header> list_item;

    public OpenDocAdapter(Context context) {
        this.context = context;
        list_item = new ArrayList<>();
    }
    @NonNull
    @Override
    public OpenDocAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_opendoc, parent, false);
        final OpenDocAdapter.ViewHolder holder = new OpenDocAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Header header = list_item.get(holder.getAdapterPosition());

        holder.tvdate1.setText(header.getDocDate().substring(0,10));
        holder.tvdocnum.setText(header.getDocNum());
//        holder.tvposted1.setText(header.getPosted());
        holder.tvprodno1.setText(header.getProdNo());
        holder.tvsequence2.setText(header.getSequence());
        holder.tvinputqty2.setText(String.valueOf(header.getInQty()));
        holder.tvoutputqty2.setText(header.getOutQty());
        holder.tvworkcenter2.setText(header.getWorkCenter());
        holder.tvdocentry.setText(String.valueOf(header.getDocEntry()));
        holder.tvposted1.setText(header.getStatus());


        // itemview jika di klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_tampil = new Intent(context, DetailOpenDocActivity.class);
                i_tampil.putExtra("key_opendoc", header);
                context.startActivity(i_tampil);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }

    public void add(Header r) {
        list_item.add(r);
        notifyItemInserted(list_item.size() - 1);
    }

    public void addAll(List<Header> moveResults) {
        for (Header results : moveResults) {
            add(results);
        }
    }

    private void remove(Header r) {
        int position = list_item.indexOf(r);
        if (position > -1) {
            list_item.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public void clearAll() {
        if (!list_item.isEmpty()) {
            list_item.clear();
            notifyDataSetChanged();
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    private Header getItem(int position) {
        if (list_item != null) {
            return list_item.get(position);
        }
        return null;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvdate1;
        public TextView tvdocnum;
        public TextView tvposted1;
        public TextView tvprodno1;
        public TextView tvsequence2;
        public TextView tvinputqty2;
        public TextView tvoutputqty2;
        public TextView tvworkcenter2;
        public TextView tvdocentry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvdate1 = itemView.findViewById(R.id.tvdate1);
            tvdocnum = itemView.findViewById(R.id.tvdocnum);
            tvposted1 = itemView.findViewById(R.id.tvposted1);
            tvprodno1 = itemView.findViewById(R.id.tvprodno1);
            tvsequence2 = itemView.findViewById(R.id.tvsequence2);
            tvinputqty2 = itemView.findViewById(R.id.tvinputqty2);
            tvoutputqty2 = itemView.findViewById(R.id.tvoutputqty2);
            tvworkcenter2 = itemView.findViewById(R.id.tvworkcenter2);
            tvdocentry = itemView.findViewById(R.id.tvdocentry);
        }
    }
}
