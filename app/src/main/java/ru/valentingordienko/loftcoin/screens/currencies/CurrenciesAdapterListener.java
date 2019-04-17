package ru.valentingordienko.loftcoin.screens.currencies;

import ru.valentingordienko.loftcoin.data.db.model.CoinEntity;

public interface CurrenciesAdapterListener {
    void onCurrencyClick(CoinEntity coin);
}
