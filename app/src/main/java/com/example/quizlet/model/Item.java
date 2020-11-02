package com.example.quizlet.model;

import java.util.ArrayList;

public class Item {
    private String term;
    private ArrayList<String> definition;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public ArrayList<String> getDefinition() {
        return definition;
    }

    public void setDefinition(ArrayList<String> definition) {
        this.definition = definition;
    }

    public Item(String term, ArrayList<String> definition) {
        this.term = term;
        this.definition = definition;
    }


}
