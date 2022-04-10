package br.com.broadfactor.cadempresas.service.impl;

import br.com.broadfactor.cadempresas.model.Login;
import br.com.broadfactor.cadempresas.repositories.LoginRepository;
import br.com.broadfactor.cadempresas.security.DetalhesDoUsuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalhesDoUsuarioServiceImpl implements UserDetailsService {
    private final LoginRepository loginRepository;

    public DetalhesDoUsuarioServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Login> login = loginRepository.findByLogin(username);

        if(login.isEmpty()) {
            throw new UsernameNotFoundException("Usuário ["+ username + "] não encontrado");
        }

        return new DetalhesDoUsuario(login);
    }
}
