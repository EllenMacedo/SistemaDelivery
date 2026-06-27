package com.ufes.delivery.repository;

import com.ufes.delivery.model.Usuario;
import java.util.Optional;
import java.util.List;

public interface IUsuarioRepository {
    Optional<Usuario> buscarUsuarioPorNomeUsuario(String nomeUsuario);
    void adicionarUsuario(Usuario usuario);
    boolean existeAdministradorAutorizado();
    List<Usuario> listarUsuarios();
    void removerUsuario(String nomeUsuario);
}
