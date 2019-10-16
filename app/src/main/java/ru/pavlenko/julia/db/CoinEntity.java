package ru.pavlenko.julia.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "coins")
public class CoinEntity {

    @PrimaryKey
    public long id;

    public String symbol;

    public String price;

    public String change24h;

    public long getId() {
        return id;
    }

    public CoinEntity(long id, String symbol, String price, String change24h) {
        this.id = id;
        this.symbol = symbol;
        this.price = price;
        this.change24h = change24h;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChange24h() {
        return change24h;
    }

    public void setChange24h(String change24h) {
        this.change24h = change24h;
    }
}
