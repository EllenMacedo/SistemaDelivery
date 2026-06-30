package com.ufes.delivery;

import com.ufes.delivery.repository.PedidoRepositoryEmMemoria;
import com.ufes.delivery.service.operacao.PainelOperacionalService;
import com.ufes.delivery.dto.PainelOperacionalDTO;

import java.time.LocalDate;

public class TestePainel {//CLASSE SERVE PRA TESTAR US04, ESTA FUNCIONANDO

    public static void main(String[] args) {


        PedidoRepositoryEmMemoria repository =
                new PedidoRepositoryEmMemoria();


        PainelOperacionalService service =
                new PainelOperacionalService(repository);


        PainelOperacionalDTO painel =
                service.carregarPainel(LocalDate.of(2026, 6, 20));


        System.out.println("Data operação: "
                + painel.getDataOperacao());

        System.out.println("Pedidos do dia: "
                + painel.getPedidosDoDia());

        System.out.println("Novos: "
                + painel.getNovos());

        System.out.println("Aguardando pagamento: "
                + painel.getAguardandoPagamento());

        System.out.println("Em preparo: "
                + painel.getEmPreparo());

        System.out.println("Aguardando entrega: "
                + painel.getAguardandoEntrega());

        System.out.println("Em trânsito: "
                + painel.getEmTransito());

        System.out.println("Entregues hoje: "
                + painel.getEntreguesHoje());

    }
}