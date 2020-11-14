package com.example.quizlet.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {
    private String term;
    private List<Answers> definition;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public List<Answers> getDefinition() {
        return definition;
    }

    public void setDefinition(List<Answers> definition) {
        this.definition = definition;
    }

    public Item(String term, List<Answers> definition) {
        this.term = term;
        this.definition = definition;
    }
}
