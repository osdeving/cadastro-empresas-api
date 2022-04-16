package br.com.broadfactor.cadempresas.repositories;

import br.com.broadfactor.cadempresas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByCnpj(String cnpj);
}
