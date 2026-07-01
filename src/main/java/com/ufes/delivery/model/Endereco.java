package com.ufes.delivery.model;

public class Endereco {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private boolean padrao;

    public Endereco(String logradouro,
                     String numero,
                     String complemento,
                     String bairro,
                     String cidade,
                     String uf,
                     String cep,
                     boolean padrao) {

        validarTextoObrigatorio(logradouro, "Logradouro obrigatório");
        validarTextoObrigatorio(numero, "Número obrigatório");
        validarTextoObrigatorio(bairro, "Bairro obrigatório");
        validarTextoObrigatorio(cidade, "Cidade obrigatória");

        if (uf == null || !uf.matches("[A-Za-z]{2}")) {
            throw new IllegalArgumentException("UF inválida");
        }

        String cepLimpo = cep.replaceAll("\\D", "");

        if (cepLimpo.length() != 8) {
            throw new IllegalArgumentException("CEP inválido");
        }

        this.logradouro = logradouro.trim();
        this.numero = numero.trim();
        this.complemento = complemento == null ? "" : complemento.trim();
        this.bairro = bairro.trim();
        this.cidade = cidade.trim();
        this.uf = uf.toUpperCase();
        this.cep = cepLimpo;
        this.padrao = padrao;
    }

    private void validarTextoObrigatorio(String valor, String mensagem) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getCep() {
        return cep;
    }

    public boolean isPadrao() {
        return padrao;
    }

    public void setPadrao(boolean padrao) {
        this.padrao = padrao;
    }

    @Override
public String toString() {
    return "Endereco{" +
            "logradouro='" + logradouro + '\'' +
            ", numero='" + numero + '\'' +
            ", complemento='" + complemento + '\'' +
            ", bairro='" + bairro + '\'' +
            ", cidade='" + cidade + '\'' +
            ", uf='" + uf + '\'' +
            ", cep='" + cep + '\'' +
            ", padrao=" + padrao +
            '}';
}

}