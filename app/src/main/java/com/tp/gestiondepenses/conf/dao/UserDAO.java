package com.tp.gestiondepenses.conf.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.tp.gestiondepenses.conf.entity.User;

@Dao
public interface UserDAO {

    @Insert
    void insert(
            User user);

    @Query("SELECT * FROM user WHERE username = :username AND password = :password LIMIT 1")
    User login(String username, String password);

    @Query("SELECT * FROM user WHERE username = :username LIMIT 1")
    User getUserByUsername(String username);


}