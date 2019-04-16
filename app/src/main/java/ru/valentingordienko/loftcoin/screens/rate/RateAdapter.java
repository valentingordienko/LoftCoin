package ru.valentingordienko.loftcoin.screens.rate;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.valentingordienko.loftcoin.R;
import ru.valentingordienko.loftcoin.data.api.model.Coin;
import ru.valentingordienko.loftcoin.data.api.model.Quote;
import ru.valentingordienko.loftcoin.data.db.model.CoinEntity;
import ru.valentingordienko.loftcoin.data.db.model.QuoteEntity;
import ru.valentingordienko.loftcoin.data.prefs.Prefs;
import ru.valentingordienko.loftcoin.utils.CurrencyFormatter;
import ru.valentingordienko.loftcoin.utils.Fiat;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.RateViewHolder> {

    private Prefs prefs;

    public List<CoinEntity> items = Collections.emptyList();

    public RateAdapter(Prefs prefs) {
        this.prefs = prefs;
    }

    public void setItems(List<CoinEntity> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RateAdapter.RateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rate, parent, false);
        return new RateViewHolder(view, prefs);
    }

    @Override
    public void onBindViewHolder(@NonNull RateAdapter.RateViewHolder holder, int position) {
        holder.bind(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private static int[] colors = {
            0xFFF5FF30,
            0xFFFFFFFF,
            0xFF2ABDF5,
            0xFFFF7416,
            0xFFFF7416,
            0xFF534FFF,
    };

    static class RateViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.symbol_text)
        TextView symbolText;

        @BindView(R.id.currency_name)
        TextView name;

        @BindView(R.id.price)
        TextView price;

        @BindView(R.id.percent_change)
        TextView percentChange;

        private Context context;
        private Prefs prefs;
        private Random random = new Random();
        private CurrencyFormatter currencyFormatter = new CurrencyFormatter();

        public RateViewHolder(@NonNull View itemView, Prefs prefs){
            super(itemView);

            this.context = itemView.getContext();
            this.prefs = prefs;

            ButterKnife.bind(this, itemView);
        }

        public void bind(CoinEntity coin, int position) {

            bindBackground(position);
            bindName(coin);
            bindIcon(coin);
            bindPercentage(coin);
            bindPrice(coin);
        }

        private void bindName(CoinEntity coin) {
            name.setText(coin.symbol);
        }

        private void bindBackground(int position) {
            if (position % 2 == 0) {
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.rate_item_background_even));
            } else {
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.rate_item_background_odd));
            }
        }

        private void bindPercentage(CoinEntity coin) {

            Fiat fiat = prefs.getFiatCurrency();
            QuoteEntity quote = coin.getQuote(fiat);

            double percentChangeValue = quote.percentChange24h;

            percentChange.setText(context.getString(R.string.rate_item_percent_change, percentChangeValue));

            if (percentChangeValue >= 0) {
                percentChange.setTextColor(context.getResources().getColor(R.color.percent_change_up));
            } else {
                percentChange.setTextColor(context.getResources().getColor(R.color.percent_change_down));
            }
        }

        private void bindPrice(CoinEntity coin) {
            Fiat fiat = prefs.getFiatCurrency();
            QuoteEntity quote = coin.getQuote(fiat);
            String value = currencyFormatter.format(quote.price, false);

            price.setText(context.getString(R.string.currency_amount, value, fiat.symbol));
        }

        private void bindIcon(CoinEntity coin) {
            symbolText.setVisibility(View.VISIBLE);

            Drawable background = symbolText.getBackground();
            Drawable wrapped = DrawableCompat.wrap(background);
            DrawableCompat.setTint(wrapped, colors[random.nextInt(colors.length)]);

            symbolText.setText(String.valueOf(coin.symbol.charAt(0)));

        }
    }
}
