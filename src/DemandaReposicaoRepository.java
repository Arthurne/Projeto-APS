package com.padaria.repository;

import com.padaria.model.Insumo;
import java.util.List;

public interface InsumoRepository {
    void salvar(Insumo insumo);
    void atualizar(Insumo insumo);
    void excluir(int id);
    Insumo buscarPorId(int id);
    List<Insumo> listarTodos();
}
