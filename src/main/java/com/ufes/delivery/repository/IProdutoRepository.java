package com.ufes.delivery.repository;

import com.ufes.delivery.model.Produto;
import com.ufes.delivery.model.CategoriaProduto;

import java.util.List;
import java.util.Optional;

public interface IProdutoRepository {

    Optional<Produto> buscarPorCodigo(int codigo);

    List<Produto> buscarPorNome(String nome);

    List<Produto> buscarPorCategoria(CategoriaProduto categoria);

    List<Produto> listarTodos();

    void adicionar(Produto produto);

    void atualizar(Produto produto);

    void remover(int codigo);
}