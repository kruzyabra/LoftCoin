package ru.pavlenko.julia.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.pavlenko.julia.R;
import ru.pavlenko.julia.main.MainActivity;
import ru.pavlenko.julia.util.Settings;
import ru.pavlenko.julia.util.SettingsImpl;
import ru.pavlenko.julia.welcome.WelcomeActivity;

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
