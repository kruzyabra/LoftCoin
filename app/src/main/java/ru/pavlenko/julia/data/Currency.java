package ru.pavlenko.julia.data;

import android.content.Context;

public interface Currency {

    static Currency get(Context context) {
        return new CurrencyImpl(context);
    }

    String getCurrentCurrency();

    void setCurrentCurrency(Currencies currency);
}
