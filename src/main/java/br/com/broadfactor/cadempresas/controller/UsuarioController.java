package br.com.broadfactor.cadempresas.controller;

import br.com.broadfactor.cadempresas.controller.dto.request.AtualizacaoUsuarioForm;
import br.com.broadfactor.cadempresas.controller.dto.request.UsuarioForm;
import br.com.broadfactor.cadempresas.controller.dto.response.DetalhesDoUsuarioDto;
import br.com.broadfactor.cadempresas.controller.dto.response.UsuarioDto;
import br.com.broadfactor.cadempresas.dto.AtividadeDto;
import br.com.broadfactor.cadempresas.dto.EmpresaDto;
import br.com.broadfactor.cadempresas.dto.QsaItemDto;
import br.com.broadfactor.cadempresas.dto.utils.MapperUtils;
import br.com.broadfactor.cadempresas.model.AtividadeSecundaria;
import br.com.broadfactor.cadempresas.model.Empresa;
import br.com.broadfactor.cadempresas.model.Usuario;
import br.com.broadfactor.cadempresas.repositories.UsuarioRepository;
import br.com.broadfactor.cadempresas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@Slf4j
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final ModelMapper modelMapper;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioService.cadastrar(usuarioForm.toEntity());

        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoUsuarioForm form) {

        Optional<Usuario> usuario = usuarioService.atualizar(id, form.toEntity());

        if(usuario.isPresent()) {
            return ResponseEntity.ok().body(new UsuarioDto(usuario.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoUsuarioDto> consultar(@PathVariable Long id) {
        Optional<Usuario> optionalUsuario = usuarioService.consultar(id);

        if(optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            Empresa empresa = usuario.getEmpresa();

            DetalhesDoUsuarioDto dto = DetalhesDoUsuarioDto.from(usuario, empresa);

            return ResponseEntity.ok().body(dto);
        }

        return ResponseEntity.notFound().build();
    }
}
