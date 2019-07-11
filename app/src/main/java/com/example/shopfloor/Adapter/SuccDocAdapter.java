package com.example.shopfloor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shopfloor.Activity.DetailSuccDocActivity;
import com.example.shopfloor.Activity.SuccessDocActivity;
import com.example.shopfloor.Fragment.InfoFragment;
import com.example.shopfloor.Fragment.RejectFragment;
import com.example.shopfloor.Models.Header;
import com.example.shopfloor.R;

import java.util.ArrayList;
import java.util.List;

public class SuccDocAdapter extends RecyclerView.Adapter<SuccDocAdapter.ViewHolder> {
    private Context context;
    private List<Header> list_item;

    public SuccDocAdapter(Context context) {
        this.context = context;
        list_item = new ArrayList<>();
    }

    public SuccDocAdapter(InfoFragment infoFragment) {
    }

    @NonNull
    @Override
    public SuccDocAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_succdoc, parent, false);
        final SuccDocAdapter.ViewHolder holder = new SuccDocAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Header header = list_item.get(holder.getAdapterPosition());

        holder.tvdate2.setText(header.getDocDate().substring(0, 10));
        holder.tvdocnum0.setText(header.getDocNum());
        holder.tvposted2.setText(String.valueOf(header.getPosted()));
        holder.tvworkcenter5.setText(header.getWorkCenter());
        holder.tvprodno2.setText(header.getProdNo());
        holder.tvsequence4.setText(header.getSequence());
        holder.tvinputqty4.setText(header.getInQty());
        holder.tvoutputqty2.setText(header.getOutQty());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailSuccDocActivity.class);
                intent.putExtra("key_succ", header);
                context.startActivity(intent);
                ((SuccessDocActivity)context).finish();
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvdate2;
        public TextView tvdocnum0;
        public TextView tvposted2;
        public TextView tvworkcenter5;
        public TextView tvprodno2;
        public TextView tvsequence4;
        public TextView tvinputqty4;
        public TextView tvoutputqty2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvdate2 = itemView.findViewById(R.id.tvdate2);
            tvdocnum0 = itemView.findViewById(R.id.tvdocnum0);
            tvposted2 = itemView.findViewById(R.id.tvposted2);
            tvworkcenter5 = itemView.findViewById(R.id.tvworkcenter5);
            tvprodno2 = itemView.findViewById(R.id.tvprodno2);
            tvsequence4 = itemView.findViewById(R.id.tvsequence4);
            tvinputqty4 = itemView.findViewById(R.id.tvinputqty4);
            tvoutputqty2 = itemView.findViewById(R.id.tvoutputqty2);;

        }
    }
}

