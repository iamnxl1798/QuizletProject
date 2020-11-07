package com.example.quizlet.model;

import java.util.ArrayList;

public class Item {
    private String term;
    private ArrayList<Answers> definition;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public ArrayList<Answers> getDefinition() {
        return definition;
    }

    public void setDefinition(ArrayList<Answers> definition) {
        this.definition = definition;
    }

    public Item(String term, ArrayList<Answers> definition) {
        this.term = term;
        this.definition = definition;
    }


}
