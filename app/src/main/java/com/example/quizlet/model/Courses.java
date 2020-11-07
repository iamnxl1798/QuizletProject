package com.example.quizlet.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Courses {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo
    private String name;

    @ColumnInfo
    private long createDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public Courses(String name, long createDate) {
        this.name = name;
        this.createDate = createDate;
    }
}
