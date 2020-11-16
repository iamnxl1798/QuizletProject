package com.example.quizlet.model.customModel;

import com.example.quizlet.model.Courses;
import com.example.quizlet.model.User;

public class Course_AnswerCount {
    private long id;
    private String courseName;
    private int answerNum;
    private long creatorDate;
    private String username;
    private String password;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

//    public Course_AnswerCount(String courseName, int answerNum, long creatorDate) {
//        this.courseName = courseName;
//        this.answerNum = answerNum;
//        this.creatorDate = creatorDate;
//    }

    public Course_AnswerCount(String courseName, int answerNum, long creatorDate, String username, String password, String email) {
        this.courseName = courseName;
        this.answerNum = answerNum;
        this.creatorDate = creatorDate;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
