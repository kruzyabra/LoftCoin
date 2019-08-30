package ru.pavlenko.julia.data;

import com.google.gson.annotations.SerializedName;

public class Quote {

    @SerializedName("price") private double price;

    @SerializedName("percent_change_24h") private double change24h;

    public String getPrice() {
        return String.valueOf(price);
    }

    public String getChange24h() {
        return String.valueOf(change24h);
    }
}
