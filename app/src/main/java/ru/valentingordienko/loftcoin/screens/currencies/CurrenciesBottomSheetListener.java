package ru.valentingordienko.loftcoin.screens.currencies;

import ru.valentingordienko.loftcoin.data.db.model.CoinEntity;

public interface CurrenciesBottomSheetListener {
    void onCurrencySelected(CoinEntity coin);
}
