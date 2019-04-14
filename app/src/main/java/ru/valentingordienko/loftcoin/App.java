package ru.valentingordienko.loftcoin;

import android.app.Application;

import ru.valentingordienko.loftcoin.data.api.Api;
import ru.valentingordienko.loftcoin.data.api.ApiInitializer;
import ru.valentingordienko.loftcoin.data.db.Database;
import ru.valentingordienko.loftcoin.data.db.DatabaseInitializer;
import ru.valentingordienko.loftcoin.data.prefs.Prefs;
import ru.valentingordienko.loftcoin.data.prefs.PrefsImpl;

import timber.log.Timber;

public class App extends Application {

    private Prefs prefs;
    private Api api;
    private Database database;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        prefs = new PrefsImpl(this);
        api = new ApiInitializer().init();
        database = new DatabaseInitializer().init(this);
    }

    public Prefs getPrefs() {
        return prefs;
    }

    public Api getApi() {
        return api;
    }

    public Database getDatabase() {
        return database;
    }
}
