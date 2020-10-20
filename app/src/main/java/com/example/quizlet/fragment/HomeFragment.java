package com.example.quizlet.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizlet.Model.ThuMucHoc;
import com.example.quizlet.R;
import com.example.quizlet.StudyActivity;
import com.example.quizlet.adapter.HomeAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView recyView_Home;
    List<ThuMucHoc> thuMucHocList;
    private FloatingActionButton floatingButtonAdd;
    HomeAdapter homeAdapter;
    private boolean check_Add;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        thuMucHocList = new ArrayList<>();
        thuMucHocList.add(new ThuMucHoc("HCI301", "Cuongnv", R.drawable.cuong));
        thuMucHocList.add(new ThuMucHoc("SWR201", "Cuongnv2", R.drawable.cuong2));
        thuMucHocList.add(new ThuMucHoc("SWR201", "Cuongnv2", R.drawable.cuong));
        thuMucHocList.add(new ThuMucHoc("SWR201123", "Cuongnv2", R.drawable.cuong2));
        thuMucHocList.add(new ThuMucHoc("SWR2023211", "Cuongnv2", R.drawable.cuong));
        thuMucHocList.add(new ThuMucHoc("SWR202311", "Cuongnv2", R.drawable.cuong2));

        AnhXa(view);

        homeAdapter = new HomeAdapter(getActivity(), thuMucHocList, new HomeAdapter.OnItemClickListener() {
            @Override
            public void OnClickMore(int position, ThuMucHoc doanhthu) {
                Intent intent = new Intent(getActivity(), StudyActivity.class);
                startActivity(intent);
            }
        });
        recyView_Home.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyView_Home.setAdapter(homeAdapter);
        if (check_Add == true) {
            homeAdapter.notifyItemInserted(thuMucHocList.size() + 1);
        }

        //Ong Add vao day nhe Lam
        floatingButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    public void AnhXa(View view) {
        recyView_Home = view.findViewById(R.id.reviewHome);
        floatingButtonAdd = view.findViewById(R.id.float_addThuMuc);
    }

}