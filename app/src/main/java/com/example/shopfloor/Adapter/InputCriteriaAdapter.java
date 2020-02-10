package com.example.shopfloor.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shopfloor.Fragment.CriteriaFragment;
import com.example.shopfloor.Models.Upcriteria;
import com.example.shopfloor.R;

import java.util.ArrayList;
import java.util.List;

public class InputCriteriaAdapter extends RecyclerView.Adapter<InputCriteriaAdapter.ViewHolder> {

    private Context context;
    private List<Upcriteria> list_item;

    public InputCriteriaAdapter(Context context) {
        this.context = context;
        list_item = new ArrayList<>();
    }

    public InputCriteriaAdapter(CriteriaFragment criteriaFragment) {
        list_item = new ArrayList<>();
    }

    public List<Upcriteria> getData() {
        return  list_item;
    }

    public InputCriteriaAdapter(List<Upcriteria> list, Context context) {
        this.context = context;
        list_item = list;
    }



    @NonNull
    @Override
    public InputCriteriaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inputcriteria_row, parent, false);
        final InputCriteriaAdapter.ViewHolder holder = new InputCriteriaAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Upcriteria upcriteria = list_item.get(holder.getAdapterPosition());

        holder.tvcriteria2.setText(String.valueOf(upcriteria.getCriteria()));
        holder.tvcritdesc2.setText(String.valueOf(upcriteria.getCriteriaDesc()));
        holder.tvstandard2.setText(String.valueOf(upcriteria.getStandard()));
        holder.tvactual2.setText(String.valueOf(upcriteria.getActualResult()));
//        holder.etactual1.addTextChangedListener(new EditTextSaverWatcher(upcriteria));



//        holder.etactual1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if (b == false) {
//                    hideKeyboard(view.getContext(), view);
//                }
//            }
//        });
//    }

//    public static void hideKeyboard(Context context, View view) {
//        if ((context == null) || (view == null)) {
//            return;
//        }
//        InputMethodManager mgr =
//                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }

    public void add(Upcriteria r) {
        list_item.add(r);
        notifyItemInserted(list_item.size() - 1);
    }

    public void addAll(List<Upcriteria> moveResults) {
        for (Upcriteria results : moveResults) {
            add(results);
        }
    }

    private void remove(Upcriteria r) {
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

    private Upcriteria getItem(int position) {
        if (list_item != null) {
            return  list_item.get(position);
        }
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvcriteria2;
        public TextView tvcritdesc2;
        public TextView tvstandard2;
        public TextView tvactual2;
        public EditText etactual1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvcriteria2 = itemView.findViewById(R.id.tvcriteria2);
            tvcritdesc2 = itemView.findViewById(R.id.tvcritdesc2);
            tvstandard2 = itemView.findViewById(R.id.tvstandard2);
            tvactual2 = itemView.findViewById(R.id.tvactual2);
            etactual1 = itemView.findViewById(R.id.etactual1);
        }
    }


    public class EditTextSaverWatcher implements TextWatcher {

        private Upcriteria upcriteria;

        public EditTextSaverWatcher(Upcriteria upcriteria) {
            this.upcriteria= upcriteria;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Another option is to save name here
        }

        @Override
        public void afterTextChanged(Editable s) {
            upcriteria.setActualResult(s.toString());
        }
    }
}
