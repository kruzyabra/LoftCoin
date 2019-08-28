package ru.pavlenko.julia.data;

import com.google.gson.annotations.SerializedName;

class Quote {

    @SerializedName("price") private double price;

    @SerializedName("percent_change_24h") private double change24h;

}
