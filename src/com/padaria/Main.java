package com.padaria;

import com.padaria.controller.DemandaReposicaoController;
import com.padaria.controller.EstoqueController;
import com.padaria.controller.InsumoController;
import com.padaria.controller.MovimentacaoController;
import com.padaria.controller.UnidadeController;
import com.padaria.db.DatabaseManager;
import com.padaria.factory.RepositoryFactory;
import com.padaria.repository.EstoqueRepository;
import com.padaria.service.DemandaReposicaoService;
import com.padaria.service.EstoqueService;
import com.padaria.service.InsumoService;
import com.padaria.service.MovimentacaoService;
import com.padaria.service.UnidadeService;
import com.padaria.view.SistemaView;

public class Main {

    public static void main(String[] args) {
        DatabaseManager.inicializar();

        EstoqueRepository estoqueRepository = RepositoryFactory.criarEstoqueRepository();

        InsumoService insumoService = new InsumoService(RepositoryFactory.criarInsumoRepository());
        EstoqueService estoqueService = new EstoqueService(estoqueRepository);
        UnidadeService unidadeService = new UnidadeService(RepositoryFactory.criarUnidadeRepository());
        MovimentacaoService movimentacaoService = new MovimentacaoService(RepositoryFactory.criarMovimentacaoRepository(), estoqueRepository);
        DemandaReposicaoService demandaService = new DemandaReposicaoService(RepositoryFactory.criarDemandaReposicaoRepository());

        InsumoController insumoController = new InsumoController(insumoService);
        EstoqueController estoqueController = new EstoqueController(estoqueService);
        UnidadeController unidadeController = new UnidadeController(unidadeService);
        MovimentacaoController movimentacaoController = new MovimentacaoController(movimentacaoService);
        DemandaReposicaoController demandaController = new DemandaReposicaoController(demandaService);

        SistemaView view = new SistemaView(
                unidadeController, insumoController, estoqueController, movimentacaoController, demandaController);

        view.iniciar();
    }
}
