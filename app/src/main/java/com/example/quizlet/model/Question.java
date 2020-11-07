package com.example.quizlet.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Question {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo
    private String questionName;

    @ForeignKey(entity = Courses.class,
            parentColumns = {"id"},
            childColumns = {"courseId"},
            onDelete = ForeignKey.CASCADE
    )
    private long courseId;

    public Question() {
    }

    public Question(String name) {
        this.questionName = name;
    }

    public Question(String questionName, long courseId) {
        this.questionName = questionName;
        this.courseId = courseId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
