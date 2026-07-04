package com.ufes.delivery;

import com.ufes.delivery.model.CategoriaProduto;
import com.ufes.delivery.model.Produto;
import com.ufes.delivery.repository.IProdutoRepository;
import com.ufes.delivery.repository.ProdutoRepositoryEmMemoria;
import com.ufes.delivery.service.produto.BuscarProdutoService;
import com.ufes.delivery.service.produto.CadastroProdutoService;

public class MainUS07 {

    public static void main(String[] args) {

        IProdutoRepository repo = new ProdutoRepositoryEmMemoria();

        CadastroProdutoService cadastroService =
                new CadastroProdutoService(repo);

        BuscarProdutoService buscarService =
                new BuscarProdutoService(repo);

        System.out.println("=== INICIO TESTES US07 ===\n");

        // =========================
        // 1. CADASTRO VÁLIDO
        // =========================
        try {
            Produto p1 = new Produto(
                    2001,
                    "Caderno Universitario",
                    CategoriaProduto.PAPELARIA,
                    35.90,
                    120
            );

            cadastroService.cadastrar(p1);

            System.out.println("OK - Produto cadastrado com sucesso");
        } catch (Exception e) {
            System.out.println("ERRO inesperado: " + e.getMessage());
        }

        // =========================
        // 2. CÓDIGO DUPLICADO
        // =========================
        try {
            Produto p2 = new Produto(
                    2001,
                    "Caneta Azul",
                    CategoriaProduto.PAPELARIA,
                    2.50,
                    500
            );

            cadastroService.cadastrar(p2);

            System.out.println("ERRO - deveria ter falhado código duplicado");

        } catch (Exception e) {
            System.out.println("OK (esperado) - " + e.getMessage());
        }

        // =========================
        // 3. PREÇO INVÁLIDO
        // =========================
        try {
            Produto p3 = new Produto(
                    2002,
                    "Produto Invalido",
                    CategoriaProduto.PAPELARIA,
                    0,
                    10
            );

            cadastroService.cadastrar(p3);

            System.out.println("ERRO - deveria ter falhado preço");

        } catch (Exception e) {
            System.out.println("OK (esperado) - " + e.getMessage());
        }

        // =========================
        // 4. ESTOQUE INVÁLIDO
        // =========================
        try {
            Produto p4 = new Produto(
                    2003,
                    "Produto Estoque Invalido",
                    CategoriaProduto.PAPELARIA,
                    10.0,
                    -5
            );

            cadastroService.cadastrar(p4);

            System.out.println("ERRO - deveria ter falhado estoque");

        } catch (Exception e) {
            System.out.println("OK (esperado) - " + e.getMessage());
        }

        // =========================
        // 5. BUSCA POR NOME (CASE INSENSITIVE)
        // =========================
        System.out.println("\n--- Busca por nome 'caderno' ---");

        var resultadoNome = buscarService.buscarPorNome("caderno");

        for (Produto p : resultadoNome) {
            System.out.println(p);
        }

        // =========================
        // 6. BUSCA POR CATEGORIA
        // =========================
        System.out.println("\n--- Busca por categoria PAPELARIA ---");

        var resultadoCat = buscarService.buscarPorCategoria(
                CategoriaProduto.PAPELARIA
        );

        for (Produto p : resultadoCat) {
            System.out.println(p);
        }

        // =========================
        // 7. BUSCA POR CÓDIGO
        // =========================
        System.out.println("\n--- Busca por código 2001 ---");

        var produto = buscarService.buscarPorCodigo(2001);

        System.out.println(produto);

        System.out.println("\n=== FIM TESTES US07 ===");
    }
}