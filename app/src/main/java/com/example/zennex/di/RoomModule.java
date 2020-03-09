package com.example.zennex.di;

import android.app.Application;

import androidx.room.Room;

import com.example.zennex.data.AppDatabase;
import com.example.zennex.data.Dao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private AppDatabase mAppDatabase;

    public RoomModule(Application application) {
        mAppDatabase = Room.databaseBuilder(application, AppDatabase.class, "database").allowMainThreadQueries().build();
    }

    @Singleton
    @Provides
    public AppDatabase providesRoomDatabase() {
        return mAppDatabase;
    }

    @Singleton
    @Provides
    Dao providesEntity(AppDatabase appDatabase) {
        return appDatabase.getEntityDao();
    }
}
