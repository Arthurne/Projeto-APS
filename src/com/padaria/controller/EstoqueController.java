package com.padaria.controller;

import com.padaria.model.Estoque;
import com.padaria.model.Insumo;
import com.padaria.service.EstoqueService;
import java.util.List;

public class EstoqueController {

    private EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    public void cadastrarEstoque(Estoque estoque) {
        estoqueService.cadastrar(estoque);
    }

    public void adicionarInsumo(int estoqueId, Insumo insumo) {
        estoqueService.adicionarInsumo(estoqueId, insumo);
    }

    public Estoque consultarEstoque(int id) {
        return estoqueService.consultar(id);
    }

    public double consultarQuantidade(int estoqueId) {
        return estoqueService.consultarQuantidade(estoqueId);
    }

    public List<Estoque> listarEstoques() {
        return estoqueService.listarTodos();
    }
}
