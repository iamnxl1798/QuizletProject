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

    @Query("SELECT * FROM User where username =:userName and  password =:pass")
    public User checkAccountUser(String userName, String pass);

    @Query("SELECT * FROM User where username =:userName")
    public User checkAccountExist(String userName);

    @Query("SELECT * FROM User where id =:id ")
    public User getUser(long id);

    @Query("UPDATE User SET password = :newPass WHERE ID = :idUser;")
    public void updateUserBypass(String newPass, long idUser);

    @Query("UPDATE User SET email = :newEmail WHERE ID = :idUser;")
    public void updateUserByemail(String newEmail, long idUser);

    @Query("SELECT * FROM User where email =:email")
    public User checkEmailExist(String email);
}
