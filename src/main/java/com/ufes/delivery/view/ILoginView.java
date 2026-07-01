package com.ufes.delivery.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public interface ILoginView {
    JTextField getTxtNomeUsuario();
    JPasswordField getTxtSenha();
    JButton getBtnAcessar();
    JButton getBtnCancelar();
    JButton getBtnCadastrarUsuario();
    JFrame getJanelaLogin();

}
