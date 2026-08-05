package com.padaria.repository;

import com.padaria.model.Unidade;
import java.util.List;

public interface UnidadeRepository {
    void salvar(Unidade unidade);
    void atualizar(Unidade unidade);
    Unidade buscarPorId(int id);
    List<Unidade> listarTodos();
}
