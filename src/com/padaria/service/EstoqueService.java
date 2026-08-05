package com.padaria.service;

import com.padaria.model.Estoque;
import com.padaria.model.Insumo;
import com.padaria.repository.EstoqueRepository;
import java.util.List;

public class EstoqueService {

    private EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public void cadastrar(Estoque estoque) {
        estoqueRepository.salvar(estoque);
    }

    public void adicionarInsumo(int estoqueId, Insumo insumo) {
        Estoque estoque = estoqueRepository.buscarPorId(estoqueId);
        if (estoque != null) {
            estoque.adicionarInsumo(insumo);
            estoqueRepository.atualizar(estoque);
        }
    }

    public Estoque consultar(int id) {
        return estoqueRepository.buscarPorId(id);
    }

    public double consultarQuantidade(int estoqueId) {
        Estoque estoque = estoqueRepository.buscarPorId(estoqueId);
        return estoque != null ? estoque.consultarQuantidade() : 0;
    }

    public List<Estoque> listarTodos() {
        return estoqueRepository.listarTodos();
    }
}
