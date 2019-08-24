package ru.pavlenko.julia.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.collection.SparseArrayCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.pavlenko.julia.R;
import ru.pavlenko.julia.fragment.ConverterFragment;
import ru.pavlenko.julia.fragment.RateFragment;
import ru.pavlenko.julia.fragment.WalletFragment;
import ru.pavlenko.julia.model.MainViewModel;

public class MainActivity extends AppCompatActivity {

    public static final SparseArrayCompat<Fragment> FRAGMENTS;

    static {
        FRAGMENTS = new SparseArrayCompat<>();
        FRAGMENTS.put(R.id.wallets, new WalletFragment());
        FRAGMENTS.put(R.id.rate, new RateFragment());
        FRAGMENTS.put(R.id.converter, new ConverterFragment());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, FRAGMENTS.get(selectedId))
                        .commit();

            }
        });



    }
}
