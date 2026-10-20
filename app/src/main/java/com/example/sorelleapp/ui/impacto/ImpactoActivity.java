package com.example.sorelleapp.ui.impacto;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sorelleapp.R;
import com.example.sorelleapp.data.ImpactStorage;

import java.util.List;

import com.example.sorelleapp.model.Certificado;

public class ImpactoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_impacto);

        TextView tvArvores = findViewById(R.id.tvArvores);
        TextView tvCo2 = findViewById(R.id.tvCo2);
        TextView tvNivel = findViewById(R.id.tvNivel);
        TextView tvVazio = findViewById(R.id.tvVazioImpacto);
        RecyclerView rvCertificados = findViewById(R.id.rvCertificados);

        tvArvores.setText(String.valueOf(ImpactStorage.getArvores(this)));
        tvCo2.setText(ImpactStorage.getCo2(this) + " Kg");
        tvNivel.setText(ImpactStorage.getNivel(this));

        List<Certificado> certificados = ImpactStorage.getCertificados(this);
        rvCertificados.setLayoutManager(new LinearLayoutManager(this));
        rvCertificados.setAdapter(new CertificadoAdapter(certificados));

        boolean vazio = certificados.isEmpty();
        tvVazio.setVisibility(vazio ? View.VISIBLE : View.GONE);
        rvCertificados.setVisibility(vazio ? View.GONE : View.VISIBLE);
    }
}