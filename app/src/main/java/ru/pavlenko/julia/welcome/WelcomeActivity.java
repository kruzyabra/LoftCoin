package ru.pavlenko.julia.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import me.relex.circleindicator.CircleIndicator;
import ru.pavlenko.julia.R;
import ru.pavlenko.julia.main.MainActivity;
import ru.pavlenko.julia.util.Settings;
import ru.pavlenko.julia.util.SettingsImpl;

public class WelcomeActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getLayoutInflater()));

        CircleIndicator circleIndicator = findViewById(R.id.circle_indicator);
        circleIndicator.setViewPager(viewPager);

        Button welcomeButton = findViewById(R.id.welcome_button);
        welcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Settings settings = new SettingsImpl(view.getContext());
                settings.doNoteShowWelcomeScreenAgain();

                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


    }
}
