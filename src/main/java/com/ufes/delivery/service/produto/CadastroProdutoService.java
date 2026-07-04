package com.ufes.delivery.service.produto;

import com.ufes.delivery.model.Produto;
import com.ufes.delivery.repository.IProdutoRepository;

public class CadastroProdutoService {

    private final IProdutoRepository produtoRepository;

    public CadastroProdutoService(IProdutoRepository produtoRepository) {

        if (produtoRepository == null) {
            throw new IllegalArgumentException(
                    "Repositorio de produtos não pode ser nulo"
            );
        }

        this.produtoRepository = produtoRepository;
    }

    public void cadastrar(Produto produto) {

        validarProduto(produto);

        if (produtoRepository.buscarPorCodigo(produto.getCodigo()).isPresent()) {
            throw new IllegalArgumentException("Código já está em uso");
        }

        produtoRepository.adicionar(produto);
    }

    private void validarProduto(Produto produto) {

        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }

        validarCodigo(produto.getCodigo());
        validarNome(produto.getNome());
        validarPreco(produto.getPrecoUnitario());
        validarEstoque(produto.getEstoqueAtual());
        validarCategoria(produto);
    }

    private void validarCodigo(int codigo) {

        if (codigo <= 0) {
            throw new IllegalArgumentException("Código deve ser positivo");
        }
    }

    private void validarNome(String nome) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        String n = nome.trim();

        if (n.length() < 2 || n.length() > 120) {
            throw new IllegalArgumentException("Nome inválido");
        }
    }

    private void validarPreco(double preco) {

        if (preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }

        String precoStr = String.valueOf(preco);

        if (precoStr.contains(".")) {
            int casas = precoStr.split("\\.")[1].length();
            if (casas > 2) {
                throw new IllegalArgumentException("Preço pode ter no máximo 2 casas decimais");
            }
        }
    }

    private void validarEstoque(int estoque) {

        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
    }

    private void validarCategoria(Produto produto) {

        if (produto.getCategoria() == null) {
            throw new IllegalArgumentException("Categoria é obrigatória");
        }
    }
}