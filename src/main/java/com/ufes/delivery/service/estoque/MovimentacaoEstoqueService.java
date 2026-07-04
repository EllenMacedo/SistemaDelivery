package com.ufes.delivery.service.estoque;

import com.ufes.delivery.model.*;
import com.ufes.delivery.repository.IMovimentacaoEstoqueRepository;
import com.ufes.delivery.repository.IProdutoRepository;

import java.time.LocalDate;

public class MovimentacaoEstoqueService {

    private final IProdutoRepository produtoRepository;
    private final IMovimentacaoEstoqueRepository movimentacaoRepository;

    public MovimentacaoEstoqueService(
        IProdutoRepository produtoRepository,
        IMovimentacaoEstoqueRepository movimentacaoRepository
) {

    if (produtoRepository == null || movimentacaoRepository == null) {
        throw new IllegalArgumentException(
                "Repositorios não podem ser nulos"
        );
    }

    this.produtoRepository = produtoRepository;
    this.movimentacaoRepository = movimentacaoRepository;
}

    // =========================
    // PRÉVIA
    // =========================
    public MovimentacaoEstoque preverMovimentacao(
            int codigoProduto,
            TipoMovimentacao tipo,
            int quantidade,
            LocalDate data,
            String motivo,
            String notaFiscal
    ) {

        Produto produto = buscarProduto(codigoProduto);

        validarBase(produto, tipo, quantidade, data);

        validarRegrasEspecificas(tipo, motivo, notaFiscal);

        MovimentacaoEstoque mov = new MovimentacaoEstoque(
                produto,
                tipo,
                quantidade,
                data,
                motivo,
                notaFiscal
        );

        if (mov.getEstoqueDepois() < 0) {
            throw new IllegalArgumentException(
                    "Movimentação resultaria em estoque negativo"
            );
        }

        return mov;
    }

    // =========================
    // CONFIRMAR
    // =========================
    public void confirmarMovimentacao(
        int codigoProduto,
        TipoMovimentacao tipo,
        int quantidade,
        LocalDate data,
        String motivo,
        String notaFiscal
) {

    MovimentacaoEstoque mov = preverMovimentacao(
            codigoProduto,
            tipo,
            quantidade,
            data,
            motivo,
            notaFiscal
    );

    Produto produto = mov.getProduto();

    produto.setEstoqueAtual(mov.getEstoqueDepois());

    produtoRepository.atualizar(produto);

    movimentacaoRepository.salvar(mov);
}

    // =========================
    // VALIDAÇÕES
    // =========================
    private void validarBase(
            Produto produto,
            TipoMovimentacao tipo,
            int quantidade,
            LocalDate data
    ) {

        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }

        if (tipo == null) {
            throw new IllegalArgumentException("Tipo é obrigatório");
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        if (data == null) {
            throw new IllegalArgumentException("Data é obrigatória");
        }

        if (data.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data não pode ser futura");
        }
    }

    private void validarRegrasEspecificas(
            TipoMovimentacao tipo,
            String motivo,
            String notaFiscal
    ) {

        if (tipo == TipoMovimentacao.AJUSTE) {

            if (motivo == null || motivo.isBlank()) {
                throw new IllegalArgumentException("Motivo do ajuste é obrigatório");
            }
        }

        if (tipo == TipoMovimentacao.ENTRADA) {

            if (notaFiscal == null || notaFiscal.isBlank()) {
                throw new IllegalArgumentException("Nota fiscal é obrigatória");
            }
        }
    }

    private Produto buscarProduto(int codigo) {

        return produtoRepository.buscarPorCodigo(codigo)
                .orElseThrow(() ->
                        new IllegalArgumentException("Produto não encontrado")
                );
    }
}