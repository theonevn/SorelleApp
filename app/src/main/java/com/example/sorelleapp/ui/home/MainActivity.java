package com.example.sorelleapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sorelleapp.ui.impacto.ImpactoActivity;
import com.example.sorelleapp.ui.produtos.ProdutosActivity;
import com.example.sorelleapp.R;
import com.example.sorelleapp.data.UsuarioStorage;
import com.example.sorelleapp.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnProdutos = findViewById(R.id.btnProdutos);
        Button btnImpacto = findViewById(R.id.btnImpact);
        TextView tvSair = findViewById(R.id.tvSair);

        btnProdutos.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ProdutosActivity.class)));

        btnImpacto.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ImpactoActivity.class)));

        tvSair.setOnClickListener(v -> {
            UsuarioStorage.setLogado(this, false);
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}