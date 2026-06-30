package com.ufes.delivery.service.operacao;

import com.ufes.delivery.dto.PainelOperacionalDTO;
import com.ufes.delivery.model.EstadoPedido;
import com.ufes.delivery.model.Pedido;
import com.ufes.delivery.repository.IPedidoRepository;

import java.time.LocalDate;
import java.util.List;

public class PainelOperacionalService {

    private final IPedidoRepository pedidoRepository;


    public PainelOperacionalService(IPedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }


    public PainelOperacionalDTO carregarPainel(LocalDate dataOperacao) {

        List<Pedido> pedidosDoDia =
                pedidoRepository.buscarPorData(dataOperacao);


        long pedidosTotal = pedidosDoDia.size();

        long novos =
                contarPorEstado(pedidosDoDia, EstadoPedido.NOVO);

        long aguardandoPagamento =
                contarPorEstado(pedidosDoDia, EstadoPedido.AGUARDANDO_PAGAMENTO);

        long emPreparo =
                contarPorEstado(pedidosDoDia, EstadoPedido.EM_PREPARO);

        long aguardandoEntrega =
                contarPorEstado(pedidosDoDia, EstadoPedido.AGUARDANDO_ENTREGA);

        long emTransito =
                contarPorEstado(pedidosDoDia, EstadoPedido.EM_TRANSITO);

        long entreguesHoje =
                contarPorEstado(pedidosDoDia, EstadoPedido.ENTREGUE);


        return new PainelOperacionalDTO(
                dataOperacao,
                pedidosDoDia,
                pedidosTotal,
                novos,
                aguardandoPagamento,
                emPreparo,
                aguardandoEntrega,
                emTransito,
                entreguesHoje
        );
    }


    private long contarPorEstado(
            List<Pedido> pedidos,
            EstadoPedido estado) {


        return pedidos.stream()
                .filter(p -> p.getEstadoPedido() == estado)
                .count();
    }
}