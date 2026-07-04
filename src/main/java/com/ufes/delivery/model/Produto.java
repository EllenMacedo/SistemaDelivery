package com.ufes.delivery.model;

public class Produto {

    private int codigo;
    private String nome;
    private CategoriaProduto categoria;
    private double precoUnitario;
    private int estoqueAtual;

    public Produto(int codigo,
                   String nome,
                   CategoriaProduto categoria,
                   double precoUnitario,
                   int estoqueInicial) {

        if (codigo <= 0) {
            throw new IllegalArgumentException("Código deve ser positivo");
        }

        validarTexto(nome, "Nome do produto é obrigatório");

        if (categoria == null) {
            throw new IllegalArgumentException("Categoria é obrigatória");
        }

        if (precoUnitario <= 0) {
            throw new IllegalArgumentException("Preço unitário deve ser maior que zero");
        }

        if (estoqueInicial < 0) {
            throw new IllegalArgumentException("Estoque inicial não pode ser negativo");
        }

        this.codigo = codigo;
        this.nome = nome.trim();
        this.categoria = categoria;
        this.precoUnitario = precoUnitario;
        this.estoqueAtual = estoqueInicial;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public int getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setPrecoUnitario(double precoUnitario) {
        if (precoUnitario <= 0) {
            throw new IllegalArgumentException("Preço unitário deve ser maior que zero");
        }
        this.precoUnitario = precoUnitario;
    }

    public void setEstoqueAtual(int estoqueAtual) {
        if (estoqueAtual < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
        this.estoqueAtual = estoqueAtual;
    }

    public void reduzirEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade inválida");
        }

        if (estoqueAtual - quantidade < 0) {
            throw new IllegalArgumentException("Estoque insuficiente");
        }

        this.estoqueAtual -= quantidade;
    }

    public void aumentarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade inválida");
        }

        this.estoqueAtual += quantidade;
    }

    private void validarTexto(String valor, String mensagem) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", categoria=" + categoria +
                ", precoUnitario=" + precoUnitario +
                ", estoqueAtual=" + estoqueAtual +
                '}';
    }
}