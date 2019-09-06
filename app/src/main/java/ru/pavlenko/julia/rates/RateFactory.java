package ru.pavlenko.julia.rates;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import ru.pavlenko.julia.data.CoinMarketCapRepository;

public class RateFactory implements ViewModelProvider.Factory {
    private CoinMarketCapRepository mRepository;

    @Inject
    RateFactory(CoinMarketCapRepository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RateViewModel(mRepository);
    }
}
