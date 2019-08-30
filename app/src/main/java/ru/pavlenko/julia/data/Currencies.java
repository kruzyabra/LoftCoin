package ru.pavlenko.julia.data;

import ru.pavlenko.julia.R;

public enum Currencies {
    USD(R.string.usd_name, "USD", R.string.usd_sign),
    EUR(R.string.eur_name, "EUR", R.string.eur_sign),
    RUB(R.string.rub_name, "RUB", R.string.rub_sign);

    private int currencyName;
    private String currencySymbol;
    private int currencySign;

    Currencies(int currencyName, String currencySymbol, int currencySign) {
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
        this.currencySign = currencySign;
    }

    public int getCurrencyName() {
        return currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public int getCurrencySign() {
        return currencySign;
    }

    public static Currencies getDefault() {
        return Currencies.USD;
    }
}
