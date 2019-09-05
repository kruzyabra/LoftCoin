package ru.pavlenko.julia.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.collection.SparseArrayCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import ru.pavlenko.julia.R;
import ru.pavlenko.julia.converter.ConverterFragment;
import ru.pavlenko.julia.rates.RateFragment;
import ru.pavlenko.julia.wallets.WalletFragment;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainNavigator mMainNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainComponent.builder()
                .fragmentActivity(this)
                .build()
                .inject(this);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        BottomNavigationView bottomNavView = findViewById(R.id.bottom_nav_view);
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                viewModel.setSelectedId(menuItem.getItemId());
                return true;
            }
        });

        viewModel.getTitle().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String title) {
                toolbar.setTitle(title);
            }
        });

        viewModel.getSelectedId().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer selectedId) {
                mMainNavigator.changeFragment(selectedId);
            }
        });



    }
}
