package ru.pavlenko.julia.rates;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import ru.pavlenko.julia.data.CoinRepository;

public class RateFactory implements ViewModelProvider.Factory {
    private CoinRepository mRepository;

    @Inject
    RateFactory(CoinRepository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RateViewModel(mRepository);
    }
}
