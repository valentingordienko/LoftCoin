package ru.valentingordienko.loftcoin.screens.rate;

import java.util.List;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.valentingordienko.loftcoin.data.api.Api;
import ru.valentingordienko.loftcoin.data.api.model.Coin;
import ru.valentingordienko.loftcoin.data.api.model.RateResponse;
import ru.valentingordienko.loftcoin.data.db.Database;
import ru.valentingordienko.loftcoin.data.db.model.CoinEntity;
import ru.valentingordienko.loftcoin.data.db.model.CoinEntityMapper;
import ru.valentingordienko.loftcoin.data.prefs.Prefs;
import timber.log.Timber;

public class RatePresenterImpl implements RatePresenter {

    private Prefs prefs;
    private Api api;
    private Database database;
    private CoinEntityMapper coinEntityMapper;

    @Nullable
    private RateView view;

    public RatePresenterImpl(Prefs prefs, Api api, Database database, CoinEntityMapper coinEntityMapper) {
        this.prefs = prefs;
        this.api = api;
        this.database = database;
        this.coinEntityMapper = coinEntityMapper;
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
        loadRate();
    }

    @Override
    public void getRate() {
        List<CoinEntity> coins = database.getCoins();

        if (coins != null) {
            view.setCoins(coins);
        }
    }

    private void loadRate() {
        Call<RateResponse> call = api.rates(Api.CONVERT);

        call.enqueue(new Callback<RateResponse>() {
            @Override
            public void onResponse(Call<RateResponse> call, Response<RateResponse> response) {

                /*RateResponse body = response.body();

                 */
                if (view != null) {
                    if (response.body() != null) {
                        List<Coin> coins = response.body().data;
                        List<CoinEntity> coinEntities = coinEntityMapper.map(coins);
                        database.saveCoins(coinEntities);
                        view.setCoins(coinEntities);
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
