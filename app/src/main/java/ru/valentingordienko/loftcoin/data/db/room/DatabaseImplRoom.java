package ru.valentingordienko.loftcoin.data.db.room;

import java.util.List;

import ru.valentingordienko.loftcoin.data.db.Database;
import ru.valentingordienko.loftcoin.data.db.model.CoinEntity;

public class DatabaseImplRoom implements Database {

    private AppDatabase appDatabase;

    public DatabaseImplRoom(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public void saveCoins(List<CoinEntity> coins) {
        appDatabase.coinDao().saveCoins(coins);
    }

    @Override
    public List<CoinEntity> getCoins() {
        return appDatabase.coinDao().getCoins();
    }
}
