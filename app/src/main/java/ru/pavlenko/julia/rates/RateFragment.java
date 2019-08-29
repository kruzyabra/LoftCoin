package ru.pavlenko.julia.rates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.pavlenko.julia.R;
import ru.pavlenko.julia.data.Coin;
import ru.pavlenko.julia.main.MainViewModel;

public class RateFragment extends Fragment {

    private MainViewModel mMainModelView;

    private RecyclerView mRecyclerView;

    private RateAdapter mRateAdapter;

    private RateViewModel mRateViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainModelView = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        mRateViewModel = ViewModelProviders.of(requireActivity()).get(RateViewModel.class);
        mRateAdapter = new RateAdapter();
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
}
