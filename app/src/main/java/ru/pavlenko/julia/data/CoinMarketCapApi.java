package ru.pavlenko.julia.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoinMarketCapApi {

    String KEY ="CMC_PRO_API_KEY";

    @GET("cryptocurrency/listings/latest")
    Call<Listing> getCoins(@Query(KEY) String coinMarketCapKey, @Query("convert") String covert);
}
