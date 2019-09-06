package ru.pavlenko.julia.main;

import androidx.collection.SparseArrayCompat;
import androidx.fragment.app.Fragment;

import dagger.Module;
import dagger.Provides;
import ru.pavlenko.julia.R;
import ru.pavlenko.julia.converter.ConverterFragment;
import ru.pavlenko.julia.rates.RateFragment;
import ru.pavlenko.julia.wallets.WalletFragment;

@Module
class MainModule {

    @Provides
    static SparseArrayCompat<Fragment> fragments() {
        final SparseArrayCompat<Fragment> fragments = new SparseArrayCompat<>();
        fragments.put(R.id.wallets, new WalletFragment());
        fragments.put(R.id.rate, new RateFragment());
        fragments.put(R.id.converter, new ConverterFragment());
        return fragments;
    }
}
