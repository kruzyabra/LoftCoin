package ru.pavlenko.julia;

import android.app.Application;

import ru.pavlenko.julia.log.DebugTree;
import timber.log.Timber;

public class LoftApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        }
        Timber.d("%s", this);
    }
}
