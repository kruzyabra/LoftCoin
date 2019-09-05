package ru.pavlenko.julia.rates;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class RateModule {

    @Provides
    static Context context(RateFragment rateFragment) {
        return rateFragment.getContext();
    }
}
