package com.example.quizlet.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class JoinedCourses {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ForeignKey(entity = User.class,
            parentColumns = {"id"},
            childColumns = {"userId"},
            onDelete = ForeignKey.CASCADE
    )
    private long userId;

    @ForeignKey(entity = Courses.class,
            parentColumns = {"id"},
            childColumns = {"courseId"},
            onDelete = ForeignKey.CASCADE
    )
    private long courseId;

    @ColumnInfo
    private long joinDate;

    public JoinedCourses() {
    }

    public JoinedCourses(long userId, long courseId, long joinDate) {
        this.userId = userId;
        this.courseId = courseId;
        this.joinDate = joinDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(long joinDate) {
        this.joinDate = joinDate;
    }
}
