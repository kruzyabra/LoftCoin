package ru.pavlenko.julia;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import ru.pavlenko.julia.data.CoinRepository;
import ru.pavlenko.julia.data.Currency;
import ru.pavlenko.julia.data.DataModule;

@Singleton
@Component(modules = {
        AppModule.class,
        DataModule.class
})
public interface AppComponent {

    static AppComponent getAppComponent(Context context) {
        final Context appContext = context.getApplicationContext();
        if (appContext instanceof LoftApp) {
            return ((LoftApp) appContext).getAppComponent();
        }
        throw new IllegalArgumentException("ApplicationContext should be an instance of LoftApp");
    }

    CoinRepository coinRepository();

    Currency currency();

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Application app);
    }
}
