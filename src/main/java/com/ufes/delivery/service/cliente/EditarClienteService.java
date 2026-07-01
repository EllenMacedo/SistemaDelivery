package com.ufes.delivery.service.cliente;

import com.ufes.delivery.model.Cliente;
import com.ufes.delivery.model.Endereco;
import com.ufes.delivery.repository.IClienteRepository;

import java.util.List;

public class EditarClienteService {

    private final IClienteRepository clienteRepository;


    public EditarClienteService(IClienteRepository clienteRepository) {

        if (clienteRepository == null) {
            throw new IllegalArgumentException(
                    "Repositorio de clientes não pode ser nulo"
            );
        }

        this.clienteRepository = clienteRepository;
    }



    public void editarCliente(Cliente cliente) {


        validarCliente(cliente);


        if (clienteRepository.buscarClientePorCpf(cliente.getCpf()).isEmpty()) {

            throw new IllegalArgumentException(
                    "Cliente não encontrado"
            );
        }


        clienteRepository.atualizarCliente(cliente);
    }





    private void validarCliente(Cliente cliente) {


        if (cliente == null) {

            throw new IllegalArgumentException(
                    "Cliente não pode ser nulo"
            );
        }


        validarNome(cliente.getNome());


        validarCpf(cliente.getCpf());


        validarEnderecos(cliente.getEnderecos());
    }





    private void validarNome(String nome) {


        if (nome == null || nome.isBlank()) {

            throw new IllegalArgumentException(
                    "Nome obrigatório"
            );
        }


        String nomeLimpo = nome.trim();


        if (nomeLimpo.length() < 2 ||
            nomeLimpo.length() > 120) {

            throw new IllegalArgumentException(
                    "Nome inválido"
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
    }





    private void validarEnderecos(List<Endereco> enderecos) {


        if (enderecos == null || enderecos.isEmpty()) {

            throw new IllegalArgumentException(
                    "Cliente deve possuir pelo menos um endereço"
            );
        }


        if (enderecos.size() > 3) {

            throw new IllegalArgumentException(
                    "Cliente pode possuir no máximo três endereços"
            );
        }


        long padroes = enderecos.stream()
                .filter(Endereco::isPadrao)
                .count();


        if (padroes != 1) {

            throw new IllegalArgumentException(
                    "Deve existir exatamente um endereço padrão"
            );
        }
    }
}