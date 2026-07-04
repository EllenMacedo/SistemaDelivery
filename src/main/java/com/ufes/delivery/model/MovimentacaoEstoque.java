package com.ufes.delivery.model;

import java.time.LocalDate;

public class MovimentacaoEstoque {

    private Produto produto;
    private TipoMovimentacao tipo;
    private int quantidade;
    private LocalDate data;
    private String motivoAjuste;
    private String notaFiscal;
    private int estoqueAntes;
    private int estoqueDepois;

    public MovimentacaoEstoque(Produto produto,
                              TipoMovimentacao tipo,
                              int quantidade,
                              LocalDate data,
                              String motivoAjuste,
                              String notaFiscal) {

        if (produto == null) {
            throw new IllegalArgumentException("Produto é obrigatório");
        }

        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de movimentação é obrigatório");
        }

        if (quantidade == 0) {
            throw new IllegalArgumentException("Quantidade deve ser diferente de zero");
        }

        if (data == null) {
            throw new IllegalArgumentException("Data é obrigatória");
        }

        this.produto = produto;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.data = data;
        this.motivoAjuste = motivoAjuste;
        this.notaFiscal = notaFiscal;

        this.estoqueAntes = produto.getEstoqueAtual();

        calcularEstoqueDepois();
    }

    private void calcularEstoqueDepois() {

        int atual = produto.getEstoqueAtual();

        if (tipo == TipoMovimentacao.ENTRADA) {
            this.estoqueDepois = atual + quantidade;
        } else {
            this.estoqueDepois = atual - quantidade;
        }
    }

    public Produto getProduto() {
        return produto;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public String getMotivoAjuste() {
        return motivoAjuste;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public int getEstoqueAntes() {
        return estoqueAntes;
    }

    public int getEstoqueDepois() {
        return estoqueDepois;
    }

    @Override
    public String toString() {
        return "MovimentacaoEstoque{" +
                "produto=" + produto.getNome() +
                ", tipo=" + tipo +
                ", quantidade=" + quantidade +
                ", data=" + data +
                ", estoqueAntes=" + estoqueAntes +
                ", estoqueDepois=" + estoqueDepois +
                ", motivo='" + motivoAjuste + '\'' +
                ", notaFiscal='" + notaFiscal + '\'' +
                '}';
    }
}