package com.example.shopfloor.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopfloor.Models.SincHeader;

import java.util.ArrayList;
import java.util.List;

public class SincHeaderAdapter extends RecyclerView.Adapter<SincHeaderAdapter.ViewHolder> {

    private Context context;
    private List<SincHeader> list_item;

    public SincHeaderAdapter(List<SincHeader> list, Context context) {
        this.context = context;
        list_item = list;
    }

    public SincHeaderAdapter(Context context) {
        this.context = context;
        list_item = new ArrayList<>();
    }


    @NonNull
    @Override
    public SincHeaderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        final  SincHeaderAdapter.ViewHolder holder = new SincHeaderAdapter()
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SincHeaderAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
