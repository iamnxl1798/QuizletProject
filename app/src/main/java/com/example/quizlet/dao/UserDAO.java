package com.example.quizlet.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quizlet.model.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    public void insert(User user);

    @Delete
    public void delete(User user);

    @Query("SELECT * FROM User")
    public List<User> getUsers();
}
