package com.padaria.controller;

import com.padaria.model.Movimentacao;
import com.padaria.service.MovimentacaoService;
import java.util.List;

public class MovimentacaoController {

    private MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    public void registrarMovimentacao(Movimentacao movimentacao) {
        movimentacaoService.registrarMovimentacao(movimentacao);
    }

    public List<Movimentacao> listarMovimentacoes() {
        return movimentacaoService.listarTodos();
    }
}
