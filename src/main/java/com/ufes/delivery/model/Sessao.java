package com.ufes.delivery.model;
import java.time.LocalDateTime;

public class Sessao {
    private Usuario usuario;
    private LocalDateTime dataHoraLogin;
    
    public Sessao(Usuario usuario, LocalDateTime dataHoraLogin)
    {
        this.usuario=usuario;
        this.dataHoraLogin=dataHoraLogin;
    }
    
    public Usuario getUsuario()
    {
        return usuario;
    }
    
    public LocalDateTime getDataHoraLogin()
    {
        return dataHoraLogin;
    }
}
