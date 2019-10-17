package ru.pavlenko.julia.data;

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

    private DataConverter mDataConverter;

    @Inject
    CoinRepositoryImpl(CoinMarketCapApi api,
                       LoftDb loftDb,
                       DataConverter dataConverter) {
        mApi = api;
        mLoftDb = loftDb;
        mDataConverter = dataConverter;
    }

    @Override
    public void getListing(String convert, Consumer<List<CoinEntity>> coins) {

        mApi.getCoins(BuildConfig.CMC_API_KEY, convert)
                .enqueue(new Callback<Listing>() {
                    @Override
                    public void onResponse(Call<Listing> call, Response<Listing> response) {
                        Listing listing = response.body();

                        List<CoinEntity> coinEntities =  mDataConverter.convertCoins(listing.getData(), convert);

                        coins.apply(coinEntities);
                    }

                    @Override
                    public void onFailure(Call<Listing> call, Throwable t) {
                    }
                });
    }

    @Override
    public List<CoinEntity> listings() {
        return mLoftDb.coins().fetchAll();
    }

    @Override
    public void refresh(String convert) {
        getListing(convert, new Consumer<List<CoinEntity>>() {
            @Override
            public void apply(List<CoinEntity> coins) {
                mLoftDb.coins().insertAll(coins);
            }
        });

    }
}
