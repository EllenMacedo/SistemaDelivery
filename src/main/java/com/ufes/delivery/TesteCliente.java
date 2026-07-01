package com.ufes.delivery;

import com.ufes.delivery.model.Cliente;
import com.ufes.delivery.repository.ClienteRepositoryEmMemoria;
import com.ufes.delivery.service.cliente.BuscarClienteService;
import com.ufes.delivery.model.Endereco;
import com.ufes.delivery.service.cliente.CadastroClienteService;
import com.ufes.delivery.service.cliente.EditarClienteService;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public class TesteCliente {//classe temporaria para fins de teste

    public static void main(String[] args) {


        ClienteRepositoryEmMemoria repository =
                new ClienteRepositoryEmMemoria();


        BuscarClienteService service =
                new BuscarClienteService(repository);



        System.out.println("===== TESTE 1 - Buscar por nome =====");


        List<Cliente> encontrados =
                service.buscarPorNome("Maria");


        for (Cliente c : encontrados) {

            System.out.println(c);
        }




        System.out.println("\n===== TESTE 2 - Buscar CPF com máscara =====");


        try {

            Optional<Cliente> cliente =
                    service.buscarPorCpf("987.654.321-00");


            if (cliente.isPresent()) {

                System.out.println(
                        "Cliente encontrado: "
                        + cliente.get()
                );

            } else {

                System.out.println(
                        "Nenhum cliente encontrado"
                );
            }


        } catch (Exception e) {

            System.out.println(
                    "Erro: " + e.getMessage()
            );
        }




        System.out.println("\n===== TESTE 3 - CPF inválido =====");


        try {

            service.buscarPorCpf("111.111.111-11");


        } catch (Exception e) {

            System.out.println(
                    "Esperado: "
                    + e.getMessage()
            );
        }





        System.out.println("\n===== TESTE 4 - Busca vazia =====");


        try {

            service.buscarPorNome("");


        } catch (Exception e) {

            System.out.println(
                    "Esperado: "
                    + e.getMessage()
            );
        }




        System.out.println("\n===== TESTE 5 - Cliente selecionado =====");


        Optional<Cliente> selecionado =
                service.buscarPorCpf("98765432100");


        if (selecionado.isPresent()) {

            Cliente cliente = selecionado.get();

            System.out.println(
                    "Abrindo cliente: "
                    + cliente.getNome()
            );
        }

        System.out.println("\n===== TESTE 6 - Cadastrar cliente válido =====");


CadastroClienteService cadastro =
        new CadastroClienteService(repository);


Cliente novoCliente = new Cliente(
        "Carlos Oliveira",
        "11122233344",
        "COMUM",
        0
);


novoCliente.adicionarEndereco(
        new Endereco(
                "Rua Principal",
                "100",
                "Casa",
                "Centro",
                "Vitoria",
                "ES",
                "29000000",
                true
        )
);



try {

    cadastro.cadastrarCliente(novoCliente);

    System.out.println(
            "Cadastrado: " + novoCliente
    );


} catch(Exception e) {

    System.out.println(
            "Erro: " + e.getMessage()
    );
}





System.out.println("\n===== TESTE 7 - CPF duplicado =====");


try {


    Cliente duplicado = new Cliente(
            "Outro Nome",
            "11122233344",
            "COMUM",
            0
    );


    duplicado.adicionarEndereco(
            new Endereco(
                    "Rua X",
                    "1",
                    "",
                    "Centro",
                    "Vitoria",
                    "ES",
                    "29000000",
                    true
            )
    );


    cadastro.cadastrarCliente(duplicado);



} catch(Exception e) {


    System.out.println(
            "Esperado: " + e.getMessage()
    );

}




System.out.println("\n===== TESTE 8 - Sem endereço padrão =====");


try {


    Cliente semPadrao = new Cliente(
            "Ana Costa",
            "99988877766",
            "COMUM",
            0
    );


    semPadrao.adicionarEndereco(
            new Endereco(
                    "Rua A",
                    "20",
                    "",
                    "Centro",
                    "Vitoria",
                    "ES",
                    "29000000",
                    false
            )
    );


    cadastro.cadastrarCliente(semPadrao);



} catch(Exception e) {


    System.out.println(
            "Esperado: " + e.getMessage()
    );

}

System.out.println("\n===== TESTE 9 - Mais de três endereços =====");


try {


    Cliente limiteEndereco = new Cliente(
            "Pedro Santos",
            "55566677788",
            "COMUM",
            0
    );


    limiteEndereco.adicionarEndereco(
            new Endereco(
                    "Rua 1",
                    "1",
                    "",
                    "Centro",
                    "Vitoria",
                    "ES",
                    "29000000",
                    true
            )
    );


    limiteEndereco.adicionarEndereco(
            new Endereco(
                    "Rua 2",
                    "2",
                    "",
                    "Centro",
                    "Vitoria",
                    "ES",
                    "29000001",
                    false
            )
    );


    limiteEndereco.adicionarEndereco(
            new Endereco(
                    "Rua 3",
                    "3",
                    "",
                    "Centro",
                    "Vitoria",
                    "ES",
                    "29000002",
                    false
            )
    );


    limiteEndereco.adicionarEndereco(
            new Endereco(
                    "Rua 4",
                    "4",
                    "",
                    "Centro",
                    "Vitoria",
                    "ES",
                    "29000003",
                    false
            )
    );


} catch(Exception e) {


    System.out.println(
            "Esperado: " + e.getMessage()
    );

}

System.out.println("\n===== TESTE 10 - Editar cliente =====");


try {


    Cliente clienteEditado = new Cliente(
            "Carlos Oliveira Silva",
            "11122233344",
            "COMUM",
            50
    );


    clienteEditado.adicionarEndereco(
            new Endereco(
                    "Rua Nova",
                    "500",
                    "Apartamento",
                    "Praia",
                    "Vitoria",
                    "ES",
                    "29000000",
                    true
            )
    );



    EditarClienteService editar =
        new EditarClienteService(repository);


    editar.editarCliente(clienteEditado);



    Optional<Cliente> atualizado =
            repository.buscarClientePorCpf("11122233344");


    System.out.println(
            "Atualizado: " + atualizado.get()
    );



} catch(Exception e) {


    System.out.println(
            "Erro: " + e.getMessage()
    );

}

System.out.println("\n===== TESTE 11 - Trocar endereço padrão =====");


Cliente cliente =
        repository.buscarClientePorCpf("11122233344")
        .get();


cliente.adicionarEndereco(
        new Endereco(
                "Rua Segunda",
                "200",
                "",
                "Jardim",
                "Vitoria",
                "ES",
                "29000000",
                false
        )
);


cliente.definirEnderecoPadrao(1);


System.out.println(cliente);

    }
}