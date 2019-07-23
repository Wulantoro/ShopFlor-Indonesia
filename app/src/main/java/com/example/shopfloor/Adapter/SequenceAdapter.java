package com.example.shopfloor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shopfloor.Activity.Add_DocActivity;
import com.example.shopfloor.Activity.SequenceListActivity;
import com.example.shopfloor.Models.Sequence;
import com.example.shopfloor.R;

import java.util.ArrayList;
import java.util.List;

public class SequenceAdapter extends RecyclerView.Adapter<SequenceAdapter.ViewHolder> {

    private Context context;
    private List<Sequence> list_item;

    public SequenceAdapter(List<Sequence> list, Context context) {
        this.context = context;
        list_item = list;
    }

    public SequenceAdapter( Context context) {
        this.context = context;
        list_item = new ArrayList<>();
    }
    @NonNull
    @Override
    public SequenceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sequence_row_item, parent, false);
        final SequenceAdapter.ViewHolder holder = new SequenceAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Sequence sequence = list_item.get(holder.getAdapterPosition());

        holder.tvseq.setText(sequence.getUQuantity());
        holder.tvseqqty.setText(String.valueOf("("+sequence.getUSequence()+")"));

    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }

    public void add(Sequence r) {
        list_item.add(r);
        notifyItemInserted(list_item.size() - 1);
    }

    public void addAll(List<Sequence> moveResult) {
        for (Sequence result : moveResult) {
            add(result);
        }
    }

    private void remove(Sequence r) {
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

    private Sequence getItem(int position) {
        if (list_item != null) {
            return list_item.get(position);
        }
        return null;
    }

    public List<Sequence> getList_item() {
        return list_item;
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvseq;
        public TextView tvseqqty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvseq = itemView.findViewById(R.id.tvseq);
            tvseqqty = itemView.findViewById(R.id.tvseqqty);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Add_DocActivity.tvSquence1.setText(String.valueOf(list_item.get(getAdapterPosition()).getUSequence()));
                    Add_DocActivity.tvSquence_Qty1.setText(list_item.get(getAdapterPosition()).getUQuantity());
                    Add_DocActivity.dialog.dismiss();
                }
            });
        }
    }
}
