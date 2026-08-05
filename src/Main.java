package com.padaria.repository;

import com.padaria.model.Movimentacao;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoRepositoryMemoria implements MovimentacaoRepository {

    private List<Movimentacao> movimentacoes = new ArrayList<>();

    @Override
    public void salvar(Movimentacao movimentacao) {
        movimentacoes.add(movimentacao);
    }

    @Override
    public List<Movimentacao> listarTodos() {
        return movimentacoes;
    }
}
