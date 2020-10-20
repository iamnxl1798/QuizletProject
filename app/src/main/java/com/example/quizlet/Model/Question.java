package com.example.quizlet.Model;

public class Question {
    private String name,answer;
    private boolean isGim;

    public Question() {
    }

    public Question(String name, String answer, boolean isGim) {
        this.name = name;
        this.answer = answer;
        this.isGim = isGim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
