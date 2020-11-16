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

    @ColumnInfo
    private long creatorID;


    public Courses(String name, long createDate) {
        this.name = name;
        this.createDate = createDate;
    }

    public Courses(String name, long createDate, long creatorID) {
        this.name = name;
        this.createDate = createDate;
        this.creatorID=creatorID;
    }

    public Courses() {
    }

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

    public long getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(long creatorID) {
        this.creatorID = creatorID;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
