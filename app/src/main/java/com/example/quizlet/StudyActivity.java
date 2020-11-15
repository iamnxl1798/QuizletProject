package com.example.quizlet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.quizlet.dao.AnswerDAO;
import com.example.quizlet.dao.CourseDAO;
import com.example.quizlet.dao.QuesstionDAO;
import com.example.quizlet.database.MyDatabase;
import com.example.quizlet.model.Answers;
import com.example.quizlet.model.Item;
import com.example.quizlet.model.Question;
import com.example.quizlet.adapter.StudyAdapter;
import com.example.quizlet.receiver.AlarmReceiver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StudyActivity extends AppCompatActivity {

    StudyAdapter studyAdapter;
    List<Question> questions;
    private RecyclerView recyView_Study;
    ImageView back, henGio, closeHenGio, acceptHenGio, btnEditCourse,btnDelCourse;
    LinearLayout checkAll, checkSao, theghinho, ghepThe;
    View view1, view2;
    MyDatabase myDatabase;
    private QuesstionDAO quesstionDAO;
    AnswerDAO answerDAO;
    private CourseDAO courseDAO;
    TextView totalQuestion, displayTime;
    private int lastSelectedHour = -1;
    private int lastSelectedMinute = -1;
    Calendar calendar;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        questions = new ArrayList<>();
        AnhXa();

        quesstionDAO = myDatabase.createQuesstionDAO();
        answerDAO = myDatabase.createAnswerDAO();
//
        Intent intent = this.getIntent();
        String totalQ = intent.getStringExtra("totalQuestion");
        final long courseId = intent.getLongExtra("idCourse",-1);
//
        questions = quesstionDAO.getAllQuesstionByCourseId(courseId);
//        items.add(new Item(question.getQuestionName(), answers));
        totalQuestion.setText(totalQ + " thuật ngữ");
        Toast.makeText(StudyActivity.this, "" + totalQ, Toast.LENGTH_SHORT).show();

        final List<Item> items = new ArrayList<>();

//        for (int i = 0; i < questions.size(); i++) {
//            List<Answers> answers = answerDAO.getAnswerByQuestion(questions.get(i).getId());
//
////            items.add(new Item(questions.get(i).getQuestionName(), (ArrayList<Answers>) answers));
//
//        }

        studyAdapter = new StudyAdapter(this, items, new StudyAdapter.OnItemClickListener() {
            @Override
            public void OnClickMore(int position) {

            }
        });

        recyView_Study.setLayoutManager(new LinearLayoutManager(this));
        recyView_Study.setAdapter(studyAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        view1.setBackgroundColor(Color.BLUE);
        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view1.setBackgroundColor(Color.BLUE);
                view2.setBackgroundColor(Color.WHITE);

            }
        });

        checkSao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view2.setBackgroundColor(Color.BLUE);
                view1.setBackgroundColor(Color.WHITE);
            }
        });

        theghinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudyActivity.this, TheGhiNhoActivity.class);
                intent.putExtra("QuestionListExtra", courseId + "");
                startActivity(intent);
            }
        });

        ghepThe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudyActivity.this, ghepTheActivity.class);
                if(courseId!=-1){
                    intent.putExtra("idCourse",courseId);
                    startActivity(intent);
                }
            }
        });

        btnEditCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudyActivity.this, EditCourseActivity.class);
                if(courseId!=-1){
                    intent.putExtra("idCourse",courseId);
                    startActivity(intent);
                }
            }
        });

        btnDelCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(courseId!=-1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(StudyActivity.this);
                    builder.setTitle("Delete course");
                    builder.setMessage("Do you really want to delete this course?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            int resultDel=courseDAO.delCourseByID(courseId);
                            finish();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
        closeHenGio.setVisibility(View.INVISIBLE);
        acceptHenGio.setVisibility(View.INVISIBLE);

        HenGio();
    }

    public void AnhXa() {
        recyView_Study = findViewById(R.id.ryce_study);
        back = findViewById(R.id.back_home);
        checkAll = findViewById(R.id.checkHocAll);
        checkSao = findViewById(R.id.checkHocSaoNgoai);
        view1 = findViewById(R.id.checkHocTatCa);
        view2 = findViewById(R.id.checkHocSao);
        theghinho = findViewById(R.id.theghinho);
        ghepThe = findViewById(R.id.ghepthe);
        totalQuestion = findViewById(R.id.total_question);
        henGio = findViewById(R.id.hengio);
        displayTime = findViewById(R.id.display_time);
        closeHenGio = findViewById(R.id.close_Hen_Gio);
        acceptHenGio = findViewById(R.id.accep_Hen_Gio);
        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        btnEditCourse=findViewById(R.id.btnEditCourse);
        btnDelCourse=findViewById(R.id.btnDelCourse);
        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, COMMON.DB_NAME).allowMainThreadQueries().build();
        courseDAO = myDatabase.createCourseDAO();
    }

    public void HenGio() {
        henGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelectTime();
                final Intent intent = new Intent(StudyActivity.this, AlarmReceiver.class);

                acceptHenGio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        intent.putExtra("extra", "on");
                        pendingIntent = PendingIntent.getBroadcast(StudyActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                        closeHenGio.setVisibility(View.VISIBLE);
                        acceptHenGio.setVisibility(View.INVISIBLE);
                    }
                });

                closeHenGio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent.putExtra("extra", "off");
                        displayTime.setText("");
                        alarmManager.cancel(pendingIntent);
                        sendBroadcast(intent);
                        closeHenGio.setVisibility(View.INVISIBLE);

                    }
                });
            }

        });
    }

    public void buttonSelectTime() {

        if (this.lastSelectedHour == -1) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            this.lastSelectedHour = c.get(Calendar.HOUR_OF_DAY);
            this.lastSelectedMinute = c.get(Calendar.MINUTE);
        }
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                calendar.set(Calendar.MINUTE, minute);
                String String_gio = hourOfDay + "";
                String String_phut = minute + "";
                if (minute < 10) {
                    String_phut = "0" + minute;
                }
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                displayTime.setText("Giờ bạn hẹn là: " + String_gio + " : " + String_phut);
                acceptHenGio.setVisibility(View.VISIBLE);
                closeHenGio.setVisibility(View.INVISIBLE);

            }
        };

        TimePickerDialog timePickerDialog = null;
        timePickerDialog = new TimePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                onTimeSetListener, lastSelectedHour, lastSelectedMinute, true);

        timePickerDialog.show();
    }
}