package ru.pavlenko.julia.data;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.pavlenko.julia.db.CoinEntity;

class DataConverterImpl implements DataConverter {

    @Inject
    DataConverterImpl() {
    }

    @Override
    public List<CoinEntity> convertCoins(List<Coin> coins, String convert) {
        List<CoinEntity> coinEntities = new ArrayList<>();
        for (Coin coin : coins) {
            Quote quote = coin.getQuotes().get(convert);
            CoinEntity coinEntity = new CoinEntity(coin.getId(), coin.getSymbol(), quote.getPrice(), quote.getChange24h());
            coinEntities.add(coinEntity);
        }

        return coinEntities;
    }
}
