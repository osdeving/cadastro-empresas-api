package br.com.broadfactor.cadempresas.controller.dto.response;

import br.com.broadfactor.cadempresas.model.Usuario;
import lombok.Data;

@Data
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
