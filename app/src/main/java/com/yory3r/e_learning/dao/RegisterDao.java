package com.yory3r.e_learning.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.yory3r.e_learning.model.Register;

import java.util.List;

@Dao
public interface RegisterDao
{
    @Query("SELECT * FROM register")
    List<Register> getAll();

    @Insert
    void insertRegister(Register register);

    @Update
    void updateRegister(Register register);

    @Delete
    void deleteRegister(Register register);
}
