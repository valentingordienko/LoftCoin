package ru.valentingordienko.loftcoin.screens.rate;

public interface RatePresenter {

    void attachView(RateView view);

    void detachView();

    void getRate();

    void onRefresh();
}