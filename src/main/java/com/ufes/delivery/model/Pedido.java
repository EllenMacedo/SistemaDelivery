package com.ufes.delivery.model;

import com.ufes.delivery.configuracao.ConfiguracaoService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Optional;

public class Pedido {
    private double taxaEntrega = ConfiguracaoService.getTaxaEntregaPadrao();
    private List<Item> itens = new ArrayList<>();
    private Cliente cliente;
    private List<CupomDescontoEntrega> cuponsDescontoEntrega = new ArrayList<>();
    private LocalDateTime data;
    //alteracao kimi trabalho
    private int numeroPedido;
    private EstadoPedido estadoPedido;
    private LocalDateTime dataConclusao;
    //fim

    private CupomDescontoPedido cupomPedidoAplicado;

    public Pedido(LocalDateTime data, Cliente cliente, int numeroPedido) {
        if (data == null) {
            throw new IllegalArgumentException("Data do pedido deve ser informada");
        }

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente do pedido deve ser informado");
        }

        this.cliente = cliente;
        this.data = data;
        //alteracoes
        this.numeroPedido=numeroPedido;
        this.estadoPedido=EstadoPedido.NOVO;
        this.dataConclusao=null;
    }

    public void adicionarItem(Item objeto) {
        if (objeto == null) {
            throw new IllegalArgumentException("Item do pedido deve ser informado");
        }

        itens.add(objeto);
    }

    public double getValorPedido() {
        double valor = 0;
        for (Item item : itens) {
            valor += item.valorTotal();
        }
        return valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public double getTaxaEntrega() {
        return taxaEntrega;
    }

    public double getTotalDescontosTaxaEntrega() {
        double desconto = 0;

        for (CupomDescontoEntrega cupom : cuponsDescontoEntrega) {
            desconto += cupom.getValorDesconto();
        }

        return desconto;
    }

    public List<CupomDescontoEntrega> getCupomDescontoEntrega() {
        return Collections.unmodifiableList(cuponsDescontoEntrega);
    }

    public void limparCuponsDescontoEntrega() {
        cuponsDescontoEntrega.clear();
    }

    public void adicionarCupomDescontoEntrega(CupomDescontoEntrega cupom) {
        if (cupom == null) {
            throw new IllegalArgumentException("Cupom de desconto da entrega deve ser informado");
        }

        double totalDescontosAposAdicionar = getTotalDescontosTaxaEntrega() + cupom.getValorDesconto();
        double limiteAplicavel = taxaEntrega;

        if (cupom.getValorDesconto() < 0) {
            throw new IllegalArgumentException("Desconto na taxa de entrega nao pode ser negativo");
        }

        if (totalDescontosAposAdicionar > limiteAplicavel) {
            throw new IllegalStateException(
                    "Desconto total na taxa de entrega nao pode ultrapassar " + limiteAplicavel);
        }

        cuponsDescontoEntrega.add(cupom);
    }

    public double getTaxaEntregaComDesconto() {
        double taxaComDesconto = taxaEntrega - getTotalDescontosTaxaEntrega();
        if (taxaComDesconto < 0) {
            return 0;
        }
        return taxaComDesconto;
    }

    public double calcularValorTotal() {
        double valorTotal = getValorPedido() + getTaxaEntregaComDesconto();
        Optional<CupomDescontoPedido> cupomAplicado = getCupomAplicado();

        if (cupomAplicado.isPresent()) {
            CupomDescontoPedido cupom = cupomAplicado.get();
            return valorTotal - valorTotal * cupom.getPercentual() / 100;
        }

        return valorTotal;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Optional<CupomDescontoPedido> getCupomAplicado() {
        return Optional.ofNullable(cupomPedidoAplicado);
    }

    public void setCupomAplicado(CupomDescontoPedido cupomPedidoAplicado) {
        if (cupomPedidoAplicado == null) {
            throw new IllegalArgumentException("Cupom do pedido deve ser informado");
        }

        this.cupomPedidoAplicado = cupomPedidoAplicado;
    }
    
    //alteracoes do kimi pro trabalho
    
    public int getNumeroPedido()
    {
        return numeroPedido;
    }
    
    public EstadoPedido getEstadoPedido()
    {
        return estadoPedido;
    }
    
    public void setEstadoPedido(EstadoPedido novoEstado) {

    if (novoEstado == null) {
        throw new IllegalArgumentException("Estado do pedido nao pode ser nulo");
    }

    if (this.estadoPedido == null) {
        throw new IllegalStateException("Estado atual do pedido invalido");
    }

    // regra de nao regressao simples (fluxo linear)
    if (novoEstado.ordinal() < this.estadoPedido.ordinal()) {
        throw new IllegalArgumentException("Nao e permitido retroceder o estado do pedido");
    }

    // valida transicoes principais (regra da US04)
    switch (this.estadoPedido) {

        case NOVO:
            if (novoEstado != EstadoPedido.AGUARDANDO_PAGAMENTO &&
                novoEstado != EstadoPedido.CANCELADO) {
                throw new IllegalArgumentException("Transicao invalida a partir de NOVO");
            }
            break;

        case AGUARDANDO_PAGAMENTO:
            if (novoEstado != EstadoPedido.EM_PREPARO &&
                novoEstado != EstadoPedido.CANCELADO) {
                throw new IllegalArgumentException("Transicao invalida a partir de AGUARDANDO_PAGAMENTO");
            }
            break;

        case EM_PREPARO:
            if (novoEstado != EstadoPedido.AGUARDANDO_ENTREGA &&
                novoEstado != EstadoPedido.CANCELADO) {
                throw new IllegalArgumentException("Transicao invalida a partir de EM_PREPARO");
            }
            break;

        case AGUARDANDO_ENTREGA:
            if (novoEstado != EstadoPedido.EM_TRANSITO &&
                novoEstado != EstadoPedido.CANCELADO) {
                throw new IllegalArgumentException("Transicao invalida a partir de AGUARDANDO_ENTREGA");
            }
            break;

        case EM_TRANSITO:
            if (novoEstado != EstadoPedido.ENTREGUE) {
                throw new IllegalArgumentException("Transicao invalida a partir de EM_TRANSITO");
            }
            break;

        case ENTREGUE:
            throw new IllegalArgumentException("Pedido ja foi entregue e nao pode ser alterado");
    }

    this.estadoPedido = novoEstado;

    // regra de conclusao
    if (novoEstado == EstadoPedido.ENTREGUE) {
        this.dataConclusao = LocalDateTime.now();
    }
}
    
    public LocalDateTime getDataConclusao()
    {
        return dataConclusao;
    }
    //funcao perigosa abaixo
    /*
    public void setDataConclusao(LocalDateTime dataConclusao)
    {
        this.dataConclusao=dataConclusao;
    }
    */
    @Override
    public String toString() {
        return "Pedido{"
                + "data=" + data
                + ", cliente=" + cliente
                + ", itens=" + itens
                + ", taxaEntrega=" + taxaEntrega
                + ", cuponsDescontoEntrega=" + cuponsDescontoEntrega
                + ", cupomPedidoAplicado=" + cupomPedidoAplicado
                + ", valorPedido=" + getValorPedido()
                + ", totalDescontosTaxaEntrega=" + getTotalDescontosTaxaEntrega()
                + ", valorTotal=" + calcularValorTotal()
                + "}";
    }
}
