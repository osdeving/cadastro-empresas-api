package br.com.broadfactor.cadempresas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpresaDto{

	@JsonProperty("atividade_principal")
	private List<AtividadeDto> atividadePrincipal;

	@JsonProperty("tipo")
	private String tipo;

	@JsonProperty("telefone")
	private String telefone;

	@JsonProperty("situacao")
	private String situacao;

	@JsonProperty("numero")
	private String numero;

	@JsonProperty("situacao_especial")
	private String situacaoEspecial;

	@JsonProperty("ultima_atualizacao")
	private String ultimaAtualizacao;

	@JsonProperty("cnpj")
	private String cnpj;

	@JsonProperty("cep")
	private String cep;

	@JsonProperty("billing")
	private Billing billing;

	@JsonProperty("atividades_secundarias")
	private List<AtividadeDto> atividadesSecundarias;

	@JsonProperty("uf")
	private String uf;

	@JsonProperty("complemento")
	private String complemento;

	@JsonProperty("natureza_juridica")
	private String naturezaJuridica;

	@JsonProperty("motivo_situacao")
	private String motivoSituacao;

	@JsonProperty("extra")
	private Extra extra;

	@JsonProperty("porte")
	private String porte;

	@JsonProperty("email")
	private String email;

	@JsonProperty("data_situacao")
	private String dataSituacao;

	@JsonProperty("bairro")
	private String bairro;

	@JsonProperty("municipio")
	private String municipio;

	@JsonProperty("nome")
	private String nome;

	@JsonProperty("efr")
	private String efr;

	@JsonProperty("fantasia")
	private String fantasia;

	@JsonProperty("abertura")
	private String abertura;

	@JsonProperty("data_situacao_especial")
	private String dataSituacaoEspecial;

	@JsonProperty("logradouro")
	private String logradouro;

	@JsonProperty("capital_social")
	private String capitalSocial;

	@JsonProperty("qsa")
	private List<QsaItemDto> qsa;

	@JsonProperty("status")
	private String status;
}
