package ru.valentingordienko.loftcoin.screens.rate;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.valentingordienko.loftcoin.data.api.Api;
import ru.valentingordienko.loftcoin.data.api.model.RateResponse;
import ru.valentingordienko.loftcoin.data.prefs.Prefs;
import timber.log.Timber;

public class RatePresenterImpl implements RatePresenter {

    private Prefs prefs;
    private Api api;

    @Nullable
    private RateView view;

    public RatePresenterImpl(Prefs prefs, Api api) {
        this.prefs = prefs;
        this.api = api;
    }

    @Override
    public void attachView(RateView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void onRefresh() {
        getRate();
    }

    @Override
    public void getRate() {

        Call<RateResponse> call = api.rates(Api.CONVERT);

        call.enqueue(new Callback<RateResponse>() {
            @Override
            public void onResponse(Call<RateResponse> call, Response<RateResponse> response) {

                RateResponse body = response.body();

                if (view != null) {

                    if (body != null) {
                        view.setCoins(body.data);
                    }

                    view.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<RateResponse> call, Throwable t) {
                Timber.e(t);
                if (view != null) {
                    view.setRefreshing(false);
                }
            }
        });
    }
}
