package ru.pavlenko.julia.rates;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.pavlenko.julia.data.Coin;
import ru.pavlenko.julia.data.CoinRepository;
import ru.pavlenko.julia.data.Currencies;
import ru.pavlenko.julia.util.Consumer;

public class RateViewModel extends ViewModel {

    private CoinRepository mRepository;

    private final MutableLiveData<List<Coin>> mCoins = new MutableLiveData<>();

    private final MutableLiveData<String> mTitle = new MutableLiveData<>();

    private Currencies mCurrency;

    @Inject
    RateViewModel(CoinRepository repository) {
        mRepository = repository;
        mCurrency = Currencies.getDefault();
        refresh();
    }

    void refresh() {
        mRepository.getListing(mCurrency.getCurrencySymbol(), new Consumer<List<Coin>>() {
            @Override
            public void apply(List<Coin> value) {
                mCoins.postValue(value);
            }
        });
    }

    public MutableLiveData<List<Coin>> getCoins() {
        return mCoins;
    }

    void updateCurrency(Currencies currency) {
        mCurrency = currency;
        refresh();
    }
}
