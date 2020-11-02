package com.example.quizlet.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class ImportantQuestions {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ForeignKey(entity = JoinedCourses.class,
            parentColumns = {"id"},
            childColumns = {"joinCourseId"},
            onDelete = ForeignKey.CASCADE
    )
    private long joinCourseId;

    @ForeignKey(entity = Question.class,
            parentColumns = {"id"},
            childColumns = {"questionId"},
            onDelete = ForeignKey.CASCADE
    )
    private long questionId;

    public ImportantQuestions() {
    }

    public ImportantQuestions(long joinCourseId, long questionId) {
        this.joinCourseId = joinCourseId;
        this.questionId = questionId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getJoinCourseId() {
        return joinCourseId;
    }

    public void setJoinCourseId(long joinCourseId) {
        this.joinCourseId = joinCourseId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}

