package ru.pavlenko.julia.rates;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import ru.pavlenko.julia.AppComponent;
import ru.pavlenko.julia.data.CoinRepository;
import ru.pavlenko.julia.data.Currency;
import ru.pavlenko.julia.main.MainViewModel;

@Module
public interface RateModule {

    @Provides
    static Context context(RateFragment rateFragment) {
        return rateFragment.getContext();
    }

    @Provides
    static AppComponent appComponent(RateFragment rateFragment) {
        return AppComponent.getAppComponent(rateFragment.requireContext());
    }

    @Provides
    static CoinRepository coinRepository(AppComponent appComponent) {
        return appComponent.coinRepository();
    }

    @Provides
    static Currency currency(AppComponent appComponent) {
        return appComponent.currency();
    }

    @Binds
    @IntoMap
    @ClassKey(RateViewModel.class)
    ViewModel rateViewModel(RateViewModel rateViewModel);

    @Binds
    @IntoMap
    @ClassKey(MainViewModel.class)
    ViewModel mainViewModel(MainViewModel mainViewModel);
}
