package ru.valentingordienko.loftcoin.screens.rate;

import ru.valentingordienko.loftcoin.utils.Fiat;

public interface RatePresenter {

    void attachView(RateView view);

    void detachView();

    void getRate();

    void onRefresh();

    void onMenuItemCurrencyClick();

    void onFiatCurrencySelected(Fiat currency);
}