package com.ufes.delivery.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public interface ICadastroUsuarioView {
    JTextField getTxtNomeCivil();
    JTextField getTxtNomeUsuario();
    JTextField getTxtSenha();
    JButton getBtnCadastrarUsuario();
    JFrame getJanelaCadastroUsuario();
}
