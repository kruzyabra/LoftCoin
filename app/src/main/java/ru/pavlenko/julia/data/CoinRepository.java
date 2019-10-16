package ru.pavlenko.julia.data;

import androidx.lifecycle.LiveData;

import java.util.List;

import ru.pavlenko.julia.db.CoinEntity;
import ru.pavlenko.julia.util.Consumer;

public interface CoinRepository {

    void getListingCoinEntities(String convert, Consumer<List<CoinEntity>> coins);


    /*
    * Метод, который наблюдает за БД.
    * Возвращает LiveData, в которой
    * хранится результат обращения к Room
    */
    LiveData<List<CoinEntity>> listings();

    /*

     */
    // void refresh(String convert, Consumer<List<Coin>> coins);
    void refresh(String convert);
}
