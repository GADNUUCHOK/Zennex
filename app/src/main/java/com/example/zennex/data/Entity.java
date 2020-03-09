package com.example.zennex.data;

import androidx.room.PrimaryKey;

@androidx.room.Entity
public class Entity {

    @PrimaryKey
    public long id;

    public String name;

    public boolean check;
}
