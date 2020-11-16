package com.example.quizlet.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.quizlet.COMMON;
import com.example.quizlet.HocPhanActivity;
import com.example.quizlet.adapter.CourseAdapter;
import com.example.quizlet.dao.CourseDAO;
import com.example.quizlet.database.MyDatabase;
import com.example.quizlet.model.ThuMucHoc;
import com.example.quizlet.R;
import com.example.quizlet.StudyActivity;
import com.example.quizlet.model.customModel.Course_AnswerCount;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView recyView_Home;
    ViewFlipper viewFlipper;
    Animation in, out;
    private RecyclerView recyView_Home2;
    List<ThuMucHoc> thuMucHocDaJoin;
    TextView xemtat1, xemtat2;
    public MyDatabase myDatabase;
    public CourseDAO courseDAO;
    CourseAdapter courseAdapter,courseAdapterMine;
    List<Course_AnswerCount> coursesList, courseListMine;
    private long userID;
    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        myDatabase = Room.databaseBuilder(getContext(), MyDatabase.class, COMMON.DB_NAME).allowMainThreadQueries().build();
        courseDAO = myDatabase.createCourseDAO();
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("taikhoan", Context.MODE_PRIVATE);
        userID=sharedPreferences.getLong("userID",-1);
        thuMucHocDaJoin = new ArrayList<>();
        thuMucHocDaJoin.add(new ThuMucHoc("HCI301", "Cuongnv", R.drawable.cuong));
        thuMucHocDaJoin.add(new ThuMucHoc("SWR201", "Cuongnv2", R.drawable.cuong2));
        thuMucHocDaJoin.add(new ThuMucHoc("SWR201", "Cuongnv2", R.drawable.cuong));
        thuMucHocDaJoin.add(new ThuMucHoc("SWR201123", "Cuongnv2", R.drawable.cuong2));
        thuMucHocDaJoin.add(new ThuMucHoc("SWR2023211", "Cuongnv2", R.drawable.cuong));
        thuMucHocDaJoin.add(new ThuMucHoc("SWR202311", "Cuongnv2", R.drawable.cuong2));

        AnhXa(view);


        in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setInAnimation(in);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        xemtat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HocPhanActivity.class);
                startActivity(intent);
            }
        });

        xemtat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HocPhanActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        coursesList = courseDAO.getCoursesSearchView();
        courseListMine = courseDAO.getCoursesSearchViewByUserID(userID);

        courseAdapter = new CourseAdapter(coursesList, getActivity(), new CourseAdapter.OnItemClickListener() {
            @Override
            public void OnClickMore(Course_AnswerCount course_AnswerCount) {
                Intent intent = new Intent(getContext(), StudyActivity.class);
                intent.putExtra("idCourse", course_AnswerCount.getId());
                intent.putExtra("totalQuestion", course_AnswerCount.getAnswerNum());
                startActivity(intent);
                Toast.makeText(getContext(), "" + course_AnswerCount.getAnswerNum(), Toast.LENGTH_SHORT).show();
            }
        });

        courseAdapterMine = new CourseAdapter(courseListMine, getActivity(), new CourseAdapter.OnItemClickListener() {
            @Override
            public void OnClickMore(Course_AnswerCount course_AnswerCount) {
                Intent intent = new Intent(getContext(), StudyActivity.class);
                intent.putExtra("idCourse", course_AnswerCount.getId());
                intent.putExtra("totalQuestion", course_AnswerCount.getAnswerNum());
                startActivity(intent);
                Toast.makeText(getContext(), "" + course_AnswerCount.getAnswerNum(), Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager.scrollToPosition(0);
        recyView_Home.setLayoutManager(layoutManager);
        recyView_Home.setAdapter(courseAdapter);

        LinearLayoutManager layoutManager1 =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager1.scrollToPosition(0);
        recyView_Home2.setLayoutManager(layoutManager1);
        recyView_Home2.setAdapter(courseAdapterMine);
    }

    public void AnhXa(View view) {
        recyView_Home = view.findViewById(R.id.reviewHome);
        viewFlipper = view.findViewById(R.id.quangcao);
        recyView_Home2 = view.findViewById(R.id.reviewHome2);
        xemtat1 = view.findViewById(R.id.xemtatcahocphan);
        xemtat2 = view.findViewById(R.id.xemtatcahocphandajoin);
    }

}