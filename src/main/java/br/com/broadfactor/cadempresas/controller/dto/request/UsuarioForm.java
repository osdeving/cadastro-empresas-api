package br.com.broadfactor.cadempresas.controller.dto.request;

import br.com.broadfactor.cadempresas.model.Usuario;
import lombok.Data;

@Data
public class UsuarioForm {

    private String nome;
    private String email;
    private String cnpj;
    private String password;

    public Usuario toEntity() {
        Usuario usuario = new Usuario();

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setCnpj(cnpj);
        usuario.setPassword(password);

        return usuario;
    }
}
