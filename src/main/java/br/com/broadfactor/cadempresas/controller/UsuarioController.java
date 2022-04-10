package br.com.broadfactor.cadempresas.controller;

import br.com.broadfactor.cadempresas.controller.dto.request.AtualizacaoUsuarioForm;
import br.com.broadfactor.cadempresas.controller.dto.request.UsuarioForm;
import br.com.broadfactor.cadempresas.controller.dto.response.DetalhesDoUsuarioDto;
import br.com.broadfactor.cadempresas.controller.dto.response.UsuarioDto;
import br.com.broadfactor.cadempresas.dto.utils.MapperUtils;
import br.com.broadfactor.cadempresas.model.Usuario;
import br.com.broadfactor.cadempresas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@Slf4j
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final PasswordEncoder encoder;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder) {
        usuarioForm.setSenha(encoder.encode(usuarioForm.getSenha()));
        Usuario usuario = usuarioService.cadastrar(usuarioForm.toEntity());

        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }


    @PutMapping("/empresa")
    @Transactional
    public ResponseEntity<DetalhesDoUsuarioDto> atualizar(@RequestBody @Valid AtualizacaoUsuarioForm form) {
        Usuario usuario = MapperUtils.map(form, Usuario.class);
        Optional<Usuario> optionalUsuario = usuarioService.atualizar(usuario);

        if(optionalUsuario.isPresent()) {
            DetalhesDoUsuarioDto detalhesDoUsuarioDto = new DetalhesDoUsuarioDto(optionalUsuario.get());
            return ResponseEntity.ok().body(detalhesDoUsuarioDto);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/empresa/{cnpj}")
    public ResponseEntity<DetalhesDoUsuarioDto> consultar(@PathVariable String cnpj) {
        Optional<Usuario> optionalUsuario = usuarioService.consultar(cnpj);

        if(optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            DetalhesDoUsuarioDto detalhesDoUsuarioDto = new DetalhesDoUsuarioDto(usuario);
            return ResponseEntity.ok().body(detalhesDoUsuarioDto);
        }

        return ResponseEntity.notFound().build();
    }
}
