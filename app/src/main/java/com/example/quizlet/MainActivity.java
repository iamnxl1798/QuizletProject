package com.example.quizlet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.quizlet.adapter.ItemAdapter;
import com.example.quizlet.model.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Item> items;
    private ItemAdapter adapter;
    private ImageView checkBtn;
    private FloatingActionButton addItemBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items =new ArrayList<>();
        final ArrayList<String> listDef=new ArrayList();
        listDef.add("");
        items.add(new Item("",listDef));
        recyclerView = findViewById(R.id.rcl_view);
        adapter  = new ItemAdapter(items, MainActivity.this);
        recyclerView.setAdapter(adapter);
        checkBtn=findViewById(R.id.checkBtn);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items=((ItemAdapter)recyclerView.getAdapter()).getItems();
            }
        });
        addItemBtn=findViewById(R.id.addItemBtn);
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items=((ItemAdapter)recyclerView.getAdapter()).getItems();
                ArrayList<String> tempString= new ArrayList<>();
                tempString.add("");
                items.add(new Item("",tempString));
                recyclerView.setAdapter(adapter);
            }
        });
    }
}