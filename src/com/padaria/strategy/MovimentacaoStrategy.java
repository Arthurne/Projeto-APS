package com.padaria.strategy;

import com.padaria.model.Estoque;

public interface MovimentacaoStrategy {
    void executar(Estoque estoque, int insumoId, double quantidade);
}
