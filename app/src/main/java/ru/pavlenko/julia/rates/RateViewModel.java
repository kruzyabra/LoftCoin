package ru.pavlenko.julia.rates;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.pavlenko.julia.data.Coin;

public class RateViewModel extends ViewModel {
    private final MutableLiveData<List<Coin>> mCoins = new MutableLiveData<>();

    private final MutableLiveData<String> mTitle = new MutableLiveData<>();

    public void setmCoins(List<Coin> coins) {
        mCoins.postValue(coins);
    }

    public void setTitle(String title) {
        mTitle.postValue(title);
    }

    public MutableLiveData<List<Coin>> getmCoins() {
        return mCoins;
    }
}
