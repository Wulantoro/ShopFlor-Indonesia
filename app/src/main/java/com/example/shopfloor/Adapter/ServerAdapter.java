package com.example.shopfloor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shopfloor.Activity.KonfigurasiActivity;
import com.example.shopfloor.Fragment.CriteriaFragment;
import com.example.shopfloor.Models.InputReject;
import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.R;

import java.util.ArrayList;
import java.util.List;

public class ServerAdapter extends RecyclerView.Adapter<ServerAdapter.ViewHolder> {
    private List<ServerModel> serverModels;
    Context context;

    public ServerAdapter(Context context) {
        this.context = context;
        serverModels = new ArrayList<>();
    }

    public ServerAdapter(CriteriaFragment criteriaFragment) {
        serverModels = new ArrayList<>();
    }

    public ServerAdapter(Context context, List<ServerModel> serverModels) {
        this.context = context;
        this.serverModels = serverModels;
    }
    @NonNull
    @Override
    public ServerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_server, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ServerAdapter.ViewHolder holder, int position) {
        final ServerModel model = serverModels.get(position);
        holder.tvip7.setText(model.getAddress());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), KonfigurasiActivity.class);
//                intent.putExtra("id", model.getId().toString());
//                intent.putExtra("address", model.getAddress());
//                view.getContext().startActivity(intent);
//            }
//        });

    }

//    @Override
//    public int getItemCount() {
//        return serverModels.size();
//    }

    @Override
    public int getItemCount() {
        return serverModels.size();
    }

    public void add(ServerModel r) {
        serverModels.add(r);
        notifyItemInserted(serverModels.size() - 1);
    }

    public void addAll(List<ServerModel> moveResults) {
        for (ServerModel results : moveResults) {
            add(results);
        }
    }

    private void remove(ServerModel r) {
        int position = serverModels.indexOf(r);
        if (position > -1) {
            serverModels.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public void clearAll() {
        if (!serverModels.isEmpty()) {
            serverModels.clear();
            notifyDataSetChanged();
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    private ServerModel getItem(int position) {
        if (serverModels != null) {
            return serverModels.get(position);
        }
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvip7;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvip7 = itemView.findViewById(R.id.tvip7);
        }
    }
}
