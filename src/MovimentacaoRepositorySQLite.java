package com.padaria.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DemandaReposicao {

    private int id;
    private Date dataSolicitacao;
    private String status;
    private Unidade unidade;
    private List<Insumo> insumos = new ArrayList<>();

    public DemandaReposicao(int id, Date dataSolicitacao, Unidade unidade) {
        this.id = id;
        this.dataSolicitacao = dataSolicitacao;
        this.status = "aberta";
        this.unidade = unidade;
    }

    public DemandaReposicao(int id, Date dataSolicitacao, String status, Unidade unidade) {
        this.id = id;
        this.dataSolicitacao = dataSolicitacao;
        this.status = status;
        this.unidade = unidade;
    }

    public void criarDemanda() {
        this.status = "aberta";
    }

    public void atualizarStatus(String status) {
        this.status = status;
    }

    public List<Insumo> listarInsumos() {
        return insumos;
    }

    public void adicionarInsumo(Insumo insumo) {
        insumos.add(insumo);
    }

    public int getId() {
        return id;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public String getStatus() {
        return status;
    }

    public Unidade getUnidade() {
        return unidade;
    }
}
