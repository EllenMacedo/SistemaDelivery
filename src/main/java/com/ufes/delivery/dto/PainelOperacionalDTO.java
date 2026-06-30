package com.ufes.delivery.dto;

import com.ufes.delivery.model.Pedido;
import java.time.LocalDate;
import java.util.List;

public class PainelOperacionalDTO {

    private LocalDate dataOperacao;
    private List<Pedido> pedidos;

    private long pedidosDoDia;
    private long novos;
    private long aguardandoPagamento;
    private long emPreparo;
    private long aguardandoEntrega;
    private long emTransito;
    private long entreguesHoje;


    public PainelOperacionalDTO(
            LocalDate dataOperacao,
            List<Pedido> pedidos,
            long pedidosDoDia,
            long novos,
            long aguardandoPagamento,
            long emPreparo,
            long aguardandoEntrega,
            long emTransito,
            long entreguesHoje) {

        this.dataOperacao = dataOperacao;
        this.pedidos = pedidos;
        this.pedidosDoDia = pedidosDoDia;
        this.novos = novos;
        this.aguardandoPagamento = aguardandoPagamento;
        this.emPreparo = emPreparo;
        this.aguardandoEntrega = aguardandoEntrega;
        this.emTransito = emTransito;
        this.entreguesHoje = entreguesHoje;
    }


    public LocalDate getDataOperacao() {
        return dataOperacao;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public long getPedidosDoDia() {
        return pedidosDoDia;
    }

    public long getNovos() {
        return novos;
    }

    public long getAguardandoPagamento() {
        return aguardandoPagamento;
    }

    public long getEmPreparo() {
        return emPreparo;
    }

    public long getAguardandoEntrega() {
        return aguardandoEntrega;
    }

    public long getEmTransito() {
        return emTransito;
    }

    public long getEntreguesHoje() {
        return entreguesHoje;
    }
}