package ru.pavlenko.julia.data;

import java.util.List;

import ru.pavlenko.julia.util.Consumer;

public interface CoinMarketCapRepository {

    static CoinMarketCapRepository get() {
        return CoinMarketCapRepositoryImpl.getInstance();
    }

    void getListing(String concert, Consumer<List<Coin>> coins);

}
