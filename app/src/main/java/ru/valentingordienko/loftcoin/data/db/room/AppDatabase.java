package ru.valentingordienko.loftcoin.data.db.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import ru.valentingordienko.loftcoin.data.db.model.CoinEntity;

@Database(entities = {CoinEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CoinDao coinDao();
}
