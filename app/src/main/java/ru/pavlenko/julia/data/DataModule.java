package ru.pavlenko.julia.data;

import android.content.Context;

import java.util.concurrent.Executors;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.pavlenko.julia.BuildConfig;

@Module
public interface DataModule {

    @Provides
    static HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.HEADERS);
        return httpLoggingInterceptor;
    }

    @Provides
    static OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    static CoinMarketCapApi api(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.CMC_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .callbackExecutor(Executors.newFixedThreadPool(4))
                .client(okHttpClient)
                .build();

        return retrofit.create(CoinMarketCapApi.class);
    }

    @Binds
    CoinRepository repository(CoinRepositoryImpl coinRepository);

    @Binds
    Currency currency(CurrencyImpl currency);
}
