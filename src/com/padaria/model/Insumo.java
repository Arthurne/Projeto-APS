package com.padaria.model;

public class Insumo {

    private int id;
    private String nome;
    private String unidadeMedida;
    private double quantidade;

    public Insumo(int id, String nome, String unidadeMedida, double quantidade) {
        this.id = id;
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.quantidade = quantidade;
    }

    public void cadastrar() {
        // regra de cadastro delegada ao Service
    }

    public void atualizar(String nome, String unidadeMedida, double quantidade) {
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.quantidade = quantidade;
    }

    public double consultar() {
        return this.quantidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
}
