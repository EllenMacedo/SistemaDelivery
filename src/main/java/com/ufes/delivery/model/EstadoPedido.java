package com.ufes.delivery.model;

public enum EstadoPedido{
    NOVO,
    AGUARDANDO_PAGAMENTO,
    EM_PREPARO,
    AGUARDANDO_ENTREGA,
    EM_TRANSITO,
    ENTREGUE,
    CANCELADO
}