package ru.pavlenko.julia.rates;

import android.graphics.Outline;
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

import ru.pavlenko.julia.R;
import ru.pavlenko.julia.data.Coin;
import ru.pavlenko.julia.data.Quote;
import ru.pavlenko.julia.util.ImgUrlGetterImpl;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.RateViewHolder> {
    private List<Coin> coins = new ArrayList<>();

    public RateAdapter() {
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    @NonNull
    @Override
    public RateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(parent.getContext(), R.layout.item_rate, null);

        return new RateViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RateViewHolder holder, int position) {
        holder.bind(coins.get(position));
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

        public void bind(Coin coin) {
            Quote quote = coin.getQuotes().get("USD");

            Picasso.get().load(new ImgUrlGetterImpl().getUrl(coin.getId())).into(mCoinIcon);

            mCoinName.setText(coin.getSymbol());
            mCoinRate.setText(quote.getPrice());
            mCoinChange.setText(quote.getChange24h());

            mCoinIcon.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0,
                            view.getWidth(), view.getHeight(),
                            view.getWidth() / 2);
                }
            });
            mCoinIcon.setClipToOutline(true);
        }
    }
}
