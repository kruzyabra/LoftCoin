package ru.pavlenko.julia.data;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.pavlenko.julia.BuildConfig;

@Module
public class DataModule {

    @Provides
    static HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
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
                .client(okHttpClient)
                .build();

        return retrofit.create(CoinMarketCapApi.class);
    }

    @Provides
    static CoinMarketCapRepository repository(CoinMarketCapApi api) {
        return new CoinMarketCapRepositoryImpl(api);
    }

    @Provides
    static Currency currency(Context context) {
        return new CurrencyImpl(context);
    }
}
