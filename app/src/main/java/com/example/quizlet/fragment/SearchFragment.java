package com.example.quizlet.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.quizlet.COMMON;
import com.example.quizlet.R;
import com.example.quizlet.adapter.CourseAdapter;
import com.example.quizlet.adapter.ItemAdapter;
import com.example.quizlet.dao.CourseDAO;
import com.example.quizlet.database.MyDatabase;
import com.example.quizlet.model.Courses;
import com.example.quizlet.model.Question;
import com.example.quizlet.model.customModel.Course_AnswerCount;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MyDatabase myDatabase;
    private CourseDAO courseDAO;
    private RecyclerView recyclerView;
    private List<Course_AnswerCount> items;
    private CourseAdapter adapter;
    private SearchView searchView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);
        myDatabase = Room.databaseBuilder(getContext(), MyDatabase.class, COMMON.DB_NAME).allowMainThreadQueries().build();
        courseDAO = myDatabase.createCourseDAO();
        items=courseDAO.getCoursesSearchView();
        List<Question> list=courseDAO.getQuestion();
        recyclerView = view.findViewById(R.id.searchCouseRecycle);
        adapter = new CourseAdapter(items, getContext());
        recyclerView.setAdapter(adapter);
        searchView=view.findViewById(R.id.searchBar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((CourseAdapter)recyclerView.getAdapter()).getFilter().filter(newText);
                return false;
            }
        });
        return view;
    }
}