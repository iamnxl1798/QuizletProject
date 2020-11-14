package com.example.quizlet.model;

import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {
    private Question term;
    private List<Answers> definition;

    public Question getTerm() {
        return term;
    }

    public void setTerm(Question term) {
        this.term = term;
    }

    public List<Answers> getDefinition() {
        return definition;
    }

    public void setDefinition(List<Answers> definition) {
        this.definition = definition;
    }

    public Item(Question term, List<Answers> definition) {
        this.term = term;
        this.definition = definition;
    }
}
