package br.com.broadfactor.cadempresas.repositories;

import br.com.broadfactor.cadempresas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
