package com.ufes.delivery.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame implements ILoginView{
    private final JTextField txtNomeUsuario;
    private final JPasswordField txtSenha;
    private final JButton btnAcessar;
    private final JButton btnCancelar;
    private final JButton btnCadastrarUsuario;
    private final JLabel lblNomeUsuario;
    private final JLabel lblSenha;

    public LoginView(){
        this.txtNomeUsuario = new JTextField(20);
        this.txtSenha = new JPasswordField(20);
        this.btnAcessar = new JButton("Acessar");
        this.btnCancelar = new JButton("Cancelar");
        this.btnCadastrarUsuario = new JButton("Cadastrar usuário");
        this.lblNomeUsuario = new JLabel("Nome de usuário:");
        this.lblSenha = new JLabel("Senha:");
        inicializarComponentes();
        configurarJanela();
    }
    
    private void inicializarComponentes(){
        JPanel pnlLogin = new JPanel(new GridBagLayout());
        GridBagConstraints restricoes = new GridBagConstraints();
        
        pnlLogin.setBorder(BorderFactory.createTitledBorder("Dados de Acesso"));
        
        restricoes.insets = new Insets(5, 5, 5, 5);
        restricoes.anchor = GridBagConstraints.WEST;

        restricoes.gridx = 0;
        restricoes.gridy = 0;
        pnlLogin.add(this.lblNomeUsuario, restricoes);

        restricoes.gridx = 1;
        restricoes.gridy = 0;
        pnlLogin.add(this.txtNomeUsuario, restricoes);

        restricoes.gridx = 0;
        restricoes.gridy = 1;
        pnlLogin.add(this.lblSenha, restricoes);

        restricoes.gridx = 1;
        restricoes.gridy = 1;
        pnlLogin.add(this.txtSenha, restricoes);
        
        restricoes.gridx = 0;
        restricoes.gridy = 2;
        restricoes.gridwidth = 1;
        restricoes.anchor = GridBagConstraints.CENTER;
        pnlLogin.add(this.btnAcessar, restricoes);

        restricoes.gridx = 1;
        restricoes.gridy = 2;
        restricoes.gridwidth = 1;
        restricoes.anchor = GridBagConstraints.CENTER;
        pnlLogin.add(this.btnCancelar, restricoes);
        
        restricoes.gridx = 2;
        restricoes.gridy = 2;
        restricoes.gridwidth = 1;
        restricoes.anchor = GridBagConstraints.CENTER;
        pnlLogin.add(this.btnCadastrarUsuario, restricoes);

        add(pnlLogin);
     }
     
    private void configurarJanela() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(false);
    }
     
    @Override
    public JTextField getTxtNomeUsuario() {
        return this.txtNomeUsuario;
    }
    
    @Override
    public JPasswordField getTxtSenha() {
        return this.txtSenha;
    }

    @Override
    public JButton getBtnAcessar() {
        return this.btnAcessar;
    }

    @Override
    public JButton getBtnCancelar() {
        return this.btnCancelar;
    }
    
    @Override
    public JButton getBtnCadastrarUsuario() {
        return this.btnCadastrarUsuario;
    }

    @Override
    public JFrame getJanelaLogin() {
        return this;
    }

}
