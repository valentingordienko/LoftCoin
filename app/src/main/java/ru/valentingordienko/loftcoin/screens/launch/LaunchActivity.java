package ru.valentingordienko.loftcoin.screens.launch;

import androidx.appcompat.app.AppCompatActivity;
import ru.valentingordienko.loftcoin.App;
import ru.valentingordienko.loftcoin.data.prefs.Prefs;
import ru.valentingordienko.loftcoin.screens.start.StartActivity;
import ru.valentingordienko.loftcoin.screens.wellcome.WellComeActivity;

import android.os.Bundle;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Prefs prefs = ((App) getApplication()).getPrefs();

        if (prefs.isFirstLaunch()) {
            WellComeActivity.start(this);
        } else {
            StartActivity.start(this);
        }

        finish();
    }
}
