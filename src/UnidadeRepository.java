package com.padaria.repository;

import com.padaria.model.Movimentacao;
import java.util.List;

public interface MovimentacaoRepository {
    void salvar(Movimentacao movimentacao);
    List<Movimentacao> listarTodos();
}
