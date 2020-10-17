package com.example.quizlet.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizlet.MainActivity;
import com.example.quizlet.R;
import com.example.quizlet.model.Item;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Holder>  {

    ArrayList<Item> items;
    Context context;

    public ItemAdapter(ArrayList<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }
    public ArrayList<Item> getItems() {
        return items;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        final EditText term=holder.getTermValue();
        final EditText def=holder.getDefValue();
        ImageView addBtn=holder.getAddBtn();
        final RecyclerView defRecycle = holder.getDefRecycle();
        final ItemDefAdapter adapterr  = new ItemDefAdapter(items.get(position).getDefinition(), context);
        defRecycle.setAdapter(adapterr);
        term.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                items.get(position).setTerm(term.getText().toString());
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> listDef=((ItemDefAdapter)defRecycle.getAdapter()).getItems();
                listDef.add("");
                items.get(position).setDefinition(listDef);
                defRecycle.setAdapter(adapterr);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        EditText tvTerm, tvDefinition;
        ImageView addBtn;
        RecyclerView defRecycle;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tvTerm = itemView.findViewById(R.id.tv_term);
            tvDefinition = itemView.findViewById(R.id.tv_definition);
            defRecycle=itemView.findViewById(R.id.defRecycle);
            addBtn=itemView.findViewById(R.id.addBtn);
        }
        public EditText getTermValue(){
            return tvTerm;
        }
        public EditText getDefValue(){
            return tvDefinition;
        }
        public RecyclerView getDefRecycle(){
            return defRecycle;
        }
        public ImageView getAddBtn(){
            return addBtn;
        }
    }
}

