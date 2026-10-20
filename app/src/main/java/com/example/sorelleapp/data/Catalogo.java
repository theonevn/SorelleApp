package com.example.sorelleapp.data;

import com.example.sorelleapp.R;

import java.util.ArrayList;
import java.util.List;

import com.example.sorelleapp.model.Produto;

public class Catalogo {

    private static final List<Produto> PRODUTOS = new ArrayList<>();

    static {
        PRODUTOS.add(new Produto("Sérum Açaí",
                "Bioativos puros da floresta.", 89.90, "Vegano", 1,
                R.drawable.serum_acai));
        PRODUTOS.add(new Produto("Creme Castanha",
                "Hidratação intensa e prolongada.", 55.90, "Reciclado", 3,
                R.drawable.creme_castanha));
        PRODUTOS.add(new Produto("Óleo Andiroba",
                "Regenerador natural da pele.", 120.00, "Comunitário", 1,
                R.drawable.oleo_andiroba));
        PRODUTOS.add(new Produto("Kit Ritual da Mata",
                "Experiência de spa sustentável.", 210.50, "Impacto Triplo", 3,
                R.drawable.kit_ritual));
    }

    public static List<Produto> getProdutos() {
        return PRODUTOS;
    }
}