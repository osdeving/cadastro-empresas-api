package br.com.broadfactor.cadempresas.controller;

import br.com.broadfactor.cadempresas.model.Login;
import br.com.broadfactor.cadempresas.repositories.LoginRepository;
import br.com.broadfactor.cadempresas.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/credenciais")
@Slf4j
public class LoginController {
    private final LoginRepository repository;
    private final PasswordEncoder encoder;

    public LoginController(LoginRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<Login>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/salvar")
    public ResponseEntity<Login> salvar(@RequestBody Login login) {
        login.setPassword(encoder.encode(login.getPassword()));
        return ResponseEntity.ok(repository.save(login));
    }

    @GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String login,
                                                @RequestParam String password) {

        Optional<Login> optionalLogin = repository.findByLogin(login);
        if (optionalLogin.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        Login l = optionalLogin.get();
        boolean valid = encoder.matches(password, l.getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    }
}
