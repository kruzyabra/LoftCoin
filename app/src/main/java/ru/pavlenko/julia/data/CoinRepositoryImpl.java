package ru.pavlenko.julia.data;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.pavlenko.julia.BuildConfig;
import ru.pavlenko.julia.util.Consumer;

class CoinRepositoryImpl implements CoinRepository {

    private CoinMarketCapApi mApi;

    @Inject
    CoinRepositoryImpl(CoinMarketCapApi api) {
        mApi = api;
    }

    @Override
    public void getListing(String convert, Consumer<List<Coin>> coins) {
        mApi.getCoins(BuildConfig.CMC_API_KEY, convert)
                .enqueue(new Callback<Listing>() {
                    @Override
                    public void onResponse(Call<Listing> call, Response<Listing> response) {
                        Listing listing = response.body();

                        coins.apply(listing.getData());
                    }

                    @Override
                    public void onFailure(Call<Listing> call, Throwable t) {
                    }
                });
    }
}
