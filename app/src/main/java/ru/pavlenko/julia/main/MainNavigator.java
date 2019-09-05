package ru.pavlenko.julia.main;

import androidx.collection.SparseArrayCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import javax.inject.Inject;

import ru.pavlenko.julia.R;

public class MainNavigator {
    private final FragmentActivity mMainActivity;

    private final SparseArrayCompat<Fragment> mFragments;

    @Inject
    MainNavigator(FragmentActivity mainActivity, SparseArrayCompat<Fragment> fragments) {
        this.mMainActivity = mainActivity;
        this.mFragments = fragments;
    }

    public void changeFragment(int selectedId) {
        mMainActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mFragments.get(selectedId))
                .commit();
    }
}
