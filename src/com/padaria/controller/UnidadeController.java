package com.padaria.controller;

import com.padaria.model.Unidade;
import com.padaria.service.UnidadeService;
import java.util.List;

public class UnidadeController {

    private UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    public void cadastrarUnidade(Unidade unidade) {
        unidadeService.cadastrar(unidade);
    }

    public void atualizarUnidade(Unidade unidade) {
        unidadeService.atualizar(unidade);
    }

    public Unidade consultarUnidade(int id) {
        return unidadeService.consultar(id);
    }

    public double consultarEstoque(int id) {
        return unidadeService.consultarEstoque(id);
    }

    public List<Unidade> listarUnidades() {
        return unidadeService.listarTodos();
    }
}
