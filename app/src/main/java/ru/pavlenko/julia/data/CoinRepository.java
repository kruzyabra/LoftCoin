package ru.pavlenko.julia.data;


import java.util.List;

import ru.pavlenko.julia.db.CoinEntity;
import ru.pavlenko.julia.util.Consumer;

public interface CoinRepository {

    void getListing(String convert, Consumer<List<CoinEntity>> coins);


    List<CoinEntity> listings();

    void refresh(String convert);
}
