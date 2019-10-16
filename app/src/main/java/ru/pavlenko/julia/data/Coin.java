package ru.pavlenko.julia.data;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Coin {
    @SerializedName("id") private long id;

    @SerializedName("symbol") private String symbol;

    @SerializedName("quote") private Map<String, Quote> quotes;

    public long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public Map<String, Quote> getQuotes() {
        return quotes;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setQuotes(Map<String, Quote> quotes) {
        this.quotes = quotes;
    }
}
