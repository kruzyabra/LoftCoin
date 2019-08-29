package ru.pavlenko.julia.data;

import android.widget.Toast;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.pavlenko.julia.BuildConfig;
import ru.pavlenko.julia.util.Consumer;

class CoinMarketCapRepositoryImpl implements CoinMarketCapRepository{
    private static final CoinMarketCapRepositoryImpl ourInstance = new CoinMarketCapRepositoryImpl();

    private CoinMarketCapApi mApi;

    public static CoinMarketCapRepositoryImpl getInstance() {
        return ourInstance;
    }

    private CoinMarketCapRepositoryImpl() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.CMC_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mApi = retrofit.create(CoinMarketCapApi.class);
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
