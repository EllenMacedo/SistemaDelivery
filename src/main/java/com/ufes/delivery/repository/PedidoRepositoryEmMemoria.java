package com.ufes.delivery.repository;

import com.ufes.delivery.model.Pedido;
import com.ufes.delivery.model.EstadoPedido;
import com.ufes.delivery.model.Cliente;
import com.ufes.delivery.model.Item;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class PedidoRepositoryEmMemoria {

    private Map<Integer, Pedido> pedidos = new HashMap<>();

    public PedidoRepositoryEmMemoria() {

        Cliente c1 = new Cliente("Rosa", "Ouro", 1, "Limoeiro", "Cidade Maravilhosa", "Castelo");
        Cliente c2 = new Cliente("Joao", "Ouro", 1, "Luzes", "Cidade Espetacular", "Fortaleza");
        Cliente c3 = new Cliente("Maria", "Prata", 1, "Colibris", "Cidade Incrível", "Forte");

        // Pedido 1001 - NOVO
        Pedido p1 = new Pedido(LocalDateTime.of(2026, 6, 20, 10, 0), c1, 1001);
        p1.adicionarItem(new Item("Hamburguer", 2, 25.0, "COMIDA"));

        // Pedido 1002 - EM PREPARO
        Pedido p2 = new Pedido(LocalDateTime.of(2026, 6, 20, 11, 0), c2, 1002);
        p2.adicionarItem(new Item("Pizza", 1, 60.0, "COMIDA"));
        p2.setEstadoPedido(EstadoPedido.EM_PREPARO);

        // Pedido 1003 - ENTREGUE
        Pedido p3 = new Pedido(LocalDateTime.of(2026, 6, 20, 12, 0), c3, 1003);
        p3.adicionarItem(new Item("Sushi", 1, 80.0, "COMIDA"));
        p3.setEstadoPedido(EstadoPedido.ENTREGUE);

        // Pedido 1004 - AGUARDANDO PAGAMENTO
        Pedido p4 = new Pedido(LocalDateTime.of(2026, 6, 20, 13, 0), c1, 1004);
        p4.adicionarItem(new Item("Coca Cola", 3, 8.0, "BEBIDA"));
        p4.setEstadoPedido(EstadoPedido.AGUARDANDO_PAGAMENTO);

        // Pedido 1005 - EM TRANSITO
        Pedido p5 = new Pedido(LocalDateTime.of(2026, 6, 20, 14, 0), c2, 1005);
        p5.adicionarItem(new Item("Lasanha", 1, 45.0, "COMIDA"));
        p5.setEstadoPedido(EstadoPedido.EM_TRANSITO);

        pedidos.put(1001, p1);
        pedidos.put(1002, p2);
        pedidos.put(1003, p3);
        pedidos.put(1004, p4);
        pedidos.put(1005, p5);
    }

    public Optional<Pedido> buscarPorNumero(int numeroPedido) {
        return Optional.ofNullable(pedidos.get(numeroPedido));
    }

    public List<Pedido> listarTodos() {
        return new ArrayList<>(pedidos.values());
    }
}