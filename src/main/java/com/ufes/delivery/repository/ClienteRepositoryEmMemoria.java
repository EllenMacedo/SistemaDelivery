package com.ufes.delivery.repository;

import com.ufes.delivery.model.Cliente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ClienteRepositoryEmMemoria implements IClienteRepository {

    private Map<String, Cliente> clientes = new HashMap<>();

    public ClienteRepositoryEmMemoria() {

        Cliente cliente1 = new Cliente(
                "Joao Silva",
                "12345678909",
                "COMUM",
                0
        );

        Cliente cliente2 = new Cliente(
                "Maria Souza",
                "98765432100",
                "PREMIUM",
                10
        );

        clientes.put(cliente1.getCpf(), cliente1);
        clientes.put(cliente2.getCpf(), cliente2);
    }


    @Override
    public Optional<Cliente> buscarClientePorCpf(String cpf) {

        if (cpf == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(
                clientes.get(cpf.replaceAll("\\D", ""))
        );
    }


    @Override
    public List<Cliente> buscarClientesPorNome(String nome) {

        List<Cliente> resultado = new ArrayList<>();

        if (nome == null) {
            return resultado;
        }

        String filtro = nome.toLowerCase();

        for (Cliente cliente : clientes.values()) {

            if (cliente.getNome()
                    .toLowerCase()
                    .contains(filtro)) {

                resultado.add(cliente);
            }
        }

        return resultado;
    }


    @Override
    public List<Cliente> listarClientes() {

        return new ArrayList<>(clientes.values());
    }


    @Override
    public void adicionarCliente(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "Cliente não pode ser nulo"
            );
        }


        if (clientes.containsKey(cliente.getCpf())) {

            throw new IllegalArgumentException(
                    "CPF já cadastrado"
            );
        }


        clientes.put(cliente.getCpf(), cliente);
    }


    @Override
    public void atualizarCliente(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "Cliente não pode ser nulo"
            );
        }


        if (!clientes.containsKey(cliente.getCpf())) {

            throw new IllegalArgumentException(
                    "Cliente não encontrado"
            );
        }


        clientes.put(cliente.getCpf(), cliente);
    }


    @Override
    public void removerCliente(String cpf) {

        if (cpf == null || cpf.isBlank()) {

            throw new IllegalArgumentException(
                    "CPF inválido"
            );
        }


        clientes.remove(cpf.replaceAll("\\D", ""));
    }
}