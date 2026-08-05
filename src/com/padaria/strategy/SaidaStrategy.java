package com.padaria.strategy;

import com.padaria.model.Estoque;

public class SaidaStrategy implements MovimentacaoStrategy {

    @Override
    public void executar(Estoque estoque, int insumoId, double quantidade) {
        estoque.registrarSaida(insumoId, quantidade);
    }
}
