package ru.pavlenko.julia.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Listing {
    @SerializedName("data") private List<Coin> data;

    public List<Coin> getData() {
        return data;
    }
}
