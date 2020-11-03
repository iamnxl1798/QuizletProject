package com.example.quizlet.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.example.quizlet.model.ThuMucHoc;
import com.example.quizlet.R;
import com.example.quizlet.StudyActivity;
import com.example.quizlet.adapter.HomeAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView recyView_Home;
    List<ThuMucHoc> thuMucHocList;
    HomeAdapter homeAdapter;
    private boolean check_Add;
    ViewFlipper viewFlipper;
    Animation in, out;

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
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager.scrollToPosition(0);
        recyView_Home.setLayoutManager(layoutManager);
        recyView_Home.setAdapter(homeAdapter);
        if (check_Add == true) {
            homeAdapter.notifyItemInserted(thuMucHocList.size() + 1);
        }

        in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        return view;
    }

    public void AnhXa(View view) {
        recyView_Home = view.findViewById(R.id.reviewHome);
        viewFlipper = view.findViewById(R.id.quangcao);
    }

}