package com.padaria.model;

public class Unidade {

    private int id;
    private String nome;
    private String endereco;
    private Estoque estoque;

    public Unidade(int id, String nome, String endereco, Estoque estoque) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.estoque = estoque;
    }

    public void cadastrar() {
        // regra de cadastro delegada ao Service
    }

    public void atualizar(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public double consultarEstoque() {
        return estoque.consultarQuantidade();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public Estoque getEstoque() {
        return estoque;
    }
}
