package com.ufes.delivery.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CadastroUsuarioView extends JFrame implements ICadastroUsuarioView{
    private final JTextField txtNomeCivil;
    private final JTextField txtNomeUsuario;
    private final JTextField txtSenha;
    private final JLabel lblNomeCivil;
    private final JLabel lblNomeUsuario;
    private final JLabel lblSenha;
    private final JButton btnCadastrarUsuario;
    
    public CadastroUsuarioView(){
        this.txtNomeCivil = new JTextField(30);
        this.txtNomeUsuario = new JTextField(20);
        this.txtSenha = new JTextField(20);
        this.lblNomeCivil = new JLabel("Nome civil:");
        this.lblNomeUsuario = new JLabel("Nome de usuário:");
        this.lblSenha = new JLabel("Senha:");
        this.btnCadastrarUsuario = new JButton("Cadastrar");
        inicializarComponentes();
        configurarJanela();
    }
    
    private void inicializarComponentes(){
        JPanel pnlCadastrarUsuario = new JPanel(new GridBagLayout());
        GridBagConstraints restricoes = new GridBagConstraints();
        
        pnlCadastrarUsuario.setBorder(BorderFactory.createTitledBorder("Dados"));
        
        restricoes.insets = new Insets(5, 5, 5, 5);
        restricoes.anchor = GridBagConstraints.WEST;

        restricoes.gridx = 0;
        restricoes.gridy = 0;
        pnlCadastrarUsuario.add(this.lblNomeCivil, restricoes);

        restricoes.gridx = 1;
        restricoes.gridy = 0;
        pnlCadastrarUsuario.add(this.txtNomeCivil, restricoes);
        
        restricoes.gridx = 0;
        restricoes.gridy = 1;
        pnlCadastrarUsuario.add(this.lblNomeUsuario, restricoes);

        restricoes.gridx = 1;
        restricoes.gridy = 1;
        pnlCadastrarUsuario.add(this.txtNomeUsuario, restricoes);
        
        restricoes.gridx = 0;
        restricoes.gridy = 2;
        pnlCadastrarUsuario.add(this.lblSenha, restricoes);

        restricoes.gridx = 1;
        restricoes.gridy = 2;
        pnlCadastrarUsuario.add(this.txtSenha, restricoes);
        
        restricoes.gridx = 0;
        restricoes.gridy = 3;
        restricoes.gridwidth = 1;
        restricoes.anchor = GridBagConstraints.CENTER;
        pnlCadastrarUsuario.add(this.btnCadastrarUsuario, restricoes);
        
        add(pnlCadastrarUsuario);
    }
    
    private void configurarJanela() {
        setTitle("Cadastrar Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(false);
    }
    
    @Override
    public JTextField getTxtNomeCivil() {
        return this.txtNomeCivil;
    }
    
    @Override
    public JTextField getTxtNomeUsuario() {
        return this.txtNomeUsuario;
    }
    
    @Override
    public JTextField getTxtSenha() {
        return this.txtSenha;
    }
    
    @Override
    public JButton getBtnCadastrarUsuario(){
        return this.btnCadastrarUsuario;
    }
    
    @Override
    public JFrame getJanelaCadastroUsuario() {
        return this;
    }
}
