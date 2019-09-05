package ru.pavlenko.julia.rates;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import ru.pavlenko.julia.R;
import ru.pavlenko.julia.data.CoinMarketCapRepository;
import ru.pavlenko.julia.data.Currencies;
import ru.pavlenko.julia.data.Currency;

public class CurrencyDialog extends DialogFragment {
    static final String TAG = "CurrencyDialog";

    @Inject CurrencyAdapter mAdapter;

    private RateViewModel mRateViewModel;

    @Inject CoinMarketCapRepository mRepository;

    @Inject Currency mCurrency;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerRateComponent.builder()
                .rateFragment((RateFragment) getParentFragment())
                .build()
                .inject(this);

        RateFactory rateFactory = new RateFactory(mRepository);
        mRateViewModel = ViewModelProviders
                .of(getParentFragment().requireActivity(), rateFactory)
                .get(RateViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_currency, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.currency_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClick(new CurrencyAdapter.OnItemClick() {
            @Override
            public void onItemClick(Currencies currency, int position) {
                Currency currencySettings = mCurrency;
                currencySettings.setCurrentCurrency(currency);

                mRateViewModel.updateCurrency(currency);

                dismissAllowingStateLoss();
            }
        });
    }
}
