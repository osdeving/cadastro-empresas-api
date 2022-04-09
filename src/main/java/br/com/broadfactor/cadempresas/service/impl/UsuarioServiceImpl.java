package br.com.broadfactor.cadempresas.service.impl;

import br.com.broadfactor.cadempresas.dto.EmpresaDto;
import br.com.broadfactor.cadempresas.dto.utils.EmpresaDtoUtils;
import br.com.broadfactor.cadempresas.exceptions.CnpjJaExisteException;
import br.com.broadfactor.cadempresas.exceptions.EmailJaExisteException;
import br.com.broadfactor.cadempresas.exceptions.UsuarioJaExisteException;
import br.com.broadfactor.cadempresas.model.Usuario;
import br.com.broadfactor.cadempresas.repositories.UsuarioRepository;
import br.com.broadfactor.cadempresas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RequestClientService client;

    @Override
    public Usuario cadastrar(Usuario usuario) {
        Usuario u = usuarioRepository.findByEmail(usuario.getEmail());

        if(u != null) {
            throw new EmailJaExisteException("Email já cadastrado");
        }

        u = usuarioRepository.findByCnpj(usuario.getCnpj());

        if(u != null) {
            throw new CnpjJaExisteException("CNPJ já cadastrado");
        }

        EmpresaDto empresaDto = client.getEmpresaByCnpj(usuario.getCnpj()).block();
        usuario.setEmpresa(EmpresaDtoUtils.toEntity(empresaDto));

        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> consultar(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> atualizar(Usuario usuario) {

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuario.getId());

        if(optionalUsuario.isPresent()) {
            Usuario usuarioToUpdate = optionalUsuario.get();
            BeanUtils.copyProperties(usuario, usuarioToUpdate, getNullPropertyNames(usuario, usuarioToUpdate));


            return Optional.of(usuarioToUpdate);
        }

        return Optional.empty();
    }

    public String[] getNullPropertyNames (Object source, Object target) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        final BeanWrapper targetBean = new BeanWrapperImpl(target);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            Class<?> accessor = src.getPropertyType(pd.getName());
            System.out.println(accessor);
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }else  {
                Class<?> acc = src.getPropertyType(pd.getName());
                String cname = acc.getCanonicalName();
                if(cname.contains("br.com.broadfactor.cadempresas.model")) {
                    Object targetVal = targetBean.getPropertyValue(pd.getName());
                    if(targetVal  != null) {
                        BeanUtils.copyProperties(srcValue,targetVal , getNullPropertyNames(srcValue,targetVal));
                        emptyNames.add(pd.getName());
                    }
                }
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


}
