package br.com.broadfactor.cadempresas.service;

import br.com.broadfactor.cadempresas.model.Usuario;

import java.util.Optional;


public interface UsuarioService {
    Usuario cadastrar(Usuario usuario);
    Optional<Usuario> consultar(Long id);
    Optional<Usuario> atualizar(Long id, Usuario usuario);
}
