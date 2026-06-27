package com.ufes.delivery.model;

public class Usuario {
    private String nomeUsuario, nomeCivil, senha;
    private SituacaoUsuario situacao;
    private PerfilUsuario perfil;
    
    public Usuario(String nomeUsuario, String nomeCivil, String senha, SituacaoUsuario situacao, PerfilUsuario perfil)
    {
        this.nomeUsuario=nomeUsuario;
        this.nomeCivil=nomeCivil;
        this.senha=senha;
        this.situacao=situacao;
        this.perfil=perfil;
    }
    
    public String getNomeUsuario()
    {
        return nomeUsuario;
    }
    
    public String getNomeCivil()
    {
        return nomeCivil;
    }
    
    public String getSenha()
    {
        return senha;
    }
    
    public SituacaoUsuario getSituacao()
    {
        return situacao;
    }
    
    public void setSituacao(SituacaoUsuario situacao)
    {
        this.situacao=situacao;
    }
    
    public PerfilUsuario getPerfil()
    {
        return perfil;
    }
    
    public void setPerfil(PerfilUsuario perfil)
    {
        this.perfil=perfil;
    }
}
