package com.example.quizlet.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quizlet.COMMON;
import com.example.quizlet.dao.CourseDAO;
import com.example.quizlet.database.MyDatabase;
import com.example.quizlet.model.Answers;
import com.example.quizlet.model.Courses;
import com.example.quizlet.model.Item;
import com.example.quizlet.R;
import com.example.quizlet.adapter.ItemAdapter;
import com.example.quizlet.model.Question;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCourseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    private List<Item> items;
    private ItemAdapter adapter;
    private Button checkBtn;
    private FloatingActionButton addItemBtn;
    public MyDatabase myDatabase;
    public CourseDAO addCourseDAO;
    public EditText edit_category;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCourseFragment newInstance(String param1, String param2) {
        AddCourseFragment fragment = new AddCourseFragment();
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
        final View view = inflater.inflate(R.layout.fragment_add_course, container, false);
        items = new ArrayList<>();
        final List<Answers> listDef = new ArrayList();
        listDef.add(new Answers());
        items.add(new Item(new Question(), listDef));
        recyclerView = view.findViewById(R.id.rcl_view);
        adapter = new ItemAdapter(items, getContext());
        recyclerView.setAdapter(adapter);
        edit_category = view.findViewById(R.id.edit_category);
        edit_category.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!edit_category.getText().toString().isEmpty()) {
                    edit_category.setBackgroundColor(Color.WHITE);
                }
            }
        });
        checkBtn = view.findViewById(R.id.checkBtn);
        checkBtn.setEnabled(true);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!edit_category.getText().toString().isEmpty()) {
                        edit_category.setBackgroundColor(Color.WHITE);
                        items = ((ItemAdapter) recyclerView.getAdapter()).getItems();
                        myDatabase = Room.databaseBuilder(getContext(), MyDatabase.class, COMMON.DB_NAME).allowMainThreadQueries().build();
                        addCourseDAO = myDatabase.createCourseDAO();
                        edit_category = view.findViewById(R.id.edit_category);
                        Date currentTime = Calendar.getInstance().getTime();
                        Courses courses = new Courses(edit_category.getText().toString(), currentTime.getTime());
                        addCourseDAO.insertCourse(courses);
                        for (Item item : items) {
                            if(!(item.getTerm().getQuestionName()==null)){
                                if(!item.getTerm().getQuestionName().isEmpty()){
                                    courses = addCourseDAO.getLastCourse();
                                    addCourseDAO.insertQuestion(new Question(item.getTerm().getQuestionName(), courses.getId()));
                                    Question addQuestion=addCourseDAO.getLastQuestion();
                                    List<Answers> temp= new ArrayList<>();
                                    for(Answers answer:item.getDefinition()){
                                        if(answer.getAnswer()==null||answer.getAnswer().isEmpty()){
                                            temp.add(answer);
                                        }
                                        else {
                                            answer.setQuestionId(addQuestion.getId());
                                            addCourseDAO.insertAnswer(answer);
                                        }
                                    }
                                    if(item.getDefinition().size()==temp.size()){
                                        addCourseDAO.delLastQuestion();
                                    }
                                }
                            }
                        }
                        if (addCourseDAO.getQuestionOfLastCourse().size() == 0) {
                            throw new Exception("Course cannot be blank");
                        }
                        Toast.makeText(getActivity(), "Add " + courses.getName() + " course success!", Toast.LENGTH_LONG).show();
                        checkBtn.setEnabled(false);
                        checkBtn.setBackground(null);
                    }else{
                        edit_category.setBackgroundColor(Color.rgb(251,227,228));
                        Toast.makeText(getActivity(), "Add course failed!", Toast.LENGTH_LONG).show();
                        addCourseDAO.delLastCourse();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    addCourseDAO.delLastCourse();
                }
            }
        });
        addItemBtn = view.findViewById(R.id.addItemBtn);
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items = ((ItemAdapter) recyclerView.getAdapter()).getItems();
                List<Answers> tempString = new ArrayList<>();
                tempString.add(new Answers());
                items.add(new Item(new Question(), tempString));
                recyclerView.setAdapter(adapter);
                recyclerView.smoothScrollToPosition(items.size()-1);

            }
        });
        return view;
    }

}