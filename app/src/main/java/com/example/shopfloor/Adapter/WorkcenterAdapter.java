package com.example.shopfloor.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopfloor.Activity.HomeActivity;
import com.example.shopfloor.Activity.PilihWCActivity;
import com.example.shopfloor.Activity.WorkcenterListActivity;
import com.example.shopfloor.Models.Workcenter;
import com.example.shopfloor.R;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class WorkcenterAdapter extends RecyclerView.Adapter<WorkcenterAdapter.ViewHolder> {

    private Context context;
    private List<Workcenter> list_item;

    public WorkcenterAdapter(List<Workcenter> list, Context context) {
        this.context = context;
        list_item = list;

    }

    public WorkcenterAdapter(Context context) {
        this.context = context;
        list_item = new ArrayList<>();
    }

    @Override
    public WorkcenterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work_center, parent, false);
        final WorkcenterAdapter.ViewHolder holder = new WorkcenterAdapter.ViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Workcenter workcenter = list_item.get(holder.getAdapterPosition());

        holder.tvCode_wc.setText(workcenter.getCode());
        holder.tvName_wc.setText(workcenter.getName());

        /************result workcenter terpilih**************/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, workcenter.getCode(), Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.putExtra("keywc",workcenter.getCode());//Put Message to pass over intent
                    context.startActivity(intent);
                    ((WorkcenterListActivity)context).finish(); //Set result OK
                 }
        });

        }
        /***********************************************************************/

    @Override
    public int getItemCount() {
        return list_item.size();
    }

    public void add(Workcenter r) {
        list_item.add(r);
        notifyItemInserted(list_item.size() - 1);
    }

    public void addAll(List<Workcenter> moveResults) {
        for (Workcenter result : moveResults) {
            add(result);
        }
    }

    private void remove(Workcenter r) {
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
    public boolean isEmpty () {
        return getItemCount() == 0;
    }

    private Workcenter getItem (int position){
        if (list_item != null) {
            return list_item.get(position);
        }
        return null;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCode_wc;
        public TextView tvName_wc;

        public ViewHolder(View itemView) {
            super(itemView);

            tvCode_wc = itemView.findViewById(R.id.tvCode_wc);
            tvName_wc = itemView.findViewById(R.id.tvName_wc);
        }
    }
}



