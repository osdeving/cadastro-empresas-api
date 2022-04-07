package br.com.broadfactor.cadempresas.service.impl;

import br.com.broadfactor.cadempresas.model.Usuario;
import br.com.broadfactor.cadempresas.repositories.UsuarioRepository;
import br.com.broadfactor.cadempresas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario cadastrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> consultar(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> atualizar(Long id, Usuario usuario) {

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if(optionalUsuario.isPresent()) {
            var u = optionalUsuario.get();
            u.setCnpj(usuario.getCnpj());
            u.setEmail(usuario.getEmail());
            u.setNome(usuario.getNome());
            u.setSenha(usuario.getSenha());
        }

        return optionalUsuario;
    }
}