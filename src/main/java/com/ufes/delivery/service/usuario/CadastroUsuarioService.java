package com.ufes.delivery.service.usuario;

import com.ufes.delivery.model.Usuario;
import com.ufes.delivery.model.PerfilUsuario;
import com.ufes.delivery.model.SituacaoUsuario;
import com.ufes.delivery.repository.IUsuarioRepository;

public class CadastroUsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public CadastroUsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void cadastrarUsuario(String nomeUsuario, String nomeCivil, String senha) {

        // validação de entrada
        validarNomeUsuario(nomeUsuario);
        validarNomeCivil(nomeCivil);
        validarSenha(senha);

        // verifica duplicidade
        if (usuarioRepository.buscarUsuarioPorNomeUsuario(nomeUsuario).isPresent()) {
            throw new IllegalArgumentException("CadastroUsuarioService erro 1:Nome de usuário já está em uso");
        }

        // verifica se já existe admin autorizado
        boolean existeAdmin = usuarioRepository.existeAdministradorAutorizado();

        PerfilUsuario perfil;
        SituacaoUsuario situacao;

        if (!existeAdmin) {
            perfil = PerfilUsuario.ADMINISTRADOR;
            situacao = SituacaoUsuario.AUTORIZADO;
        } else {
            perfil = PerfilUsuario.ATENDENTE;
            situacao = SituacaoUsuario.PENDENTE;
        }

        // cria usuário
        Usuario usuario = new Usuario(
                nomeUsuario,
                nomeCivil,
                senha,
                situacao,
                perfil
        );

        // salva no repositório
        usuarioRepository.adicionarUsuario(usuario);
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

    private void validarNomeCivil(String nomeCivil) {
        if (nomeCivil == null || nomeCivil.isBlank()) {
            throw new IllegalArgumentException("Nome civil obrigatório");
        }

        if (nomeCivil.length() < 2 || nomeCivil.length() > 120) {
            throw new IllegalArgumentException("Nome civil inválido");
        }
        
        if (!nomeCivil.matches("^[a-zA-Z' -]+$")) {
            throw new IllegalArgumentException("Nome civil deve conter apenas letras minusculas, maiusculas, apostrofos e hifens");
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