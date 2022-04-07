package br.com.broadfactor.cadempresas.controller;

import br.com.broadfactor.cadempresas.controller.dto.request.UsuarioForm;
import br.com.broadfactor.cadempresas.controller.dto.response.UsuarioDto;
import br.com.broadfactor.cadempresas.model.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@Slf4j
@RequiredArgsConstructor
public class UsuarioController {
    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioForm.toEntity();

        // TODO: salva no banco

        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(1).toUri();

        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }
}
