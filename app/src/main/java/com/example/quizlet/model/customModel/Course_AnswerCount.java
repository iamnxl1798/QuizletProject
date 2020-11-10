package com.example.quizlet.model.customModel;

import com.example.quizlet.model.Courses;

public class Course_AnswerCount {
    private long id;
    private String courseName;
    private int answerNum;
    private long creatorDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(int answerNum) {
        this.answerNum = answerNum;
    }

    public long getCreatorDate() {
        return creatorDate;
    }

    public void setCreatorDate(long creatorDate) {
        this.creatorDate = creatorDate;
    }

    public Course_AnswerCount(String courseName, int answerNum, long creatorDate) {
        this.courseName = courseName;
        this.answerNum = answerNum;
        this.creatorDate = creatorDate;
    }
}
