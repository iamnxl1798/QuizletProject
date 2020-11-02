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

}
