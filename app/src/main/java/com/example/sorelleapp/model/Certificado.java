package com.example.sorelleapp.model;

public class Certificado {
    private final String nome;
    private final String data;
    private final int arvores;

    public Certificado(String nome, String data, int arvores) {
        this.nome = nome;
        this.data = data;
        this.arvores = arvores;
    }

    public String getNome() { return nome; }
    public String getData() { return data; }
    public int getArvores() { return arvores; }
}