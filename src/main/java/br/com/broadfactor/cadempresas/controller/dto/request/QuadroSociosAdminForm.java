package br.com.broadfactor.cadempresas.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuadroSociosAdminForm {
    private String nome;
    private String qualificacao;
    private String paisOrigem;
    private String nomeRepresentanteLegal;
    private String qualificacaoRepresentanteLegal;
}
