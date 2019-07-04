package com.example.shopfloor.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shopfloor.Models.Criteria;
import com.example.shopfloor.R;

import java.util.ArrayList;
import java.util.List;

public class CriteriaAdapter extends RecyclerView.Adapter<CriteriaAdapter.ViewHolder> {

    private Context context;
    private List<Criteria> list_item;

    public CriteriaAdapter(List<Criteria> list, Context context) {
        this.context = context;
        list_item = list;
    }

    public CriteriaAdapter(Context context) {
        this.context = context;
        list_item = new ArrayList<>();
    }

    @NonNull
    @Override
    public CriteriaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.actual_crit_list, parent, false);
        final CriteriaAdapter.ViewHolder holder = new CriteriaAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Criteria criteria = list_item.get(holder.getAdapterPosition());

        holder.tvcriteria1.setText(criteria.getUCriteria());
        holder.tvcritdesc1.setText(String.valueOf(criteria.getUCriteriaName()));
        holder.tvstandard1.setText(criteria.getUStandard());
    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }

    public void add(Criteria r) {
        list_item.add(r);
        notifyItemInserted(list_item.size() - 1);
    }

    public void addAll(List<Criteria> moveResults) {
        for (Criteria result : moveResults) {
            add(result);
        }
    }

    private void remove(Criteria r) {
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

    private Criteria getItem (int position) {
        if (list_item != null) {
            return list_item.get(position);
        }
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvcriteria1;
        public TextView tvcritdesc1;
        public TextView tvvaluetype1;
        public TextView tvstandard1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvcriteria1 = itemView.findViewById(R.id.tvcriteria1);
            tvcritdesc1 = itemView.findViewById(R.id.tvcritdesc1);
            tvstandard1 = itemView.findViewById(R.id.tvstandard1);

        }
    }
}
