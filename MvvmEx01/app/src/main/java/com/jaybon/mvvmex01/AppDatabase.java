package com.jaybon.mvvmex01;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 3) // 엔티티와 엔티티변경 버전
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserRepository userRepository();
}
