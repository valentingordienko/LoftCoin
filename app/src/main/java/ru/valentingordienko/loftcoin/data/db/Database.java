package ru.valentingordienko.loftcoin.data.db;

import java.util.List;

import io.reactivex.Flowable;
import ru.valentingordienko.loftcoin.data.db.model.CoinEntity;

public interface Database {

    void saveCoins(List<CoinEntity> coins);

    Flowable<List<CoinEntity>> getCoins();
}
