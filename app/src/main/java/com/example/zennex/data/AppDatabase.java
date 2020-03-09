package com.example.zennex.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Entity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract Dao getEntityDao();
}
