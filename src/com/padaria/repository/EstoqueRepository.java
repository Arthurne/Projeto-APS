package com.padaria.repository;

import com.padaria.model.Estoque;
import java.util.List;

public interface EstoqueRepository {
    void salvar(Estoque estoque);
    void atualizar(Estoque estoque);
    Estoque buscarPorId(int id);
    List<Estoque> listarTodos();
}
