package ru.pavlenko.julia.rates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import ru.pavlenko.julia.R;
import ru.pavlenko.julia.data.Coin;
import ru.pavlenko.julia.main.MainViewModel;

public class RateFragment extends Fragment {

    private MainViewModel mMainModelView;

    private RecyclerView mRecyclerView;

    @Inject RateAdapter mRateAdapter;

    private RateViewModel mRateViewModel;

    @Inject ViewModelProvider.Factory mVmFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerRateComponent.builder()
                .rateFragment(this)
                .build()
                .inject(this);

        mMainModelView = ViewModelProviders
                .of(requireActivity(), mVmFactory)
                .get(MainViewModel.class);

        mRateViewModel = ViewModelProviders
                .of(this, mVmFactory)
                .get(RateViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMainModelView.setTitle(getResources().getString(R.string.rate));

        mRecyclerView = view.findViewById(R.id.list_of_coins);
        mRecyclerView.setAdapter(mRateAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRateViewModel.getCoins().observe(this, new Observer<List<Coin>>() {
            @Override
            public void onChanged(List<Coin> coins) {
                mRateAdapter.setCoins(coins);
                mRateAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.rate_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (R.id.currency == item.getItemId()) {
            CurrencyDialog currencyDialog = new CurrencyDialog();
            currencyDialog.show(getChildFragmentManager(), CurrencyDialog.TAG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
