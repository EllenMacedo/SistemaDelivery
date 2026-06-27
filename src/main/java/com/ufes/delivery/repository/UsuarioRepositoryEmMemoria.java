package com.ufes.delivery.repository;

import com.ufes.delivery.model.Usuario;
import com.ufes.delivery.model.SituacaoUsuario;
import com.ufes.delivery.model.PerfilUsuario;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class UsuarioRepositoryEmMemoria implements IUsuarioRepository{
    private Map<String, Usuario> usuarios = new HashMap<>();
    
    public UsuarioRepositoryEmMemoria(){
        //nao esquecer: nome usuario apenas minusculo
        //nome de usuario serve como identificador no map já que sao unicos msm
        usuarios.put("rosa50", new Usuario("rosa50","Rosa Branca", "33331111", SituacaoUsuario.AUTORIZADO, PerfilUsuario.ADMINISTRADOR));
        usuarios.put("joao13", new Usuario("joao13","Joao da Silva", "11111111", SituacaoUsuario.AUTORIZADO, PerfilUsuario.ATENDENTE));
        usuarios.put("jose17", new Usuario("jose17","Jose Armando","22221111", SituacaoUsuario.AUTORIZADO, PerfilUsuario.ATENDENTE));
        usuarios.put("maria2002", new Usuario("maria2002","Maria Soares", "44441111", SituacaoUsuario.NAO_AUTORIZADO, PerfilUsuario.ATENDENTE));
        usuarios.put("pedro777", new Usuario("pedro777","Pedro Globson", "55551111", SituacaoUsuario.PENDENTE, PerfilUsuario.ATENDENTE));
    }
    
    @Override
    public Optional<Usuario> buscarUsuarioPorNomeUsuario(String nomeUsuario) {
    if (nomeUsuario == null) {
        return Optional.empty();
    }
    return Optional.ofNullable(usuarios.get(nomeUsuario.toLowerCase()));
    }
    
    @Override
    public void adicionarUsuario(Usuario usuario)//adiciona usuario na lista
    {
    validarUsuario(usuario);

    if (usuarios.containsKey(usuario.getNomeUsuario().toLowerCase())) {
    throw new IllegalArgumentException("Usuario ja existe");
    }

    usuarios.put(usuario.getNomeUsuario().toLowerCase(), usuario);//coloca o nome de usuario forcado em lowercase como identificados do map e depois o objeto usuario com as informacoes
    }
    
    @Override
    public boolean existeAdministradorAutorizado()//usado para cumprir US02 que torna primeiro usuario cadastrado em um adm autorizado
    {
        for (Usuario usuario : usuarios.values()){
        if (usuario.getPerfil() == PerfilUsuario.ADMINISTRADOR && usuario.getSituacao() == SituacaoUsuario.AUTORIZADO){
        return true;
        }
    }
        return false;
    }
    
    @Override
    public List<Usuario> listarUsuarios()//transforma usuarios em lista, é usado em funcoes que recebem uma lista de usuarios para exclusao, permissao e etc.
    {
    return new ArrayList<>(usuarios.values());
    }
    
    @Override
    public void removerUsuario(String nomeUsuario)
    {
    if (nomeUsuario == null || nomeUsuario.isBlank()) {
        throw new IllegalArgumentException("Nome de usuário inválido");
    }

    usuarios.remove(nomeUsuario.toLowerCase());
    }
    
    
    private void validarUsuario(Usuario usuario)//valida os aspectos necessarios para cumprir com US01
    {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario nao pode ser nulo");
        }
        //garante regras de nome de usuario
        if (usuario.getNomeUsuario() == null || usuario.getNomeUsuario().isBlank()) {
            throw new IllegalArgumentException("Nome do usuario nao pode ser vazio");
        }
        
        if (usuario.getNomeUsuario().length()>30 || usuario.getNomeUsuario().length()<3)
        {
            throw new IllegalArgumentException("Nome do usuario de tamanho invalido");
        }
        //garante regras de nome civil
        if (usuario.getNomeCivil() == null || usuario.getNomeCivil().isBlank()) {
            throw new IllegalArgumentException("Nome civil nao pode ser vazio");
        }
        
        if (usuario.getNomeCivil().length()>120 || usuario.getNomeCivil().length()<2)
        {
            throw new IllegalArgumentException("Nome civil de tamanho invalido");
        }
        
        if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            throw new IllegalArgumentException("Senha do usuario nao pode ser vazio");
        }
        
        if (usuario.getSenha().length()>64 || usuario.getSenha().length()<8)
        {
            throw new IllegalArgumentException("Senha do usuario de tamanho invalido");
        }
    }

}
