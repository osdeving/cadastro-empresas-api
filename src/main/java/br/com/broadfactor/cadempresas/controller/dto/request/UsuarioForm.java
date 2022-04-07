package br.com.broadfactor.cadempresas.controller.dto.request;

import br.com.broadfactor.cadempresas.model.Usuario;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UsuarioForm {
    @NotNull
    @NotEmpty
    @Length(max = 100)
    private String nome;
    @NotNull @NotEmpty @Length(max = 100)
    private String email;
    @NotNull @NotEmpty @Length(min = 14, max = 20)
    private String cnpj;
    @NotNull @NotEmpty @Length(min = 6, max = 20)
    private String senha;

    public Usuario toEntity() {
        Usuario usuario = new Usuario();

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setCnpj(cnpj);
        usuario.setSenha(senha);

        return usuario;
    }
}
