package com.padaria.strategy;

import com.padaria.model.Estoque;

public class EntradaStrategy implements MovimentacaoStrategy {

    @Override
    public void executar(Estoque estoque, int insumoId, double quantidade) {
        estoque.registrarEntrada(insumoId, quantidade);
    }
}
