package com.padaria.view;

import com.padaria.controller.DemandaReposicaoController;
import com.padaria.controller.EstoqueController;
import com.padaria.controller.InsumoController;
import com.padaria.controller.MovimentacaoController;
import com.padaria.controller.UnidadeController;
import com.padaria.model.DemandaReposicao;
import com.padaria.model.Estoque;
import com.padaria.model.Insumo;
import com.padaria.model.Movimentacao;
import com.padaria.model.Unidade;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SistemaView {

    private Scanner scanner = new Scanner(System.in);

    private UnidadeController unidadeController;
    private InsumoController insumoController;
    private EstoqueController estoqueController;
    private MovimentacaoController movimentacaoController;
    private DemandaReposicaoController demandaController;

    public SistemaView(UnidadeController unidadeController, InsumoController insumoController,
                        EstoqueController estoqueController, MovimentacaoController movimentacaoController,
                        DemandaReposicaoController demandaController) {
        this.unidadeController = unidadeController;
        this.insumoController = insumoController;
        this.estoqueController = estoqueController;
        this.movimentacaoController = movimentacaoController;
        this.demandaController = demandaController;
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerInt();
            switch (opcao) {
                case 1 -> cadastrarUnidade();
                case 2 -> cadastrarInsumo();
                case 3 -> adicionarInsumoAoEstoque();
                case 4 -> registrarMovimentacao();
                case 5 -> criarDemanda();
                case 6 -> consultarEstoque();
                case 7 -> listarDemandas();
                case 8 -> listarInsumos();
                case 9 -> listarUnidades();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("\n--- Sistema Rede de Padarias ---");
        System.out.println("1 - Cadastrar Unidade");
        System.out.println("2 - Cadastrar Insumo");
        System.out.println("3 - Adicionar Insumo ao Estoque");
        System.out.println("4 - Registrar Movimentação (entrada/saída)");
        System.out.println("5 - Criar Demanda de Reposição");
        System.out.println("6 - Consultar Estoque de uma Unidade");
        System.out.println("7 - Listar Demandas de Reposição");
        System.out.println("8 - Listar Insumos");
        System.out.println("9 - Listar Unidades");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private void cadastrarUnidade() {
        System.out.print("ID da unidade: ");
        int id = lerInt();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        Estoque estoque = new Estoque(id);
        estoqueController.cadastrarEstoque(estoque);

        Unidade unidade = new Unidade(id, nome, endereco, estoque);
        unidadeController.cadastrarUnidade(unidade);

        System.out.println("Unidade cadastrada com estoque vinculado.");
    }

    private void cadastrarInsumo() {
        System.out.print("ID do insumo: ");
        int id = lerInt();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Unidade de medida: ");
        String unidadeMedida = scanner.nextLine();
        System.out.print("Quantidade inicial: ");
        double quantidade = lerDouble();

        Insumo insumo = new Insumo(id, nome, unidadeMedida, quantidade);
        insumoController.cadastrarInsumo(insumo);

        System.out.println("Insumo cadastrado.");
    }

    private void adicionarInsumoAoEstoque() {
        System.out.print("ID da unidade (estoque): ");
        int estoqueId = lerInt();
        System.out.print("ID do insumo: ");
        int insumoId = lerInt();

        Insumo insumo = insumoController.consultarInsumo(insumoId);
        if (insumo == null) {
            System.out.println("Insumo não encontrado.");
            return;
        }
        estoqueController.adicionarInsumo(estoqueId, insumo);
        System.out.println("Insumo adicionado ao estoque.");
    }

    private void registrarMovimentacao() {
        System.out.print("ID da unidade (estoque): ");
        int estoqueId = lerInt();
        System.out.print("ID do insumo: ");
        int insumoId = lerInt();
        System.out.print("Tipo (entrada/saida): ");
        String tipo = scanner.nextLine();
        System.out.print("Quantidade: ");
        double quantidade = lerDouble();

        Estoque estoque = estoqueController.consultarEstoque(estoqueId);
        Insumo insumo = insumoController.consultarInsumo(insumoId);
        if (estoque == null || insumo == null) {
            System.out.println("Estoque ou insumo não encontrado.");
            return;
        }

        Movimentacao movimentacao = new Movimentacao(
                (int) (Math.random() * 100000), tipo, quantidade, new Date(), insumo, estoque);
        movimentacaoController.registrarMovimentacao(movimentacao);

        System.out.println("Movimentação registrada. Quantidade atual no estoque: " + estoque.consultarQuantidade());
    }

    private void criarDemanda() {
        System.out.print("ID da demanda: ");
        int id = lerInt();
        System.out.print("ID da unidade solicitante: ");
        int unidadeId = lerInt();

        Unidade unidade = unidadeController.consultarUnidade(unidadeId);
        if (unidade == null) {
            System.out.println("Unidade não encontrada.");
            return;
        }

        DemandaReposicao demanda = new DemandaReposicao(id, new Date(), unidade);
        demandaController.criarDemanda(demanda);

        System.out.println("Demanda criada com status: " + demanda.getStatus());
    }

    private void consultarEstoque() {
        System.out.print("ID da unidade: ");
        int id = lerInt();
        double quantidade = unidadeController.consultarEstoque(id);
        System.out.println("Quantidade atual no estoque: " + quantidade);
    }

    private void listarDemandas() {
        List<DemandaReposicao> demandas = demandaController.listarDemandas();

        if (demandas.isEmpty()) {
            System.out.println("Nenhuma demanda de reposição cadastrada.");
            return;
        }

        for (DemandaReposicao d : demandas) {
            imprimirItem(
                    "Demanda " + d.getId(),
                    "Unidade: " + d.getUnidade().getId(),
                    "Status: " + d.getStatus()
            );
        }
    }

    private void listarInsumos() {
        List<Insumo> insumos = insumoController.listarInsumos();

        if (insumos.isEmpty()) {
            System.out.println("Nenhum insumo cadastrado.");
            return;
        }

        for (Insumo i : insumos) {
            imprimirItem(
                    "Insumo " + i.getId(),
                    "Nome: " + i.getNome(),
                    "Unidade de medida: " + i.getUnidadeMedida(),
                    "Quantidade: " + i.getQuantidade()
            );
        }
    }

    private void listarUnidades() {
        List<Unidade> unidades = unidadeController.listarUnidades();

        if (unidades.isEmpty()) {
            System.out.println("Nenhuma unidade cadastrada.");
            return;
        }

        for (Unidade u : unidades) {
            imprimirItem(
                    "Unidade " + u.getId(),
                    "Nome: " + u.getNome(),
                    "Endereço: " + u.getEndereco(),
                    "Estoque atual: " + u.consultarEstoque()
            );
        }
    }

    private void imprimirItem(String titulo, String... campos) {
        System.out.println("----------------");
        System.out.println(titulo);
        for (String campo : campos) {
            System.out.println("- " + campo);
        }
        System.out.println("----------------");
    }

    private int lerInt() {
        int valor = Integer.parseInt(scanner.nextLine().trim());
        return valor;
    }

    private double lerDouble() {
        return Double.parseDouble(scanner.nextLine().trim());
    }
}
