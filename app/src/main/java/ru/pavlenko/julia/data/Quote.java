package ru.pavlenko.julia.data;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Quote {

    @SerializedName("price") private double price;

    @SerializedName("percent_change_24h") private double change24h;

    public String getPrice() {
        return String.valueOf(new BigDecimal(price)
                .setScale(2, RoundingMode.UP)
                .doubleValue());
    }

    public String getChange24h() {
        return String.valueOf(new BigDecimal(change24h)
                .setScale(2, RoundingMode.UP)
                .doubleValue());
    }
}
