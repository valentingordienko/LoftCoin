package ru.valentingordienko.loftcoin.data.prefs;

import ru.valentingordienko.loftcoin.utils.Fiat;

public interface Prefs {
    boolean isFirstLaunch();

    void setFirstLaunch(boolean firstLaunch);

    Fiat getFiatCurrency();

    void setFiatCurrency(Fiat fiat);
}
