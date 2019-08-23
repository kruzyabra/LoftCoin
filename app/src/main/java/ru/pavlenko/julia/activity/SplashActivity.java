package ru.pavlenko.julia.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.pavlenko.julia.R;
import ru.pavlenko.julia.Settings;
import ru.pavlenko.julia.SettingsImpl;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Settings settings = new SettingsImpl(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (settings.shouldShowWelcomeScreen()) {
                    Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        }, 2000);
    }
}
