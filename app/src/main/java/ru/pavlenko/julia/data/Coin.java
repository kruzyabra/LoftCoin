package ru.pavlenko.julia.data;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Coin {
    @SerializedName("id") private int id;

    @SerializedName("symbol") private String symbol;

    @SerializedName("quote") private Map<String, Quote> quotes;

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public Map<String, Quote> getQuotes() {
        return quotes;
    }
}
