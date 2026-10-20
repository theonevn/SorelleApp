package com.example.sorelleapp.model;

public class Produto {
    private final String nome;
    private final String descricao;
    private final double preco;
    private final String tag;
    private final int arvores;
    private final int imagem;

    public Produto(String nome, String descricao, double preco, String tag, int arvores, int imagem) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tag = tag;
        this.arvores = arvores;
        this.imagem = imagem;
    }

    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }
    public String getTag() { return tag; }
    public int getArvores() { return arvores; }
    public int getImagem() { return imagem; }
}