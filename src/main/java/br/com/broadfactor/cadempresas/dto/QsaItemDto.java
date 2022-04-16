package br.com.broadfactor.cadempresas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class QsaItemDto {
	@JsonProperty("qual")
	private String qualificacao;

	@JsonProperty("nome")
	private String nome;

	@JsonProperty("qual_rep_legal")
	private String qualRepLegal;

	@JsonProperty("nome_rep_legal")
	private String nomeRepLegal;

	@JsonProperty("pais_origem")
	private String paisOrigem;
}
