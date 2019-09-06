package ru.pavlenko.julia.rates;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.pavlenko.julia.data.CoinMarketCapRepository;

@Module
public class RateModule {

    @Provides
    static Context context(RateFragment rateFragment) {
        return rateFragment.getContext();
    }

}
