package ru.valentingordienko.loftcoin.screens.rate;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.valentingordienko.loftcoin.App;
import ru.valentingordienko.loftcoin.R;
import ru.valentingordienko.loftcoin.data.api.Api;
import ru.valentingordienko.loftcoin.data.db.Database;
import ru.valentingordienko.loftcoin.data.db.model.CoinEntity;
import ru.valentingordienko.loftcoin.data.db.model.CoinEntityMapper;
import ru.valentingordienko.loftcoin.data.db.model.CoinEntityMapperImpl;
import ru.valentingordienko.loftcoin.data.prefs.Prefs;
import ru.valentingordienko.loftcoin.utils.Fiat;
import timber.log.Timber;

public class RateFragment extends Fragment implements RateView, Toolbar.OnMenuItemClickListener, CurrencyDialog.CurrencyDialogListener {

    private static final String LAYOUT_MANAGER_STATE = "layout_manager_state";

    public RateFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler)
    RecyclerView recycler;

    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    @BindView(R.id.rate_content)
    ViewGroup content;

    private RatePresenter presenter;
    private RateAdapter adapter;
    private Parcelable layoutMangerState;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = getActivity();

        if (activity == null) {
            return;
        }

        Api api = ((App) getActivity().getApplication()).getApi();
        Prefs prefs = ((App) getActivity().getApplication()).getPrefs();
        Database database = ((App) getActivity().getApplication()).getDatabase();
        CoinEntityMapper coinEntityMapper = new CoinEntityMapperImpl();

        presenter = new RatePresenterImpl(prefs, api, database, coinEntityMapper);
        adapter = new RateAdapter(prefs);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);


        toolbar.setTitle(R.string.rate_screen_title);
        toolbar.inflateMenu(R.menu.menu_rate);
        toolbar.setOnMenuItemClickListener(this);

        recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);


        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
            }
        });

        if (savedInstanceState != null) {
            layoutMangerState = savedInstanceState.getParcelable(LAYOUT_MANAGER_STATE);
        }

        Fragment fragment = getFragmentManager().findFragmentByTag(CurrencyDialog.TAG);

        if (fragment != null) {
            CurrencyDialog dialog = (CurrencyDialog) fragment;
            dialog.setListener(this);
        }

        presenter.attachView(this);
        presenter.getRate();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(LAYOUT_MANAGER_STATE, recycler.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setCoins(List<CoinEntity> coins) {
        Timber.d("setCoins: ");
        adapter.setItems(coins);

        if (layoutMangerState != null) {
            recycler.getLayoutManager().onRestoreInstanceState(layoutMangerState);
            layoutMangerState = null;
        }
    }

    @Override
    public void setRefreshing(Boolean refreshing) {
        refresh.setRefreshing(refreshing);
    }

    @Override
    public void showCurrencyDialog() {
        CurrencyDialog dialog = new CurrencyDialog();
        dialog.show(getFragmentManager(), CurrencyDialog.TAG);
        dialog.setListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_currency:
                presenter.onMenuItemCurrencyClick();
                return true;

            default:
                return false;
        }
    }

    @Override
    public void onCurrencySelected(Fiat currency) {
        presenter.onFiatCurrencySelected(currency);
    }

    @Override
    public void invalidateRates() {
        adapter.notifyDataSetChanged();
    }
}
