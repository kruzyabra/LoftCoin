package ru.pavlenko.julia.data;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.pavlenko.julia.BuildConfig;
import ru.pavlenko.julia.db.CoinEntity;
import ru.pavlenko.julia.db.LoftDb;
import ru.pavlenko.julia.util.Consumer;
import timber.log.Timber;

class CoinRepositoryImpl implements CoinRepository {

    private CoinMarketCapApi mApi;

    private LoftDb mLoftDb;

    @Inject
    CoinRepositoryImpl(CoinMarketCapApi api,
                       LoftDb loftDb) {
        mApi = api;
        mLoftDb = loftDb;
    }

    @Override
    public void getListingCoinEntities(String convert, Consumer<List<CoinEntity>> coins) {

        Timber.d("THREAD_LOG: " + Thread.currentThread().toString());
        mApi.getCoins(BuildConfig.CMC_API_KEY, convert)
                .enqueue(new Callback<Listing>() {
                    @Override
                    public void onResponse(Call<Listing> call, Response<Listing> response) {
                        Timber.d("THREAD_LOG: " + Thread.currentThread().toString());
                        Listing listing = response.body();

                        List<Coin> listCoins = listing.getData();
                        List<CoinEntity> coinEntities = new ArrayList<>();
                        for (Coin coin : listCoins) {
                            Quote quote = coin.getQuotes().get(convert);
                            CoinEntity coinEntity = new CoinEntity(coin.getId(), coin.getSymbol(), quote.getPrice(), quote.getChange24h());
                            coinEntities.add(coinEntity);
                        }

                        coins.apply(coinEntities);
                    }

                    @Override
                    public void onFailure(Call<Listing> call, Throwable t) {
                    }
                });
    }

    @Override
    public LiveData<List<CoinEntity>> listings() {
        Timber.d("THREAD_LOG: " + Thread.currentThread().toString());
        return mLoftDb.coins().fetchAll();
    }

    @Override
    public void refresh(String convert) {
        Timber.d("THREAD_LOG: " + Thread.currentThread().toString());

        getListingCoinEntities(convert, new Consumer<List<CoinEntity>>() {
            @Override
            public void apply(List<CoinEntity> coins) {
                Timber.d("THREAD_LOG: " + Thread.currentThread().toString());

                mLoftDb.coins().insertAll(coins);
            }
        });

    }
}
