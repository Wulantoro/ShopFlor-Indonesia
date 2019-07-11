package com.example.shopfloor.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shopfloor.Fragment.RejectFragment;
import com.example.shopfloor.Models.InputReject;
import com.example.shopfloor.R;

import java.util.ArrayList;
import java.util.List;

public class InputRejectAdapter extends RecyclerView.Adapter<InputRejectAdapter.ViewHolder> {

    private Context context;
    private List<InputReject> list_item;

    public InputRejectAdapter(Context context) {
        this.context = context;
        list_item = new ArrayList<>();
    }

    public InputRejectAdapter(RejectFragment rejectFragment) {
    }

    public InputRejectAdapter(List<InputReject> list, Context context) {
        this.context= context;
        list_item = list;
    }




    @NonNull
    @Override
    public InputRejectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reject_row, parent, false);
        final InputRejectAdapter.ViewHolder holder = new InputRejectAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder , int position) {
        final InputReject inputReject = list_item.get(holder.getAdapterPosition());

        holder.tvcodereject1.setText(inputReject.getRejectCode());
        holder.tvqty1.setText(inputReject.getRejectQty());
        holder.tvnamareject.setText(inputReject.getRejectName());
    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }

    public void add(InputReject r) {
        list_item.add(r);
        notifyItemInserted(list_item.size() - 1);
    }

    public void addAll(List<InputReject> moveResults) {
        for (InputReject results : moveResults) {
            add(results);
        }
    }

    private void remove(InputReject r) {
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

    private InputReject getItem(int position) {
        if (list_item != null) {
            return list_item.get(position);
        }
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvcodereject1;
        public TextView tvqty1;
        public TextView tvnamareject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvcodereject1 = itemView.findViewById(R.id.tvcodereject1);
            tvqty1 = itemView.findViewById(R.id.tvqty1);
            tvnamareject = itemView.findViewById(R.id.tvnamareject);
        }
    }
}
