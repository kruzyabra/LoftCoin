package ru.pavlenko.julia;

import android.app.Application;
import android.os.StrictMode;

import ru.pavlenko.julia.log.DebugTree;
import timber.log.Timber;

public class LoftApp extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults();
            Timber.plant(new DebugTree());
        }
        Timber.d("%s", this);

        this.mAppComponent = DaggerAppComponent.factory().create(this);
    }

    public AppComponent getAppComponent() {
        return this.mAppComponent;
    }
}
