package com.ufes.delivery.service.cliente;

import com.ufes.delivery.model.Cliente;
import com.ufes.delivery.repository.IClienteRepository;

import java.util.List;
import java.util.Optional;

public class BuscarClienteService {

    private final IClienteRepository clienteRepository;


    public BuscarClienteService(IClienteRepository clienteRepository) {

        if (clienteRepository == null) {
            throw new IllegalArgumentException(
                    "Repositorio de clientes não pode ser nulo"
            );
        }

        this.clienteRepository = clienteRepository;
    }


    public List<Cliente> buscarPorNome(String nome) {

        validarNome(nome);

        return clienteRepository.buscarClientesPorNome(nome.trim());
    }


    public Optional<Cliente> buscarPorCpf(String cpf) {

        String cpfLimpo = removerMascara(cpf);

        validarCpf(cpfLimpo);

        return clienteRepository.buscarClientePorCpf(cpfLimpo);
    }



    private void validarNome(String nome) {

        if (nome == null || nome.isBlank()) {

            throw new IllegalArgumentException(
                    "Valor da busca é obrigatório"
            );
        }


        String nomeLimpo = nome.trim();


        if (nomeLimpo.length() < 2 ||
            nomeLimpo.length() > 120) {

            throw new IllegalArgumentException(
                    "Nome para busca inválido"
            );
        }


        if (!nomeLimpo.matches("[a-zA-ZÀ-ÿ' -]+")) {

            throw new IllegalArgumentException(
                    "Nome contém caracteres inválidos"
            );
        }
    }



    private void validarCpf(String cpf) {

        if (cpf == null || cpf.isBlank()) {

            throw new IllegalArgumentException(
                    "CPF obrigatório"
            );
        }


        String cpfLimpo = cpf.replaceAll("\\D", "");

    if (cpfLimpo.length() != 11) {
    throw new IllegalArgumentException(
            "CPF inválido"
    );
}


        if (!validarDigitosCpf(cpf)) {

            throw new IllegalArgumentException(
                    "CPF inválido"
            );
        }
    }



    private String removerMascara(String cpf) {

        if (cpf == null) {
            return null;
        }

        return cpf.replaceAll("\\D", "");
    }



    private boolean validarDigitosCpf(String cpf) {


        if (cpf.chars().distinct().count() == 1) {
            return false;
        }


        int soma = 0;


        for (int i = 0; i < 9; i++) {

            soma += Character.getNumericValue(cpf.charAt(i))
                    * (10 - i);
        }


        int resto = soma % 11;

        int digito1 = resto < 2 ? 0 : 11 - resto;



        soma = 0;


        for (int i = 0; i < 10; i++) {

            soma += Character.getNumericValue(cpf.charAt(i))
                    * (11 - i);
        }


        resto = soma % 11;

        int digito2 = resto < 2 ? 0 : 11 - resto;



        return digito1 == Character.getNumericValue(cpf.charAt(9))
                &&
               digito2 == Character.getNumericValue(cpf.charAt(10));
    }
}