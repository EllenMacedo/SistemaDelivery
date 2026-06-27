package com.ufes.delivery.service.usuario;

import com.ufes.delivery.model.Usuario;
import com.ufes.delivery.model.PerfilUsuario;
import com.ufes.delivery.model.SituacaoUsuario;
import com.ufes.delivery.repository.IUsuarioRepository;

import java.util.List;
/*Responsável por:
buscar usuários
autorizar usuários
desautorizar usuários
excluir usuários
validar permissões de admin*/

public class GerenciamentoUsuarioService {
    
    private final IUsuarioRepository usuarioRepository;

    public GerenciamentoUsuarioService(IUsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
    }
    
    public void validarAdmin(Usuario usuarioLogado) {
    if (usuarioLogado == null || usuarioLogado.getPerfil() != PerfilUsuario.ADMINISTRADOR || usuarioLogado.getSituacao()==SituacaoUsuario.AUTORIZADO) {
        throw new IllegalArgumentException("GerenciamentoUsuarioService erro 1: Acesso restrito ao administrador ou usuario é nulo");
    }
    }
    
    private void validarSelecao(List<Usuario> usuariosSelecionados) {
    if (usuariosSelecionados == null || usuariosSelecionados.isEmpty()) {
        throw new IllegalArgumentException("GerenciamentoUsuarioService erro 1.2:Lista de usuarios para validacai vazia");
    }
    }
    
    public List<Usuario> buscarUsuarios(String termo, Usuario usuarioLogado) {

    validarAdmin(usuarioLogado);

    List<Usuario> todos = usuarioRepository.listarUsuarios();

    if (termo == null || termo.isBlank() || termo.length()>120 || termo.length()<2)
    {
        return todos;
    }

    String filtro = termo.toLowerCase();

    return todos.stream()
            .filter(u ->u.getNomeUsuario().toLowerCase().contains(filtro) ||u.getNomeCivil().toLowerCase().contains(filtro)).toList();
    }
    
    public void autorizarUsuarios(List<Usuario> usuariosSelecionados, Usuario usuarioLogado) {

    validarAdmin(usuarioLogado);
    validarSelecao(usuariosSelecionados);

    for (Usuario u : usuariosSelecionados) {
        u.setSituacao(SituacaoUsuario.AUTORIZADO);
    }
    }
    
    public void desautorizarUsuarios(List<Usuario> usuariosSelecionados, Usuario usuarioLogado) {

    validarAdmin(usuarioLogado);
    validarSelecao(usuariosSelecionados);

    for (Usuario u : usuariosSelecionados) {
        u.setSituacao(SituacaoUsuario.NAO_AUTORIZADO);
    }
    }
    
    public void excluirUsuarios(List<Usuario> usuariosSelecionados, Usuario usuarioLogado) {

    validarAdmin(usuarioLogado);
    validarSelecao(usuariosSelecionados);

    for (Usuario u : usuariosSelecionados) {
        usuarioRepository.removerUsuario(u.getNomeUsuario());
    }
    }
    
    //criar funcao para alterar usuarios depois
}
