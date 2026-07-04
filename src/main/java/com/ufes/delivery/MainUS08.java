package com.ufes.delivery;

import com.ufes.delivery.model.*;
import com.ufes.delivery.repository.*;
import com.ufes.delivery.service.estoque.MovimentacaoEstoqueService;
import com.ufes.delivery.model.CategoriaProduto;

import java.time.LocalDate;

public class MainUS08 {

    public static void main(String[] args) {

        System.out.println("=== INICIO TESTES US08 ===\n");

        // =========================
        // REPOSITÓRIOS
        // =========================
        IProdutoRepository produtoRepo = new ProdutoRepositoryEmMemoria();
        IMovimentacaoEstoqueRepository movRepo =
                new MovimentacaoEstoqueRepositoryEmMemoria();

        MovimentacaoEstoqueService service =
                new MovimentacaoEstoqueService(produtoRepo, movRepo);

        // =========================
        // PRODUTO BASE
        // =========================
        Produto caderno = new Produto(
                2001,
                "Caderno Universitario",
                CategoriaProduto.PAPELARIA,
                35.90,
                120
        );

        produtoRepo.adicionar(caderno);

        // =========================
        // CENÁRIO 1 - ENTRADA (OK)
        // =========================
        System.out.println("--- CENARIO 1: ENTRADA ---");

        try {
            MovimentacaoEstoque mov = service.preverMovimentacao(
                    2001,
                    TipoMovimentacao.ENTRADA,
                    30,
                    LocalDate.now(),
                    null,
                    "NF-12345"
            );

            System.out.println("PREVIA: " + mov);

            service.confirmarMovimentacao(
                    2001,
                    TipoMovimentacao.ENTRADA,
                    30,
                    LocalDate.now(),
                    null,
                    "NF-12345"
            );

            System.out.println("OK - Entrada confirmada");
            System.out.println("Estoque atual: " + caderno.getEstoqueAtual());

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        // =========================
        // CENÁRIO 2 - AJUSTE (OK)
        // =========================
        System.out.println("\n--- CENARIO 2: AJUSTE ---");

        try {
            MovimentacaoEstoque mov = service.preverMovimentacao(
                    2001,
                    TipoMovimentacao.AJUSTE,
                    15,
                    LocalDate.now(),
                    "Inventario",
                    null
            );

            System.out.println("PREVIA: " + mov);

            service.confirmarMovimentacao(
                    2001,
                    TipoMovimentacao.AJUSTE,
                    15,
                    LocalDate.now(),
                    "Inventario",
                    null
            );

            System.out.println("OK - Ajuste confirmado");
            System.out.println("Estoque atual: " + caderno.getEstoqueAtual());

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        // =========================
        // CENÁRIO 3 - ERRO: SEM NOTA FISCAL
        // =========================
        System.out.println("\n--- CENARIO 3: ERRO NF ---");

        try {
            service.confirmarMovimentacao(
                    2001,
                    TipoMovimentacao.ENTRADA,
                    10,
                    LocalDate.now(),
                    null,
                    null
            );

        } catch (Exception e) {
            System.out.println("OK (esperado) - " + e.getMessage());
        }

        // =========================
        // CENÁRIO 4 - ERRO: SEM MOTIVO
        // =========================
        System.out.println("\n--- CENARIO 4: ERRO MOTIVO ---");

        try {
            service.confirmarMovimentacao(
                    2001,
                    TipoMovimentacao.AJUSTE,
                    10,
                    LocalDate.now(),
                    null,
                    null
            );

        } catch (Exception e) {
            System.out.println("OK (esperado) - " + e.getMessage());
        }

        // =========================
        // CENÁRIO 5 - ERRO: ESTOQUE NEGATIVO
        // =========================
        System.out.println("\n--- CENARIO 5: ESTOQUE NEGATIVO ---");

        try {
            service.confirmarMovimentacao(
                    2001,
                    TipoMovimentacao.AJUSTE,
                    10000,
                    LocalDate.now(),
                    "Erro inventario",
                    null
            );

        } catch (Exception e) {
            System.out.println("OK (esperado) - " + e.getMessage());
        }

        // =========================
        // HISTÓRICO
        // =========================
        System.out.println("\n--- HISTORICO DE MOVIMENTACOES ---");

        for (MovimentacaoEstoque m : movRepo.listar()) {
            System.out.println(m);
        }

        System.out.println("\n=== FIM TESTES US08 ===");
    }
}