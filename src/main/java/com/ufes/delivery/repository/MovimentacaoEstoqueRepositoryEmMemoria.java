package com.ufes.delivery.repository;

import com.ufes.delivery.model.MovimentacaoEstoque;

import java.util.ArrayList;
import java.util.List;

public class MovimentacaoEstoqueRepositoryEmMemoria
        implements IMovimentacaoEstoqueRepository {

    private final List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();

    @Override
    public void salvar(MovimentacaoEstoque movimentacao) {

        if (movimentacao == null) {
            throw new IllegalArgumentException("Movimentação não pode ser nula");
        }

        movimentacoes.add(movimentacao);
    }

    @Override
    public List<MovimentacaoEstoque> listar() {
        return new ArrayList<>(movimentacoes);
    }

    @Override
    public List<MovimentacaoEstoque> buscarPorProduto(int codigoProduto) {

        List<MovimentacaoEstoque> resultado = new ArrayList<>();

        for (MovimentacaoEstoque m : movimentacoes) {

            if (m.getProduto().getCodigo() == codigoProduto) {
                resultado.add(m);
            }
        }

        return resultado;
    }
}