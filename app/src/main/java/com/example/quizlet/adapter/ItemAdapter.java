package com.example.quizlet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizlet.R;
import com.example.quizlet.model.Item;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder>  {
    ArrayList<Item> items;
    Context context;

    public Adapter(ArrayList<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.setContent(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        EditText tvTerm, tvDefinition;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tvTerm = itemView.findViewById(R.id.tv_term);
            tvDefinition = itemView.findViewById(R.id.tv_definition);
        }
        public void setContent(Item item){

        }
    }
}
