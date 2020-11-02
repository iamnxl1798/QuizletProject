package com.example.quizlet.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Answers {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo
    private String answer;
    @ColumnInfo
    private boolean abc;

    @ForeignKey(entity = Question.class,
            parentColumns = {"id"},
            childColumns = {"questionId"},
            onDelete = ForeignKey.CASCADE
    )
    private long questionId;

    public Answers() {
    }

    public Answers(String answer, boolean abc, long questionId) {
        this.answer = answer;
        this.abc = abc;
        this.questionId = questionId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isAbc() {
        return abc;
    }

    public void setAbc(boolean abc) {
        this.abc = abc;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}
