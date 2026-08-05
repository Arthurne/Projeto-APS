package com.padaria.repository;

import com.padaria.model.Insumo;
import java.util.ArrayList;
import java.util.List;

public class InsumoRepositoryMemoria implements InsumoRepository {

    private List<Insumo> insumos = new ArrayList<>();

    @Override
    public void salvar(Insumo insumo) {
        insumos.add(insumo);
    }

    @Override
    public void atualizar(Insumo insumo) {
        Insumo existente = buscarPorId(insumo.getId());
        if (existente != null) {
            existente.atualizar(insumo.getNome(), insumo.getUnidadeMedida(), insumo.getQuantidade());
        }
    }

    @Override
    public void excluir(int id) {
        insumos.removeIf(i -> i.getId() == id);
    }

    @Override
    public Insumo buscarPorId(int id) {
        for (Insumo i : insumos) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    @Override
    public List<Insumo> listarTodos() {
        return insumos;
    }
}
