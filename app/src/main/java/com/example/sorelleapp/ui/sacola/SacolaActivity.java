package com.example.sorelleapp.ui.sacola;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sorelleapp.ui.impacto.ImpactoActivity;
import com.example.sorelleapp.R;
import com.example.sorelleapp.data.ImpactStorage;
import com.example.sorelleapp.data.Sacola;

import java.util.Locale;

public class SacolaActivity extends AppCompatActivity {

    private RecyclerView rvSacola;
    private TextView tvTotal, tvVazio;
    private Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activy_sacola);

        rvSacola = findViewById(R.id.rvSacola);
        tvTotal = findViewById(R.id.tvTotal);
        tvVazio = findViewById(R.id.tvVazio);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        rvSacola.setLayoutManager(new LinearLayoutManager(this));
        rvSacola.setAdapter(new SacolaAdapter(this::atualizarTela));

        btnFinalizar.setOnClickListener(v -> {
            CertificadoBottomSheet sheet = new CertificadoBottomSheet();
            sheet.setOnConfirmarListener(nome -> {
                int arvores = Sacola.getInstancia().getArvoresTotal();
                ImpactStorage.registrarCompra(this, nome, arvores);
                Sacola.getInstancia().limpar();
                startActivity(new Intent(this, ImpactoActivity.class));
                finish();
            });
            sheet.show(getSupportFragmentManager(), "certificado");
        });

        atualizarTela();
    }

    private void atualizarTela() {
        Sacola sacola = Sacola.getInstancia();
        boolean vazia = sacola.getItens().isEmpty();

        tvVazio.setVisibility(vazia ? View.VISIBLE : View.GONE);
        rvSacola.setVisibility(vazia ? View.GONE : View.VISIBLE);
        btnFinalizar.setEnabled(!vazia);
        tvTotal.setText(String.format(new Locale("pt", "BR"), "R$ %.2f", sacola.getTotal()));
    }
}