package com.padaria.model;

import java.util.Date;

public class Movimentacao {

    private int id;
    private String tipoMovimentacao;
    private double quantidade;
    private Date data;
    private Insumo insumo;
    private Estoque estoque;

    public Movimentacao(int id, String tipoMovimentacao, double quantidade, Date data, Insumo insumo, Estoque estoque) {
        this.id = id;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.data = data;
        this.insumo = insumo;
        this.estoque = estoque;
    }

    public int getId() {
        return id;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public Date getData() {
        return data;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public Estoque getEstoque() {
        return estoque;
    }
}
