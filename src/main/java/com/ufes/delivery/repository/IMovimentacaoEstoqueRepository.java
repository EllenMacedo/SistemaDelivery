package com.ufes.delivery.repository;

import com.ufes.delivery.model.MovimentacaoEstoque;

import java.util.List;

public interface IMovimentacaoEstoqueRepository {

    void salvar(MovimentacaoEstoque movimentacao);

    List<MovimentacaoEstoque> listar();

    List<MovimentacaoEstoque> buscarPorProduto(int codigoProduto);
}