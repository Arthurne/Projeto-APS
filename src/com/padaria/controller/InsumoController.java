package com.padaria.controller;

import com.padaria.model.Insumo;
import com.padaria.service.InsumoService;
import java.util.List;

public class InsumoController {

    private InsumoService insumoService;

    public InsumoController(InsumoService insumoService) {
        this.insumoService = insumoService;
    }

    public void cadastrarInsumo(Insumo insumo) {
        insumoService.cadastrar(insumo);
    }

    public void atualizarInsumo(Insumo insumo) {
        insumoService.atualizar(insumo);
    }

    public void excluirInsumo(int id) {
        insumoService.excluir(id);
    }

    public Insumo consultarInsumo(int id) {
        return insumoService.consultar(id);
    }

    public List<Insumo> listarInsumos() {
        return insumoService.listarTodos();
    }
}
