package ru.pavlenko.julia.rates;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import ru.pavlenko.julia.data.CoinRepository;
import ru.pavlenko.julia.data.Currencies;
import ru.pavlenko.julia.db.CoinEntity;

public class RateViewModel extends ViewModel {

    private CoinRepository mRepository;

    private final MutableLiveData<List<CoinEntity>> mCoinEntities = new MutableLiveData<>();

    private Currencies mCurrency;

    @Inject
    RateViewModel(CoinRepository repository) {
        mRepository = repository;
        mCurrency = Currencies.getDefault();

        refresh();
    }

    void refresh() {
        mRepository.refresh(mCurrency.getCurrencySymbol());
        mCoinEntities.postValue(mRepository.listings());
    }

    public MutableLiveData<List<CoinEntity>> getCoinEntities() {
        return mCoinEntities;
    }

    void updateCurrency(Currencies currency) {
        mCurrency = currency;
        refresh();
    }
}
