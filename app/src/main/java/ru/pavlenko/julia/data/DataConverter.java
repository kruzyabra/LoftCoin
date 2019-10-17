package ru.pavlenko.julia.data;

import java.util.List;

import ru.pavlenko.julia.db.CoinEntity;

interface DataConverter {

    List<CoinEntity> convertCoins(List<Coin> coins, String convert);
}
