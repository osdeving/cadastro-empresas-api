package br.com.broadfactor.cadempresas.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class Usuario {
    private String nome;
    private String email;
    private String cnpj;
    private String password;
}
