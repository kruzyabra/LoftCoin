package ru.pavlenko.julia;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public interface AppModule {

    @Provides
    static Context context(Application app) {
        return app.getApplicationContext();
    }
}
