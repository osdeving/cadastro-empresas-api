package br.com.broadfactor.cadempresas.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpresaForm {
	private String nome;
	private String fantasia;
	private String cnpj;
	private String tipo;
	private String porte;
	private String abertura;
	private String situacao;
	private String situacaoEspecial;
	private String naturezaJuridica;
	private String motivoSituacao;
	private String dataSituacao;
	private String efr;
	private String dataSituacaoEspecial;
	private String capitalSocial;
	private String status;
	private String ultimaAtualizacao;

	EnderecoForm endereco;
	ContatoForm contato;

	List<QuadroSociosAdminForm> quadroSociosAdmins;
	List<AtividadeForm> atividadePrincipal;
	List<AtividadeForm> atividadeSecundaria;
}
