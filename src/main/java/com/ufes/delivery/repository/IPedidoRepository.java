package com.ufes.delivery.repository;

import com.ufes.delivery.model.Pedido;
import java.util.Optional;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IPedidoRepository {

    void adicionarPedido(Pedido pedido);

    List<Pedido> listarTodos();

    List<Pedido> buscarPorData(LocalDate data);

    Optional<Pedido> buscarPorNumero(int numeroPedido);
}
