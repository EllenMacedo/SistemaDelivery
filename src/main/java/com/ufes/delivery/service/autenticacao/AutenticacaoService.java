package com.ufes.delivery.service.autenticacao;

import com.ufes.delivery.model.Usuario;
import com.ufes.delivery.model.Sessao;
import com.ufes.delivery.model.SituacaoUsuario;
import com.ufes.delivery.repository.IUsuarioRepository;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class AutenticacaoService {

    private final IUsuarioRepository usuarioRepository;

    public AutenticacaoService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = Objects.requireNonNull(usuarioRepository,
                "Repositorio de usuarios nao pode ser nulo");
    }

    public Sessao autenticar(String nomeUsuario, String senha) {

        //  validação de entrada (formato)
        validarNomeUsuario(nomeUsuario);
        validarSenha(senha);

        //  busca usuário
        Optional<Usuario> usuarioOpt = usuarioRepository.buscarUsuarioPorNomeUsuario(nomeUsuario);

        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        Usuario usuario = usuarioOpt.get();

        //  valida senha
        if (!usuario.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        //  valida situação
        if (usuario.getSituacao() != SituacaoUsuario.AUTORIZADO) {
            throw new IllegalStateException("Acesso depende de autorização administrativa");
        }

        //  cria sessão
        return new Sessao(usuario, LocalDateTime.now());
    }

    private void validarNomeUsuario(String nomeUsuario) {
        if (nomeUsuario == null || nomeUsuario.isBlank()) {
            throw new IllegalArgumentException("Nome de usuário obrigatório");
        }

        if (nomeUsuario.length() < 3 || nomeUsuario.length() > 30) {
            throw new IllegalArgumentException("Nome de usuário inválido");
        }

        if (!nomeUsuario.equals(nomeUsuario.toLowerCase())) {
            throw new IllegalArgumentException("Nome deve estar em minúsculas");
        }

        if (nomeUsuario.contains(" ")) {
            throw new IllegalArgumentException("Nome não pode conter espaços");
        }

        if (!nomeUsuario.matches("[a-z0-9]+")) {
            throw new IllegalArgumentException("Nome deve conter apenas letras minúsculas e números");
        }
    }

    private void validarSenha(String senha) {
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha obrigatória");
        }

        if (senha.length() < 8 || senha.length() > 64) {
            throw new IllegalArgumentException("Senha inválida");
        }
    }
}