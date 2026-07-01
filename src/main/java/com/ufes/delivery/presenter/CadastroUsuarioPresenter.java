package com.ufes.delivery.presenter;

import com.ufes.delivery.service.usuario.CadastroUsuarioService;
import com.ufes.delivery.view.CadastroUsuarioView;
import com.ufes.delivery.view.ICadastroUsuarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CadastroUsuarioPresenter {
    private final ICadastroUsuarioView cadastroUsuarioView;
    private final CadastroUsuarioService cadastroUsuarioService;
    private final ExibidorMensagem exibidorMensagem;
    
    public CadastroUsuarioPresenter(
            ICadastroUsuarioView cadastroUsuarioView,
            CadastroUsuarioService cadastroUsuarioService,
            ExibidorMensagem exibidorMensagem){
        this.cadastroUsuarioView = Objects.requireNonNull(cadastroUsuarioView, "A view não pode ser nula.");
        this.cadastroUsuarioService = Objects.requireNonNull(cadastroUsuarioService, "O serviço de cadastro não pode ser nulo.");
        this.exibidorMensagem = Objects.requireNonNull(exibidorMensagem, "O exibidor de mensagem não pode ser nulo.");
        configurarListeners();
        exibirTelaInicial();
    }
    
    private void configurarListeners(){
        this.cadastroUsuarioView.getBtnCadastrarUsuario().addActionListener(new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent evento){
                cadastrarUsuario();
            }
        
        });
    }
    
    private void cadastrarUsuario(){        
        String nomeCivil = this.cadastroUsuarioView.getTxtNomeUsuario().getText();
        String nomeUsuario = this.cadastroUsuarioView.getTxtNomeUsuario().getText();
        String senha = this.cadastroUsuarioView.getTxtSenha().getText();
        
        try{
            this.cadastroUsuarioService.cadastrarUsuario(nomeUsuario, nomeCivil, senha);
            this.exibidorMensagem.exibirInformacao("Usuário cadastrado com sucesso!", "Situação");
            this.cadastroUsuarioView.getJanelaCadastroUsuario().dispose();
        }catch(Exception e){
            this.exibidorMensagem.exibirErro(e.getMessage(),"Erro");
            this.cadastroUsuarioView.getTxtSenha().setText("");
        }
    }
    
    private void exibirTelaInicial() {
        this.cadastroUsuarioView.getJanelaCadastroUsuario().setVisible(true);
    }
}
