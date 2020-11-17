package com.example.quizlet.model;

public class AnswersOfQuestion {
    private long answerId;
    private long questionId;

    public AnswersOfQuestion() {
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}
