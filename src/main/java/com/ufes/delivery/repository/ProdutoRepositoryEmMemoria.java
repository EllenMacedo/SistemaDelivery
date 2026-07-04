package com.ufes.delivery.repository;

import com.ufes.delivery.model.CategoriaProduto;
import com.ufes.delivery.model.Produto;

import java.util.*;

public class ProdutoRepositoryEmMemoria implements IProdutoRepository {

    private final Map<Integer, Produto> produtos = new HashMap<>();

    public ProdutoRepositoryEmMemoria() {

        // produtos de exemplo (opcional, ajuda nos testes)

        Produto p1 = new Produto(
                1001,
                "Caderno Universitario",
                CategoriaProduto.PAPELARIA,
                35.90,
                120
        );

        Produto p2 = new Produto(
                1002,
                "Caneta Azul",
                CategoriaProduto.PAPELARIA,
                2.50,
                500
        );

        produtos.put(p1.getCodigo(), p1);
        produtos.put(p2.getCodigo(), p2);
    }

    @Override
    public Optional<Produto> buscarPorCodigo(int codigo) {
        return Optional.ofNullable(produtos.get(codigo));
    }

    @Override
    public List<Produto> buscarPorNome(String nome) {

        List<Produto> resultado = new ArrayList<>();

        if (nome == null) {
            return resultado;
        }

        String filtro = nome.toLowerCase();

        for (Produto produto : produtos.values()) {
            if (produto.getNome().toLowerCase().contains(filtro)) {
                resultado.add(produto);
            }
        }

        return resultado;
    }

    @Override
    public List<Produto> buscarPorCategoria(CategoriaProduto categoria) {

        List<Produto> resultado = new ArrayList<>();

        if (categoria == null) {
            return resultado;
        }

        for (Produto produto : produtos.values()) {
            if (produto.getCategoria() == categoria) {
                resultado.add(produto);
            }
        }

        return resultado;
    }

    @Override
    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos.values());
    }

    @Override
    public void adicionar(Produto produto) {

        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }

        if (produtos.containsKey(produto.getCodigo())) {
            throw new IllegalArgumentException("Código já cadastrado");
        }

        produtos.put(produto.getCodigo(), produto);
    }

    @Override
    public void atualizar(Produto produto) {

        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }

        if (!produtos.containsKey(produto.getCodigo())) {
            throw new IllegalArgumentException("Produto não encontrado");
        }

        produtos.put(produto.getCodigo(), produto);
    }

    @Override
    public void remover(int codigo) {
        produtos.remove(codigo);
    }
}