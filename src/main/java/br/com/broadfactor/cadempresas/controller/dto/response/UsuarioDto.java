package br.com.broadfactor.cadempresas.controller.dto.response;

import br.com.broadfactor.cadempresas.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDto {
    private String nome;
    private String email;
    private String cnpj;
    private String senha;

    public UsuarioDto() {
    }

    public UsuarioDto(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.cnpj = usuario.getCnpj();
        this.senha = usuario.getSenha();
    }
}
