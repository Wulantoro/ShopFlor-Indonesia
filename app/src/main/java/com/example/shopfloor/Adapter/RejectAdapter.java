package com.example.shopfloor.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shopfloor.Activity.RespRejectFragActivity;
//import com.example.shopfloor.Fragment.RejectListFragment;
import com.example.shopfloor.Models.Reject;
import com.example.shopfloor.R;

import java.util.ArrayList;
import java.util.List;

public class RejectAdapter extends RecyclerView.Adapter<RejectAdapter.ViewHolder> {

    private Context context;
    private List<Reject> list_item;

    public RejectAdapter(List<Reject> list, Context context) {
        this.context = context;
        list_item = list;
    }

    public RejectAdapter(Context context) {
        this.context = context;
        list_item = new ArrayList<>();
    }
    @NonNull
    @Override
    public RejectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reject_row_item, parent, false);
        final RejectAdapter.ViewHolder holder = new RejectAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Reject reject = list_item.get(holder.getAdapterPosition());

        holder.tvCodeR.setText(reject.getCode());
        holder.tvNameR.setText(reject.getName());

    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }

    public void add(Reject r) {
        list_item.add(r);
        notifyItemInserted(list_item.size() - 1);
    }

    public void addAll(List<Reject> moveResults) {
        for (Reject result : moveResults) {
            add(result);
        }
    }

    private void remove(Reject r) {
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

    private Reject getItem(int position) {
        if (list_item != null) {
            return list_item.get(position);
        }
        return null;
    }

    public List<Reject> getList_item() {
        return list_item;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCodeR;
        public TextView tvNameR;
        public TextView tvcodereject0;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCodeR = itemView.findViewById(R.id.tvCodeR);
            tvNameR = itemView.findViewById(R.id.tvNameR);
            tvcodereject0 = itemView.findViewById(R.id.tvcodereject0);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RespRejectFragActivity.tvReject3.setText(list_item.get(getAdapterPosition()).getName());
                    RespRejectFragActivity.tvcodereject0.setText(list_item.get(getAdapterPosition()).getCode());
                    RespRejectFragActivity.dialog.dismiss();
                }
            });
        }
    }
}
