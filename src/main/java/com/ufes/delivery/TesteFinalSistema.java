package com.ufes.delivery;

import com.ufes.delivery.model.Cliente;
import com.ufes.delivery.model.Endereco;
import com.ufes.delivery.model.Sessao;
import com.ufes.delivery.model.Usuario;
import com.ufes.delivery.dto.PainelOperacionalDTO;
import com.ufes.delivery.repository.PedidoRepositoryEmMemoria;
import com.ufes.delivery.service.operacao.PainelOperacionalService;
import java.time.LocalDate;

import com.ufes.delivery.repository.ClienteRepositoryEmMemoria;
import com.ufes.delivery.repository.UsuarioRepositoryEmMemoria;

import com.ufes.delivery.service.autenticacao.AutenticacaoService;
import com.ufes.delivery.service.cliente.CadastroClienteService;
import com.ufes.delivery.service.cliente.EditarClienteService;

public class TesteFinalSistema {//classe temporaria para testar a implementacao de US05 e US06


    public static void main(String[] args) {


        System.out.println("===== TESTE FINAL SISTEMA DELIVERY =====");



        // =========================
        // US06 - CLIENTE
        // =========================

        System.out.println("\n===== US06 - Cadastro cliente =====");


        ClienteRepositoryEmMemoria clienteRepository =
                new ClienteRepositoryEmMemoria();


        CadastroClienteService cadastro =
                new CadastroClienteService(clienteRepository);



        Cliente cliente =
                new Cliente(
                        "Ana Oliveira",
                        "123.123.123-45",
                        "COMUM",
                        0
                );



        cliente.adicionarEndereco(
                new Endereco(
                        "Rua das Flores",
                        "100",
                        "Casa",
                        "Centro",
                        "Vitoria",
                        "ES",
                        "29000000",
                        true
                )
        );



        cadastro.cadastrarCliente(cliente);



        System.out.println(
                "Cliente cadastrado:"
        );

        System.out.println(cliente);





        System.out.println("\n===== US06 - Editar cliente =====");



        Cliente clienteEditado =
                new Cliente(
                        "Ana Oliveira Silva",
                        "123.123.123-45",
                        "PREMIUM",
                        20
                );



        clienteEditado.adicionarEndereco(
                new Endereco(
                        "Rua Nova",
                        "500",
                        "",
                        "Jardim",
                        "Vitoria",
                        "ES",
                        "29000000",
                        true
                )
        );



        EditarClienteService editar =
                new EditarClienteService(clienteRepository);



        editar.editarCliente(clienteEditado);



        System.out.println(
                clienteRepository
                .buscarClientePorCpf("12312312345")
                .get()
        );





        // =========================
        // LOGIN
        // =========================


        System.out.println("\n===== AUTENTICACAO =====");



        UsuarioRepositoryEmMemoria usuarioRepository =
                new UsuarioRepositoryEmMemoria();



        AutenticacaoService autenticacao =
                new AutenticacaoService(usuarioRepository);



        Sessao sessao =
                autenticacao.autenticar(
                        "rosa50",
                        "33331111"
                );



        System.out.println(
                "Usuario logado: "
                + sessao.getUsuario().getNomeUsuario()
        );

System.out.println("\n===== US05 - Painel Operacional =====");


PedidoRepositoryEmMemoria pedidoRepository =
        new PedidoRepositoryEmMemoria();


PainelOperacionalService painelService =
        new PainelOperacionalService(pedidoRepository);



PainelOperacionalDTO painel =
        painelService.carregarPainel(
                LocalDate.of(2026, 6, 20)
        );


System.out.println("Data: "
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


        System.out.println("\n===== FIM TESTE FINAL =====");


    }

}