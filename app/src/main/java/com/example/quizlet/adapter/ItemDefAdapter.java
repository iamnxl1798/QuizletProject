package com.example.quizlet.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizlet.R;
import com.example.quizlet.model.Answers;

import java.util.ArrayList;

public class ItemDefAdapter extends RecyclerView.Adapter<ItemDefAdapter.Holder> {
    ArrayList<Answers> items;
    Context context;

    public ItemDefAdapter(ArrayList<Answers> items, Context context) {
        this.items = items;
        this.context = context;
    }
    public ArrayList<Answers> getItems() {
        return items;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemDefAdapter.Holder(LayoutInflater.from(context).inflate(R.layout.item_def,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final EditText txtDef=holder.getDef();
        final CheckBox isTrue =holder.getIsTrue();
        txtDef.setText(items.get(position).getAnswer());
        isTrue.setChecked(items.get(position).isTrue());
        txtDef.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                items.set(position,new Answers(txtDef.getText().toString(),isTrue.isChecked()));
            }
        });
        isTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ( isChecked )
                {
                    items.set(position,new Answers(txtDef.getText().toString(),true));
                }
                else{
                    items.set(position,new Answers(txtDef.getText().toString(),false));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        EditText txtDef;
        CheckBox checkBox;
        public Holder(@NonNull View itemView) {
            super(itemView);
            txtDef=itemView.findViewById(R.id.tv_definition);
            checkBox=itemView.findViewById(R.id.isTrue);
        }
        public EditText getDef(){
            return txtDef;
        }
        public CheckBox getIsTrue(){
            return checkBox;
        }

    }
}
