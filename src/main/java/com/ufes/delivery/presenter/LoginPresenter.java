package com.ufes.delivery.presenter;

import com.ufes.delivery.command.CadastrarUsuarioCommand;
import com.ufes.delivery.model.Sessao;
import com.ufes.delivery.repository.UsuarioRepositoryEmMemoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import com.ufes.delivery.service.autenticacao.AutenticacaoService;
import com.ufes.delivery.service.usuario.CadastroUsuarioService;
import com.ufes.delivery.view.CadastroUsuarioView;
import com.ufes.delivery.view.ILoginView;

public final class LoginPresenter {
    private final ILoginView loginView;
    private final AutenticacaoService autenticacaoService;
    private final ExibidorMensagem exibidorMensagem;
    
    public LoginPresenter(ILoginView loginView,
            AutenticacaoService autenticacaoService,
            ExibidorMensagem exibidorMensagem) {
        this.loginView = Objects.requireNonNull(loginView, "A view não pode ser nula.");
        this.autenticacaoService = Objects.requireNonNull(autenticacaoService,"O serviço de autenticação não pode ser nulo.");
        this.exibidorMensagem = Objects.requireNonNull(exibidorMensagem, "O exibidor de mensagem não pode ser nulo.");
        configurarListeners();
        exibirTelaInicial();
    }
    
    private void configurarListeners() {
        this.loginView.getBtnAcessar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento) {
                logarUsuario();
            }
        });
        
        this.loginView.getBtnCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                cancelarLogin();
            }
        });
        
        this.loginView.getBtnCadastrarUsuario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                cadastrarUsuario();
            }
        });
    }
    
    private void logarUsuario() {
        String nomeUsuario = this.loginView.getTxtNomeUsuario().getText();
        String senha = new String(this.loginView.getTxtSenha().getPassword());
            
        try{
            Sessao sessao = this.autenticacaoService.autenticar(nomeUsuario, senha);
            this.exibidorMensagem.exibirInformacao("Usuário autenticado com sucesso!", "Credenciais válidas");
        }catch (Exception e){
            this.exibidorMensagem.exibirErro(e.getMessage(),"Erro");
            this.loginView.getTxtSenha().setText(""); 
        }
    }

    private void cancelarLogin(){
        loginView.getJanelaLogin().dispose();
    }
    
    private void cadastrarUsuario(){
        CadastroUsuarioView cadastroUsuarioView = new CadastroUsuarioView();
        
        CadastroUsuarioService cadastroUsuarioService = 
                new CadastroUsuarioService(new UsuarioRepositoryEmMemoria());
        
        new CadastroUsuarioPresenter(cadastroUsuarioView,
            cadastroUsuarioService, exibidorMensagem);
        
        cadastroUsuarioView.setVisible(true);
    }

    private void exibirTelaInicial() {
        this.loginView.getJanelaLogin().setVisible(true);
    }

}
