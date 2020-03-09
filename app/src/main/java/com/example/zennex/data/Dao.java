package com.example.zennex.data;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@androidx.room.Dao
public interface Dao {

    @Query("SELECT * FROM entity")
    Maybe<List<Entity>> getAll();

    @Query("SELECT * FROM entity WHERE id = :id")
    Maybe<Entity> getById(long id);

    @Insert
    void insert(Entity entity);
    @Update
    void update(Entity entity);
    @Delete
    void delete(Entity entity);
}
