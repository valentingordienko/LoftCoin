package ru.valentingordienko.loftcoin.data.db.model;

import java.util.List;

import ru.valentingordienko.loftcoin.data.api.model.Coin;

public interface CoinEntityMapper {
    List<CoinEntity> map(List<Coin> coins);
}
