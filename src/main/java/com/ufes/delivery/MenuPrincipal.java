package com.ufes.delivery;

import com.ufes.delivery.repository.IUsuarioRepository;
import com.ufes.delivery.repository.UsuarioRepositoryEmMemoria;
import com.ufes.delivery.service.autenticacao.AutenticacaoService;
import com.ufes.delivery.view.ILoginView;
import com.ufes.delivery.view.LoginView;
import com.ufes.delivery.presenter.ExibidorMensagem;
import com.ufes.delivery.presenter.ExibidorMensagemJOptionPane;
import com.ufes.delivery.presenter.LoginPresenter;

public class MenuPrincipal { // classe para testar a interface do sistema

    public static void main(String[] args) {
        IUsuarioRepository usuarioRep = new UsuarioRepositoryEmMemoria();
        AutenticacaoService autenticacaoService = new AutenticacaoService(usuarioRep);
        
        ILoginView loginView = new LoginView();
        
        ExibidorMensagem exibidorMensagem =  
            new ExibidorMensagemJOptionPane(loginView.getJanelaLogin());
        
        new LoginPresenter(loginView, autenticacaoService, exibidorMensagem);
        
    }
}