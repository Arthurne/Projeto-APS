package com.padaria.factory;

import com.padaria.repository.DemandaReposicaoRepository;
import com.padaria.repository.DemandaReposicaoRepositoryMemoria;
import com.padaria.repository.DemandaReposicaoRepositorySQLite;
import com.padaria.repository.EstoqueRepository;
import com.padaria.repository.EstoqueRepositoryMemoria;
import com.padaria.repository.EstoqueRepositorySQLite;
import com.padaria.repository.InsumoRepository;
import com.padaria.repository.InsumoRepositoryMemoria;
import com.padaria.repository.InsumoRepositorySQLite;
import com.padaria.repository.MovimentacaoRepository;
import com.padaria.repository.MovimentacaoRepositoryMemoria;
import com.padaria.repository.MovimentacaoRepositorySQLite;
import com.padaria.repository.UnidadeRepository;
import com.padaria.repository.UnidadeRepositoryMemoria;
import com.padaria.repository.UnidadeRepositorySQLite;

public class RepositoryFactory {

    public enum TipoArmazenamento {
        MEMORIA,
        SQLITE
    }

    // Troque para TipoArmazenamento.MEMORIA se quiser voltar a rodar tudo em memória.
    private static TipoArmazenamento tipoArmazenamento = TipoArmazenamento.SQLITE;

    public static void setTipoArmazenamento(TipoArmazenamento tipo) {
        tipoArmazenamento = tipo;
    }

    public static InsumoRepository criarInsumoRepository() {
        return tipoArmazenamento == TipoArmazenamento.SQLITE
                ? new InsumoRepositorySQLite()
                : new InsumoRepositoryMemoria();
    }

    public static EstoqueRepository criarEstoqueRepository() {
        return tipoArmazenamento == TipoArmazenamento.SQLITE
                ? new EstoqueRepositorySQLite()
                : new EstoqueRepositoryMemoria();
    }

    public static UnidadeRepository criarUnidadeRepository() {
        return tipoArmazenamento == TipoArmazenamento.SQLITE
                ? new UnidadeRepositorySQLite()
                : new UnidadeRepositoryMemoria();
    }

    public static MovimentacaoRepository criarMovimentacaoRepository() {
        return tipoArmazenamento == TipoArmazenamento.SQLITE
                ? new MovimentacaoRepositorySQLite()
                : new MovimentacaoRepositoryMemoria();
    }

    public static DemandaReposicaoRepository criarDemandaReposicaoRepository() {
        return tipoArmazenamento == TipoArmazenamento.SQLITE
                ? new DemandaReposicaoRepositorySQLite()
                : new DemandaReposicaoRepositoryMemoria();
    }
}
