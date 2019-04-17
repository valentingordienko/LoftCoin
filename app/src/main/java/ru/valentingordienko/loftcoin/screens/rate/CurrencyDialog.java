package ru.valentingordienko.loftcoin.screens.rate;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import ru.valentingordienko.loftcoin.R;
import ru.valentingordienko.loftcoin.utils.Fiat;

public class CurrencyDialog extends DialogFragment {

    public static final String TAG = "CurrencyDialog";

    private CurrencyDialogListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_currency, null);


        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setView(view)
                .create();

        View usd = view.findViewById(R.id.usd);
        View eur = view.findViewById(R.id.eur);
        View rub = view.findViewById(R.id.rub);

        usd.setOnClickListener(v -> {
            dismiss();
            if (listener != null) {
                listener.onCurrencySelected(Fiat.USD);
            }
        });

        eur.setOnClickListener(v -> {
            dismiss();
            if (listener != null) {
                listener.onCurrencySelected(Fiat.EUR);
            }
        });

        rub.setOnClickListener(v -> {
            dismiss();
            if (listener != null) {
                listener.onCurrencySelected(Fiat.RUB);
            }
        });

        return dialog;
    }

    public interface CurrencyDialogListener {
        void onCurrencySelected(Fiat currency);
    }

    public void setListener(CurrencyDialogListener listener) {
        this.listener = listener;
    }
}
