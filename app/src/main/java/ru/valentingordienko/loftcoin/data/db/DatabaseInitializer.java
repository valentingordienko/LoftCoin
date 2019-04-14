package ru.valentingordienko.loftcoin.data.db;

import android.content.Context;

import androidx.room.Room;
import ru.valentingordienko.loftcoin.data.db.room.AppDatabase;
import ru.valentingordienko.loftcoin.data.db.room.DatabaseImplRoom;

public class DatabaseInitializer {

    public Database init(Context context) {
        AppDatabase appDatabase = Room.databaseBuilder(context, AppDatabase.class, "loftcoin.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        return new DatabaseImplRoom(appDatabase);
    }
}
