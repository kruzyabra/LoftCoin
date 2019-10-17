package ru.pavlenko.julia.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CoinEntity.class}, version = 1)
public abstract class LoftDb extends RoomDatabase {
    public abstract CoinDao coins();
}
