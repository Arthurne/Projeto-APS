package com.padaria.service;

import com.padaria.model.Unidade;
import com.padaria.repository.UnidadeRepository;
import java.util.List;

public class UnidadeService {

    private UnidadeRepository unidadeRepository;

    public UnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public void cadastrar(Unidade unidade) {
        unidadeRepository.salvar(unidade);
    }

    public void atualizar(Unidade unidade) {
        unidadeRepository.atualizar(unidade);
    }

    public Unidade consultar(int id) {
        return unidadeRepository.buscarPorId(id);
    }

    public double consultarEstoque(int id) {
        Unidade unidade = unidadeRepository.buscarPorId(id);
        return unidade != null ? unidade.consultarEstoque() : 0;
    }

    public List<Unidade> listarTodos() {
        return unidadeRepository.listarTodos();
    }
}
