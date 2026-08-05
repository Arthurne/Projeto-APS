package com.padaria.repository;

import com.padaria.model.DemandaReposicao;
import java.util.ArrayList;
import java.util.List;

public class DemandaReposicaoRepositoryMemoria implements DemandaReposicaoRepository {

    private List<DemandaReposicao> demandas = new ArrayList<>();

    @Override
    public void salvar(DemandaReposicao demanda) {
        demandas.add(demanda);
    }

    @Override
    public void atualizar(DemandaReposicao demanda) {
        DemandaReposicao existente = buscarPorId(demanda.getId());
        if (existente != null) {
            existente.atualizarStatus(demanda.getStatus());
        }
    }

    @Override
    public DemandaReposicao buscarPorId(int id) {
        for (DemandaReposicao d : demandas) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    @Override
    public List<DemandaReposicao> listarTodos() {
        return demandas;
    }
}
