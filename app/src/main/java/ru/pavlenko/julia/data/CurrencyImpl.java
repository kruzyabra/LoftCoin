package ru.pavlenko.julia.data;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

class CurrencyImpl implements Currency {
    private final static String KEY = "currency_code";

    private SharedPreferences mPref;


    public CurrencyImpl(Context context) {
        mPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public String getCurrentCurrency() {
        return mPref.getString(KEY, Currencies.getDefault().getCurrencySymbol());
    }

    @Override
    public void setCurrentCurrency(Currencies currency) {
        mPref.edit().putString(KEY, currency.getCurrencySymbol()).apply();

    }
}
