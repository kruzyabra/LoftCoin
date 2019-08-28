package ru.pavlenko.julia.data;

import java.util.List;

public interface CoinMarketCapRepository {

    static CoinMarketCapRepository get() {
        return CoinMarketCapRepositoryImpl.getInstance();
    }

    void getListing(String concert, List<Coin> coins);

}
