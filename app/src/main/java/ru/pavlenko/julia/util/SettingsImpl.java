package ru.pavlenko.julia.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingsImpl implements Settings {
    private final static String KEY_SHOW_WELCOME_SCREEN = "show_welcome_screen";

    private SharedPreferences mPref;


    public SettingsImpl(Context context) {
        mPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public boolean shouldShowWelcomeScreen() {
        return mPref.getBoolean(KEY_SHOW_WELCOME_SCREEN, true);
    }

    @Override
    public void doNoteShowWelcomeScreenAgain() {
        mPref.edit().putBoolean(KEY_SHOW_WELCOME_SCREEN, false).apply();
    }

}
