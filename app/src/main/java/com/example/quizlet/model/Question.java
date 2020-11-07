package com.example.quizlet.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Question {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo
    private String questionName;

    @ColumnInfo
    private String answer;

    @ColumnInfo
    private boolean isGim;

    @ColumnInfo
    private String image;

    public Question() {
    }

    public Question(String name) {
        this.questionName = name;
    }

    public Question(String name, String answer, boolean isGim) {
        this.questionName = name;
        this.answer = answer;
        this.isGim = isGim;
    }

    public String getName() {
        return questionName;
    }

    public void setName(String name) {
        this.questionName = name;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isGim() {
        return isGim;
    }

    public void setGim(boolean gim) {
        isGim = gim;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
