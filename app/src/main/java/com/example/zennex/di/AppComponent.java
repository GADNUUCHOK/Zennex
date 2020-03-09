package com.example.zennex.di;

import android.app.Application;

import com.example.zennex.data.AppDatabase;
import com.example.zennex.data.Dao;
import com.example.zennex.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(dependencies = {}, modules = {AppModule.class, RoomModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    Application application();

    AppDatabase appDatabase();

    Dao dao();
}
