package com.padaria.service;

import com.padaria.model.Estoque;
import com.padaria.model.Insumo;
import com.padaria.model.Movimentacao;
import com.padaria.repository.EstoqueRepository;
import com.padaria.repository.MovimentacaoRepository;
import com.padaria.strategy.EntradaStrategy;
import com.padaria.strategy.MovimentacaoStrategy;
import com.padaria.strategy.SaidaStrategy;
import java.util.List;

public class MovimentacaoService {

    private MovimentacaoRepository movimentacaoRepository;
    private EstoqueRepository estoqueRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, EstoqueRepository estoqueRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.estoqueRepository = estoqueRepository;
    }

    public void registrarMovimentacao(Movimentacao movimentacao) {
        MovimentacaoStrategy strategy = obterStrategy(movimentacao.getTipoMovimentacao());
        strategy.executar(movimentacao.getEstoque(), movimentacao.getInsumo().getId(), movimentacao.getQuantidade());
        estoqueRepository.atualizar(movimentacao.getEstoque());
        movimentacaoRepository.salvar(movimentacao);
    }

    private MovimentacaoStrategy obterStrategy(String tipoMovimentacao) {
        if (tipoMovimentacao.equalsIgnoreCase("entrada")) {
            return new EntradaStrategy();
        }
        return new SaidaStrategy();
    }

    public List<Movimentacao> listarTodos() {
        return movimentacaoRepository.listarTodos();
    }
}
