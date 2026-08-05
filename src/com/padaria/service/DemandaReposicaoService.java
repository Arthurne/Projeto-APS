package com.padaria.service;

import com.padaria.model.DemandaReposicao;
import com.padaria.model.Insumo;
import com.padaria.repository.DemandaReposicaoRepository;
import java.util.List;

public class DemandaReposicaoService {

    private DemandaReposicaoRepository demandaRepository;

    public DemandaReposicaoService(DemandaReposicaoRepository demandaRepository) {
        this.demandaRepository = demandaRepository;
    }

    public void criarDemanda(DemandaReposicao demanda) {
        demanda.criarDemanda();
        demandaRepository.salvar(demanda);
    }

    public void atualizarStatus(int id, String status) {
        DemandaReposicao demanda = demandaRepository.buscarPorId(id);
        if (demanda != null) {
            demanda.atualizarStatus(status);
        }
    }

    public List<Insumo> listarInsumos(int id) {
        DemandaReposicao demanda = demandaRepository.buscarPorId(id);
        return demanda != null ? demanda.listarInsumos() : null;
    }

    public DemandaReposicao consultar(int id) {
        return demandaRepository.buscarPorId(id);
    }

    public List<DemandaReposicao> listarTodos() {
        return demandaRepository.listarTodos();
    }
}
