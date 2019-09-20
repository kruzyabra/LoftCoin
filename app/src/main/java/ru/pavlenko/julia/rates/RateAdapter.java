package ru.pavlenko.julia.rates;

import android.content.Context;
import android.graphics.Outline;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.pavlenko.julia.R;
import ru.pavlenko.julia.data.Coin;
import ru.pavlenko.julia.data.Currencies;
import ru.pavlenko.julia.data.Currency;
import ru.pavlenko.julia.data.Quote;
import ru.pavlenko.julia.util.ImgUrlGetterImpl;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.RateViewHolder> {
    private Context mContext;

    private Currency mCurrency;

    private List<Coin> coins = new ArrayList<>();

    @Inject
    public RateAdapter(Context context, Currency currency) {
        mContext = context;
        mCurrency = currency;
        currency.setCurrentCurrency(Currencies.getDefault());
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    @NonNull
    @Override
    public RateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_rate, parent, false);
        return new RateViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RateViewHolder holder, int position) {
        Coin coin = coins.get(position);

        Currency currency = mCurrency;

        Quote quote = coin.getQuotes().get(currency.getCurrentCurrency());

        Picasso.get().load(new ImgUrlGetterImpl().getUrl(coin.getId())).into(holder.mCoinIcon);

        holder.mCoinName.setText(coin.getSymbol());
        holder.mCoinRate.setText(quote.getPrice());
        holder.mCoinChange.setText(quote.getChange24h());

        holder.mCoinIcon.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0,
                        view.getWidth(), view.getHeight(),
                        view.getWidth() / 2);
            }
        });
        holder.mCoinIcon.setClipToOutline(true);

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.dark_three));
        }
        else {
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.dark_two));
        }
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    static class RateViewHolder extends RecyclerView.ViewHolder {
        private TextView mCoinName;
        private TextView mCoinRate;
        private TextView mCoinChange;

        private ImageView mCoinIcon;

        public RateViewHolder(@NonNull View itemView) {
            super(itemView);

            mCoinName = itemView.findViewById(R.id.coin_name);
            mCoinRate = itemView.findViewById(R.id.coin_rate);
            mCoinChange = itemView.findViewById(R.id.coin_change);

            mCoinIcon = itemView.findViewById(R.id.coin_icon);
        }
    }
}
