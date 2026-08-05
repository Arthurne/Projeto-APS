package com.padaria.repository;

import com.padaria.model.DemandaReposicao;
import java.util.List;

public interface DemandaReposicaoRepository {
    void salvar(DemandaReposicao demanda);
    void atualizar(DemandaReposicao demanda);
    DemandaReposicao buscarPorId(int id);
    List<DemandaReposicao> listarTodos();
}
