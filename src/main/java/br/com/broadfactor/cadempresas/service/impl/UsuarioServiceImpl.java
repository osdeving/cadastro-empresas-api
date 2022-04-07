package br.com.broadfactor.cadempresas.service.impl;

import br.com.broadfactor.cadempresas.model.Usuario;
import br.com.broadfactor.cadempresas.repositories.UsuarioRepository;
import br.com.broadfactor.cadempresas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario cadastrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
