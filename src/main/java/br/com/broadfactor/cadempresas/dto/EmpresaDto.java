package br.com.broadfactor.cadempresas.dto;

import br.com.broadfactor.cadempresas.dto.utils.EmpresaDtoUtils;
import br.com.broadfactor.cadempresas.dto.utils.MapperUtils;
import br.com.broadfactor.cadempresas.model.Empresa;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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

//	private EmpresaDto convertToDto(Empresa empresa) {
//		var empresaDto = MapperUtils.map(empresa, EmpresaDto.class);
//
//		empresaDto.setAtividadesSecundarias(MapperUtils.mapList(modelMapper, empresa.getAtividadeSecundaria(), AtividadeDto.class));
//		empresaDto.setAtividadePrincipal(MapperUtils.mapList(modelMapper, empresa.getAtividadePrincipal(), AtividadeDto.class));
//		empresaDto.setQsa(MapperUtils.mapList(modelMapper, empresa.getQuadroSociosAdmins(), QsaItemDto.class));
//
//
//		return empresaDto;
//	}
//	public EmpresaDto toDto(Empresa empresa) {
//		EmpresaDto dto = new EmpresaDto();
//
//		dto.setAtividadePrincipal(empresa.getAtividadePrincipal(),
//			empresa.getTipo(),
//			empresa.getContato().getTelefone(),
//				empresa.getSituacao(),
//				empresa.getEndereco().getNumero(),
//				empresa.getSituacaoEspecial(),
//				empresa.getUltimaAtualizacao(),
//				empresa.getCnpj(),
//				empresa.getEndereco().getCep(),
//				null,
//				empresa.getAtividadeSecundaria(),
//				empresa.getEndereco().getUf(),
//				empresa.getEndereco().getComplemento(),
//				empresa.getNaturezaJuridica(),
//				empresa.getMotivoSituacao(),
//				empresa.getPorte(),
//
//
//
//
//	}
}
