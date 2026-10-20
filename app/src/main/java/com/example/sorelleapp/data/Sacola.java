package com.example.sorelleapp.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.sorelleapp.model.Produto;

public class Sacola {

    private static final Sacola INSTANCIA = new Sacola();

    private final Map<Produto, Integer> itens = new LinkedHashMap<>();

    private Sacola() {
    }

    public static Sacola getInstancia() {
        return INSTANCIA;
    }

    public void adicionar(Produto produto) {
        int quantidadeAtual = itens.getOrDefault(produto, 0);
        itens.put(produto, quantidadeAtual + 1);
    }

    public void remover(Produto produto) {
        Integer quantidadeAtual = itens.get(produto);
        if (quantidadeAtual == null) return;
        if (quantidadeAtual <= 1) {
            itens.remove(produto);
        } else {
            itens.put(produto, quantidadeAtual - 1);
        }
    }

    public List<Produto> getProdutos() {
        return new ArrayList<>(itens.keySet());
    }

    public int getQuantidade(Produto produto) {
        return itens.getOrDefault(produto, 0);
    }

    public Map<Produto, Integer> getItens() {
        return itens;
    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<Produto, Integer> item : itens.entrySet()) {
            total += item.getKey().getPreco() * item.getValue();
        }
        return total;
    }

    public int getQuantidadeTotal() {
        int total = 0;
        for (int quantidade : itens.values()) {
            total += quantidade;
        }

        return total;

    }

    public void limpar() {
        itens.clear();
    }

    public int getArvoresTotal() {
        int total = 0;
        for (Map.Entry<Produto, Integer> item : itens.entrySet()) {
            total += item.getKey().getArvores() * item.getValue();
        }
        return total;
    }
}