package ru.pavlenko.julia;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.pavlenko.julia.db.LoftDb;

@Module
public interface AppModule {

    @Provides
    static Context context(Application app) {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    static LoftDb loftDb(Context context) {
        return Room.databaseBuilder(context, LoftDb.class, "loft_database").build();
    }
}
