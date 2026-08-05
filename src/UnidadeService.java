package com.padaria.repository;

import com.padaria.model.Unidade;
import java.util.ArrayList;
import java.util.List;

public class UnidadeRepositoryMemoria implements UnidadeRepository {

    private List<Unidade> unidades = new ArrayList<>();

    @Override
    public void salvar(Unidade unidade) {
        unidades.add(unidade);
    }

    @Override
    public void atualizar(Unidade unidade) {
        Unidade existente = buscarPorId(unidade.getId());
        if (existente != null) {
            existente.atualizar(unidade.getNome(), unidade.getEndereco());
        }
    }

    @Override
    public Unidade buscarPorId(int id) {
        for (Unidade u : unidades) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List<Unidade> listarTodos() {
        return unidades;
    }
}
