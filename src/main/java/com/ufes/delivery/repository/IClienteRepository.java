package com.ufes.delivery.repository;

import com.ufes.delivery.model.Cliente;
import java.util.Optional;
import java.util.List;

public interface IClienteRepository {

    Optional<Cliente> buscarClientePorCpf(String cpf);

    List<Cliente> buscarClientesPorNome(String nome);

    List<Cliente> listarClientes();

    void adicionarCliente(Cliente cliente);

    void atualizarCliente(Cliente cliente);

    void removerCliente(String cpf);
}