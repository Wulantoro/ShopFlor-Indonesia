package com.example.shopfloor.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.shopfloor.Activity.EditRejectActivity;
import com.example.shopfloor.Activity.RejectActivity;
import com.example.shopfloor.Fragment.RejectFragment;
import com.example.shopfloor.Models.InputReject;
import com.example.shopfloor.Models.ServerModel;
import com.example.shopfloor.R;
import com.example.shopfloor.Utils.GlobalVars;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmResults;

public class InputRejectAdapter extends RecyclerView.Adapter<InputRejectAdapter.ViewHolder> {

    private Context context;
    private List<InputReject> list_item;

    public InputRejectAdapter(Context context) {
        this.context = context;
        list_item = new ArrayList<>();
    }

    public InputRejectAdapter(RejectFragment rejectFragment) {
        list_item = new ArrayList<>();
    }

    public InputRejectAdapter(List<InputReject> list, Context context) {
        this.context = context;
        list_item = list;
    }

    public List<InputReject> getData() {
        return list_item;
    }


    @NonNull
    @Override
    public InputRejectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reject_row, parent, false);
        final InputRejectAdapter.ViewHolder holder = new InputRejectAdapter.ViewHolder(v);
        return holder;
    }

//    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final InputReject inputReject = list_item.get(holder.getAdapterPosition());

        holder.tvcodereject1.setText(inputReject.getRejectCode());
        holder.tvqty1.setText(inputReject.getRejectQty().replace(".000000", ""));
        holder.tvnamareject.setText(inputReject.getRejectName());
        holder.tvid0.setText(String.valueOf(inputReject.getId()));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {

                PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.delete:

                                new AlertDialog.Builder(context)
                                        .setTitle("Hapus")
                                        .setMessage("Yakin ingin menghapus?")
                                        .setNegativeButton(android.R.string.no, null)
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface arg0, int arg1) {
                                                Realm realm = Realm.getDefaultInstance();
                                                RealmResults<ServerModel> results = realm.where(ServerModel.class).findAll();
                                                String text = "";
                                                for (ServerModel c : results) {
                                                    text = text + c.getAddress();

//                                                    AndroidNetworking.delete(GlobalVars.BASE_IP + "index.php/inputreject?id=" + inputReject.getId())
                                                    AndroidNetworking.delete(c.getAddress() + "shopfloor2/index.php/inputreject?docEntry=" + inputReject.getDocEntry())
                                                            .setPriority(Priority.MEDIUM)
                                                            .build()
                                                            .getAsJSONObject(new JSONObjectRequestListener() {
                                                                @Override
                                                                public void onResponse(JSONObject response) {
                                                                    try {
                                                                        String message = response.getString("message");
                                                                        Toasty.success(v.getContext(), message, Toast.LENGTH_SHORT).show();
                                                                    } catch (JSONException e) {
                                                                        e.printStackTrace();
                                                                        Toast.makeText(v.getContext(), "JSONExceptions" + e, Toast.LENGTH_SHORT).show();
                                                                    }

                                                                }

                                                                @Override
                                                                public void onError(ANError anError) {
                                                                    Toasty.error(v.getContext(), "Gagal menghapus reject", Toast.LENGTH_SHORT).show();

                                                                }
                                                            });
                                                }
                                                remove(inputReject);
                                            }
                                        }).create().show();
                                break;

                            case R.id.update:
                                Intent intent = new Intent(context, EditRejectActivity.class);
                                intent.putExtra("edit", inputReject);
                                context.startActivity(intent);
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
                return false;
            }
        });
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
        public TextView tvid0;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvcodereject1 = itemView.findViewById(R.id.tvcodereject1);
            tvqty1 = itemView.findViewById(R.id.tvqty1);
            tvnamareject = itemView.findViewById(R.id.tvnamareject);
            tvid0 = itemView.findViewById(R.id.tvid0);

        }

    }
}
