package br.com.broadfactor.cadempresas.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnderecoDto {
    private String cep;
    private String municipio;
    private String bairro;
    private String logradouto;
    private Integer numero;
    private String complemento;
    private String uf;
}
