package com.yory3r.e_learning.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.yory3r.e_learning.dao.RegisterDao;
import com.yory3r.e_learning.model.Register;

@Database(entities = {Register.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract RegisterDao registerDao();
}
