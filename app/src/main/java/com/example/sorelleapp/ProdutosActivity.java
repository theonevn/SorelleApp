package com.example.sorelleapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ProdutosActivity extends AppCompatActivity {

    Produto serum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_produtos);

        serum = Catalogo.getProdutos().get(0);

        Button btnSerum = findViewById(R.id.btnSerum);

        btnSerum.setOnClickListener(v -> {
            System.out.println(serum.getNome());
        });
    }
}