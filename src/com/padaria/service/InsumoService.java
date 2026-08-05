package com.padaria.service;

import com.padaria.model.Insumo;
import com.padaria.repository.InsumoRepository;
import java.util.List;

public class InsumoService {

    private InsumoRepository insumoRepository;

    public InsumoService(InsumoRepository insumoRepository) {
        this.insumoRepository = insumoRepository;
    }

    public void cadastrar(Insumo insumo) {
        insumoRepository.salvar(insumo);
    }

    public void atualizar(Insumo insumo) {
        insumoRepository.atualizar(insumo);
    }

    public void excluir(int id) {
        insumoRepository.excluir(id);
    }

    public Insumo consultar(int id) {
        return insumoRepository.buscarPorId(id);
    }

    public List<Insumo> listarTodos() {
        return insumoRepository.listarTodos();
    }
}
