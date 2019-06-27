package com.example.shopfloor.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvdate2 = itemView.findViewById(R.id.tvdate2);

        }
    }
}

