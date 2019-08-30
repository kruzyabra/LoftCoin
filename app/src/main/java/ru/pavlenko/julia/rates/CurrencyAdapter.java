package ru.pavlenko.julia.rates;


import android.graphics.Outline;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

import ru.pavlenko.julia.R;
import ru.pavlenko.julia.data.Currencies;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {
    private OnItemClick mListener;

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_currency, null);
        return new CurrencyViewHolder(view);
    }

    void setOnItemClick(OnItemClick listener) {
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        holder.bind(Currencies.values()[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(Currencies.values()[position], position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Currencies.values().length;
    }

    static class CurrencyViewHolder extends RecyclerView.ViewHolder {
        private TextView mCurrencySign;
        private TextView mCurrencyName;

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            mCurrencySign = itemView.findViewById(R.id.currency_sign);
            mCurrencyName = itemView.findViewById(R.id.currency_name);
        }

        public void bind(Currencies currency) {
            mCurrencySign.setText(currency.getCurrencySign());
            mCurrencyName.setText(String.format(Locale.US,
                    "%s | %s",
                    this.itemView.getContext().getString(currency.getCurrencyName()),
                    currency.getCurrencySymbol()));

            mCurrencySign.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0,
                            view.getWidth(), view.getHeight(),
                            view.getWidth() / 2);
                }
            });
            mCurrencySign.setClipToOutline(true);
        }
    }

    interface OnItemClick {
        void onItemClick(Currencies currency, int position);
    }
}
