package com.ufes.delivery.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nome;
    private String cpf;

    // Mantidos por compatibilidade com as US anteriores
    private String tipo;
    private double fidelidade;

    // Novo para US05/US06
    private List<Endereco> enderecos;

    public Cliente(String nome,
                   String cpf,
                   String tipo,
                   double fidelidade) {

        validarTextoObrigatorio(nome, "Nome do cliente não pode ser vazio");
        validarTextoObrigatorio(cpf, "CPF do cliente não pode ser vazio");
        validarTextoObrigatorio(tipo, "Tipo do cliente não pode ser vazio");

        if (fidelidade < 0) {
            throw new IllegalArgumentException("Fidelidade do cliente não pode ser negativa");
        }

        this.nome = nome.trim();
        this.cpf = cpf.replaceAll("\\D", "");
        this.tipo = tipo.trim();
        this.fidelidade = fidelidade;
        this.enderecos = new ArrayList<>();
    }

    public Cliente(String nome,
               String tipo,
               double fidelidade,
               String logradouro,
               String bairro,
               String cidade) {


    this(nome, "00000000000", tipo, fidelidade);


    Endereco endereco = new Endereco(
            logradouro,
            "S/N",
            "",
            bairro,
            cidade,
            "ES",
            "00000000",
            true
    );


    this.enderecos.add(endereco);
}

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTipo() {
        return tipo;
    }

    public double getFidelidade() {
        return fidelidade;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public String getBairro() {

    for (Endereco endereco : enderecos) {

        if (endereco.isPadrao()) {
            return endereco.getBairro();
        }
    }

    return null;
    }

    public String getLogradouro() {

    for (Endereco endereco : enderecos) {

        if (endereco.isPadrao()) {
            return endereco.getLogradouro();
        }
    }

    return null;
}


public String getCidade() {

    for (Endereco endereco : enderecos) {

        if (endereco.isPadrao()) {
            return endereco.getCidade();
        }
    }

    return null;
}

    public void setFidelidade(double fidelidade) {
        if (fidelidade < 0) {
            throw new IllegalArgumentException("Fidelidade do cliente não pode ser negativa");
        }

        this.fidelidade = fidelidade;
    }

    public void adicionarEndereco(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo");
        }

        if (enderecos.size() >= 3) {
            throw new IllegalArgumentException("Cliente pode possuir no máximo três endereços");
        }

        enderecos.add(endereco);
    }

    private void validarTextoObrigatorio(String valor, String mensagem) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    public void removerEndereco(int indice) {

    if (indice < 0 || indice >= enderecos.size()) {
        throw new IllegalArgumentException(
                "Endereço selecionado inválido"
        );
    }


    if (enderecos.get(indice).isPadrao()) {

        throw new IllegalArgumentException(
                "Não é possível remover o endereço padrão"
        );
    }


    enderecos.remove(indice);
}



public void definirEnderecoPadrao(int indice) {

    if (indice < 0 || indice >= enderecos.size()) {

        throw new IllegalArgumentException(
                "Endereço selecionado inválido"
        );
    }


    for (Endereco endereco : enderecos) {

        endereco.setPadrao(false);
    }


    enderecos.get(indice).setPadrao(true);
}



public void editarEndereco(int indice, Endereco novoEndereco) {


    if (indice < 0 || indice >= enderecos.size()) {

        throw new IllegalArgumentException(
                "Endereço selecionado inválido"
        );
    }


    if (novoEndereco == null) {

        throw new IllegalArgumentException(
                "Novo endereço não pode ser nulo"
        );
    }


    enderecos.set(indice, novoEndereco);
}

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fidelidade=" + fidelidade +
                ", enderecos=" + enderecos +
                '}';
    }
}
