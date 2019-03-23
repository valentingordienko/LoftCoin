package ru.valentingordienko.loftcoin.data.prefs;

public interface Prefs {
    boolean isFirstLaunch();

    void setFirstLaunch(boolean firstLaunch);
}
