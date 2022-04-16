package br.com.broadfactor.cadempresas.controller.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AtualizacaoUsuarioForm {
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

    private EmpresaForm empresa;
}
