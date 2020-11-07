package com.example.quizlet.model.customModel;

import com.example.quizlet.model.Courses;

public class Course_AnswerCount {
    private Courses courses;
    private int answerNum;

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public int getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(int answerNum) {
        this.answerNum = answerNum;
    }

    public Course_AnswerCount(Courses courses, int answerNum) {
        this.courses = courses;
        this.answerNum = answerNum;
    }
}
