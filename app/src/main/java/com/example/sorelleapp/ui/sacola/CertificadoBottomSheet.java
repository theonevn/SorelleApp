package com.example.sorelleapp.ui.sacola;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sorelleapp.R;
import com.example.sorelleapp.data.Sacola;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class CertificadoBottomSheet extends BottomSheetDialogFragment {

    public interface OnConfirmarListener {
        void onConfirmar(String nome);
    }

    private OnConfirmarListener listener;

    public void setOnConfirmarListener(OnConfirmarListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_certificado, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputEditText etNome = view.findViewById(R.id.etNome);
        TextView tvTotal = view.findViewById(R.id.tvTotalDialog);
        Button btnConfirmar = view.findViewById(R.id.btnConfirmar);
        Button btnCancelar = view.findViewById(R.id.btnCancelar);

        tvTotal.setText(String.format(new Locale("pt", "BR"), "R$ %.2f",
                Sacola.getInstancia().getTotal()));

        btnConfirmar.setOnClickListener(v -> {
            String nome = etNome.getText() == null ? "" : etNome.getText().toString().trim();
            if (nome.isEmpty()) {
                etNome.setError("Digite seu nome completo");
                return;
            }
            dismiss();
            if (listener != null) {
                listener.onConfirmar(nome);
            }
        });

        btnCancelar.setOnClickListener(v -> dismiss());
    }
}