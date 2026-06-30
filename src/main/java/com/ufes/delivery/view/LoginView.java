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

    public LoginView() {
        this.txtNomeUsuario = new JTextField(20);
        //para impedir o usuário de inserir um nome de usuário 
        // com letras maiúsculas ou espaço
        txtNomeUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String texto = txtNomeUsuario.getText();
                texto = texto.toLowerCase().replace(" ", "");
                txtNomeUsuario.setText(texto);
            }
        });
        this.txtSenha = new JPasswordField(20);
        this.btnAcessar = new JButton("Acessar");
        this.btnCancelar = new JButton("Cancelar");
        this.btnCadastrarUsuario = new JButton("Cadastrar usuário");
        this.lblNomeUsuario = new JLabel("Nome de usuário:");
        this.lblSenha = new JLabel("Senha:");
        inicializarComponentes();
        configurarJanela();
    }
    
     private void inicializarComponentes() {
        JPanel pnlPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints restricoes = new GridBagConstraints();
        
        pnlPrincipal.setBorder(BorderFactory.createTitledBorder("Dados de Acesso"));
        
        restricoes.insets = new Insets(5, 5, 5, 5);
        restricoes.anchor = GridBagConstraints.WEST;

        restricoes.gridx = 0;
        restricoes.gridy = 0;
        pnlPrincipal.add(this.lblNomeUsuario, restricoes);

        restricoes.gridx = 1;
        restricoes.gridy = 0;
        pnlPrincipal.add(this.txtNomeUsuario, restricoes);

        restricoes.gridx = 0;
        restricoes.gridy = 1;
        pnlPrincipal.add(this.lblSenha, restricoes);

        restricoes.gridx = 1;
        restricoes.gridy = 1;
        pnlPrincipal.add(this.txtSenha, restricoes);
        
        restricoes.gridx = 0;
        restricoes.gridy = 2;
        restricoes.gridwidth = 1;
        restricoes.anchor = GridBagConstraints.CENTER;
        pnlPrincipal.add(this.btnAcessar, restricoes);

        restricoes.gridx = 1;
        restricoes.gridy = 2;
        restricoes.gridwidth = 1;
        restricoes.anchor = GridBagConstraints.CENTER;
        pnlPrincipal.add(this.btnCancelar, restricoes);
        
        restricoes.gridx = 2;
        restricoes.gridy = 2;
        restricoes.gridwidth = 1;
        restricoes.anchor = GridBagConstraints.CENTER;
        pnlPrincipal.add(this.btnCadastrarUsuario, restricoes);

        add(pnlPrincipal);
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
    public JFrame getJanelaPrincipal() {
        return this;
    }

}
