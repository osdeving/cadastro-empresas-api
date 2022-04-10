package br.com.broadfactor.cadempresas.service.impl;

import br.com.broadfactor.cadempresas.dto.EmpresaDto;
import br.com.broadfactor.cadempresas.dto.utils.EmpresaDtoUtils;
import br.com.broadfactor.cadempresas.dto.utils.MapperUtils;
import br.com.broadfactor.cadempresas.exceptions.CnpjJaExisteException;
import br.com.broadfactor.cadempresas.exceptions.EmailJaExisteException;
import br.com.broadfactor.cadempresas.exceptions.UsuarioJaExisteException;
import br.com.broadfactor.cadempresas.model.Login;
import br.com.broadfactor.cadempresas.model.Usuario;
import br.com.broadfactor.cadempresas.repositories.LoginRepository;
import br.com.broadfactor.cadempresas.repositories.UsuarioRepository;
import br.com.broadfactor.cadempresas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final LoginRepository loginRepository;
    private final RequestClientService client;

    @Override
    public Usuario cadastrar(Usuario usuario) {
        Usuario u = usuarioRepository.findByEmail(usuario.getEmail());

        if(u != null) {
            throw new EmailJaExisteException("Email já cadastrado");
        }

        String cnpj = usuario.getCnpj().replaceAll("\\D", "");
        usuario.setCnpj(cnpj);

        Optional<Usuario> optionalUsuario = usuarioRepository.findByCnpj(cnpj);

        if(optionalUsuario.isPresent()) {
            throw new CnpjJaExisteException("CNPJ já cadastrado");
        }

        try {
            EmpresaDto empresaDto = client.getEmpresaByCnpj(usuario.getCnpj()).block();
            usuario.setEmpresa(EmpresaDtoUtils.toEntity(empresaDto));
        } catch(Exception ex) {
            log.info("Erro ao consultar empresa no serviço: " + ex.getMessage());
        }

        criaNovoLoginUsuario(usuario);

        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> consultar(String cnpj) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByCnpj(cnpj);
        return optionalUsuario;
    }

    private Login criaNovoLoginUsuario(Usuario usuario) {
        Login login = new Login();

        login.setLogin(usuario.getEmail());
        login.setPassword(usuario.getSenha());

        Optional<Login> optionalLogin = loginRepository.findByLogin(login.getLogin());

        if(optionalLogin.isPresent()) {
            throw new UsuarioJaExisteException("Usuario já cadastrado");
        }

        return loginRepository.save(login);
    }

    @Override
    public Optional<Usuario> atualizar(Usuario usuario) {

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuario.getId());

        if(optionalUsuario.isPresent()) {
            Usuario usuarioToUpdate = optionalUsuario.get();

            MapperUtils.nonNullAndNestedAwareCopy(usuario, usuarioToUpdate);


            return Optional.of(usuarioToUpdate);
        }

        return Optional.empty();
    }
}
