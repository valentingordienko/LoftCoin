package ru.valentingordienko.loftcoin.data.db;

import java.util.List;

import ru.valentingordienko.loftcoin.data.db.model.CoinEntity;

public interface Database {

    void saveCoins(List<CoinEntity> coins);

    List<CoinEntity> getCoins();
}
