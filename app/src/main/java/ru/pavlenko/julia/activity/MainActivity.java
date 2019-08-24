package ru.pavlenko.julia.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.pavlenko.julia.R;
import ru.pavlenko.julia.model.MainViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final MainViewModel viewModel = new MainViewModel();

        BottomNavigationView bottomNavView = findViewById(R.id.bottom_nav_view);
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                viewModel.getSelectedId();
                return true;
            }
        });

        viewModel.getTitle().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String title) {
                toolbar.setTitle(title);
            }
        });


    }
}
