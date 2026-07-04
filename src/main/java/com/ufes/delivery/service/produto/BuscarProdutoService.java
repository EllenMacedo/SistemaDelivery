package com.ufes.delivery.service.produto;

import com.ufes.delivery.model.CategoriaProduto;
import com.ufes.delivery.model.Produto;
import com.ufes.delivery.repository.IProdutoRepository;

import java.util.List;
import java.util.Optional;

public class BuscarProdutoService {

    private final IProdutoRepository produtoRepository;

    public BuscarProdutoService(IProdutoRepository produtoRepository) {

        if (produtoRepository == null) {
            throw new IllegalArgumentException(
                    "Repositorio de produtos não pode ser nulo"
            );
        }

        this.produtoRepository = produtoRepository;
    }

    public Optional<Produto> buscarPorCodigo(int codigo) {

        if (codigo <= 0) {
            throw new IllegalArgumentException("Código inválido");
        }

        return produtoRepository.buscarPorCodigo(codigo);
    }

    public List<Produto> buscarPorNome(String nome) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Valor da busca é obrigatório");
        }

        return produtoRepository.buscarPorNome(nome.trim());
    }

    public List<Produto> buscarPorCategoria(CategoriaProduto categoria) {

        if (categoria == null) {
            throw new IllegalArgumentException("Categoria é obrigatória");
        }

        return produtoRepository.buscarPorCategoria(categoria);
    }
}