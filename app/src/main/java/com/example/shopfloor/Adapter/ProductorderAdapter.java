package com.example.shopfloor.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shopfloor.Activity.Add_DocActivity;
import com.example.shopfloor.Models.Productorder;
import com.example.shopfloor.R;

import java.util.ArrayList;
import java.util.List;

public class ProductorderAdapter extends RecyclerView.Adapter<ProductorderAdapter.MyViewHolder> {

    private Context context;
    private List<Productorder> list_item;


    public ProductorderAdapter(List<Productorder> list, Context context) {
        this.context = context;
        list_item = list;
    }

    public ProductorderAdapter(Context context) {
        this.context = context;
        list_item = new ArrayList<>();
    }

    @NonNull
    @Override
    public ProductorderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.po_row_item, parent, false);
        final ProductorderAdapter.MyViewHolder holder = new ProductorderAdapter.MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Productorder productorder = list_item.get(holder.getAdapterPosition());

        holder.tv_docNum.setText(String.valueOf(productorder.getDocNum()));
        holder.tv_itemCode.setText(productorder.getItemCode());
        holder.tv_itemName.setText(productorder.getItemName());

       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Add_DocActivity.class);
                intent.putExtra("namaprod", productorder.getItemName());
                intent.putExtra("product", String.valueOf(productorder.getItemCode()));
                intent.putExtra("noprod", String.valueOf(productorder.getDocNum()));
                intent.putExtra("status", productorder.getStatus());
                intent.putExtra("route", productorder.getCode());
                intent.putExtra("routeCode", productorder.getName());
                intent.putExtra("rencQty", String.valueOf(productorder.getPlannedQty()));
                context.startActivity (intent);
                ((ProductorderListActivity) context).finish();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }

    public void add(Productorder r) {
            list_item.add(r);
        notifyItemInserted(list_item.size() - 1);
    }

    public void addAll(List<Productorder> moveResults) {
        for (Productorder result : moveResults) {
            add(result);
        }
    }

    private void remove(Productorder r) {
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

    private Productorder getItem (int position){
        if (list_item != null) {
            return list_item.get(position);
        }
        return null;
    }

    public List<Productorder> getList_item() {
        return list_item;
    }


     class  MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_itemName;
        public TextView tv_itemCode;
        public TextView tv_docNum;
        public EditText searchPO;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_docNum = itemView.findViewById(R.id.tv_docNum);
            tv_itemCode = itemView.findViewById(R.id.tv_itemCode);
            tv_itemName = itemView.findViewById(R.id.tv_itemName);
            searchPO = itemView.findViewById(R.id.searchPO);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Add_DocActivity.tvNm_prod1.setText(list_item.get(getAdapterPosition()).getItemName());
                    Add_DocActivity.tvNo_Prod1.setText(String.valueOf(list_item.get(getAdapterPosition()).getDocNum()));
                    Add_DocActivity.tvprod1.setText(list_item.get(getAdapterPosition()).getItemCode());
                    Add_DocActivity.tvRoute_Code1.setText(list_item.get(getAdapterPosition()).getCode());
                    Add_DocActivity.tvRoute_Code2.setText(list_item.get(getAdapterPosition()).getName());
                    Add_DocActivity.tvQty_rencProd1.setText(list_item.get(getAdapterPosition()).getPlannedQty().replace(".000000",""));
                    Add_DocActivity.tvSts_Prod1.setText(list_item.get(getAdapterPosition()).getStatus());
                    Add_DocActivity.dialog.dismiss();
                }
            });
        }
    }
}
