package br.com.broadfactor.cadempresas.repositories;

import br.com.broadfactor.cadempresas.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByLogin(String login);
}
