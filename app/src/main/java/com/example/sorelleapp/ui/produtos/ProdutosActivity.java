package com.example.sorelleapp.ui.produtos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sorelleapp.R;
import com.example.sorelleapp.data.Catalogo;
import com.example.sorelleapp.data.Sacola;
import com.example.sorelleapp.ui.sacola.SacolaActivity;

public class ProdutosActivity extends AppCompatActivity {

    private Button btnSacola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_produtos);

        btnSacola = findViewById(R.id.btnSacola);
        RecyclerView rvProdutos = findViewById(R.id.rvProdutos);
        rvProdutos.setLayoutManager(new GridLayoutManager(this, 2));

        ProdutoAdapter adapter = new ProdutoAdapter(Catalogo.getProdutos(), produto -> {
            Sacola.getInstancia().adicionar(produto);
            atualizarBotaoSacola();
            Toast.makeText(this, produto.getNome() + " adicionado à sacola",
                    Toast.LENGTH_SHORT).show();
        });
        rvProdutos.setAdapter(adapter);

        btnSacola.setOnClickListener(v ->
                startActivity(new Intent(ProdutosActivity.this, SacolaActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarBotaoSacola();
    }

    private void atualizarBotaoSacola() {
        int quantidade = Sacola.getInstancia().getQuantidadeTotal();
        btnSacola.setText("Sacola (" + quantidade + ")");
    }
}