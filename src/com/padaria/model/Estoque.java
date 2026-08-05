package com.padaria.model;

import java.util.ArrayList;
import java.util.List;

public class Estoque {

    private int id;
    private double quantidadeAtual;
    private List<Insumo> insumos = new ArrayList<>();

    public Estoque(int id) {
        this.id = id;
        this.quantidadeAtual = 0;
    }

    public Estoque(int id, double quantidadeAtual) {
        this.id = id;
        this.quantidadeAtual = quantidadeAtual;
    }

    public void adicionarInsumo(Insumo insumo) {
        insumos.add(insumo);
    }

    public void registrarEntrada(int insumoId, double quantidade) {
        Insumo insumo = buscarInsumo(insumoId);
        if (insumo != null) {
            insumo.setQuantidade(insumo.getQuantidade() + quantidade);
            this.quantidadeAtual += quantidade;
        }
    }

    public void registrarSaida(int insumoId, double quantidade) {
        Insumo insumo = buscarInsumo(insumoId);
        if (insumo != null) {
            insumo.setQuantidade(insumo.getQuantidade() - quantidade);
            this.quantidadeAtual -= quantidade;
        }
    }

    public double consultarQuantidade() {
        return this.quantidadeAtual;
    }

    private Insumo buscarInsumo(int insumoId) {
        for (Insumo i : insumos) {
            if (i.getId() == insumoId) {
                return i;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public List<Insumo> getInsumos() {
        return insumos;
    }
}
