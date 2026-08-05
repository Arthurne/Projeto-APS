package com.padaria.repository;

import com.padaria.model.Estoque;
import java.util.ArrayList;
import java.util.List;

public class EstoqueRepositoryMemoria implements EstoqueRepository {

    private List<Estoque> estoques = new ArrayList<>();

    @Override
    public void salvar(Estoque estoque) {
        estoques.add(estoque);
    }

    @Override
    public void atualizar(Estoque estoque) {
        // Estoque é sempre a mesma instância em memória, atualização já reflete
    }

    @Override
    public Estoque buscarPorId(int id) {
        for (Estoque e : estoques) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    @Override
    public List<Estoque> listarTodos() {
        return estoques;
    }
}
