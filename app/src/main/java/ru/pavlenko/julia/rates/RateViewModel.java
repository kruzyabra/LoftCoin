package ru.pavlenko.julia.rates;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import ru.pavlenko.julia.data.CoinRepository;
import ru.pavlenko.julia.data.Currencies;
import ru.pavlenko.julia.db.CoinEntity;
import ru.pavlenko.julia.util.Consumer;
import timber.log.Timber;

public class RateViewModel extends ViewModel {

    private CoinRepository mRepository;

    private final MutableLiveData<List<CoinEntity>> mCoinEntities = new MutableLiveData<>();

    private final MutableLiveData<String> mTitle = new MutableLiveData<>();

    private Currencies mCurrency;

    @Inject
    RateViewModel(CoinRepository repository) {
        Timber.d("THREAD_LOG: " + Thread.currentThread().toString());
        mRepository = repository;
        mCurrency = Currencies.getDefault();

        //mCoinEntities = mRepository.listings();

        refresh();
    }

    void refresh() {
        Timber.d("THREAD_LOG: " + Thread.currentThread().toString());
        // Обновляем mCoins в модели
        mRepository.getListingCoinEntities(mCurrency.getCurrencySymbol(), new Consumer<List<CoinEntity>>() {
            @Override
            public void apply(List<CoinEntity> value) {
                Timber.d("THREAD_LOG: " + Thread.currentThread().toString());
                mCoinEntities.postValue(value);
            }
        });

        // Обновляем данные в БД
        mRepository.refresh(mCurrency.getCurrencySymbol());
    }

    public MutableLiveData<List<CoinEntity>> getCoinEntities() {
        return mCoinEntities;
    }

    void updateCurrency(Currencies currency) {
        mCurrency = currency;
        refresh();
    }
}
