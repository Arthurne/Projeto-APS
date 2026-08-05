package com.padaria.controller;

import com.padaria.model.DemandaReposicao;
import com.padaria.model.Insumo;
import com.padaria.service.DemandaReposicaoService;
import java.util.List;

public class DemandaReposicaoController {

    private DemandaReposicaoService demandaService;

    public DemandaReposicaoController(DemandaReposicaoService demandaService) {
        this.demandaService = demandaService;
    }

    public void criarDemanda(DemandaReposicao demanda) {
        demandaService.criarDemanda(demanda);
    }

    public void atualizarStatus(int id, String status) {
        demandaService.atualizarStatus(id, status);
    }

    public List<Insumo> listarInsumos(int id) {
        return demandaService.listarInsumos(id);
    }

    public DemandaReposicao consultarDemanda(int id) {
        return demandaService.consultar(id);
    }

    public List<DemandaReposicao> listarDemandas() {
        return demandaService.listarTodos();
    }
}
