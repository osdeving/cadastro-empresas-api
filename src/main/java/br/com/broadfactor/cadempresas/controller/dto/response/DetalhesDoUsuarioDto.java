package br.com.broadfactor.cadempresas.controller.dto.response;

import br.com.broadfactor.cadempresas.dto.AtividadeDto;
import br.com.broadfactor.cadempresas.dto.EmpresaDto;
import br.com.broadfactor.cadempresas.dto.QsaItemDto;
import br.com.broadfactor.cadempresas.dto.utils.MapperUtils;
import br.com.broadfactor.cadempresas.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetalhesDoUsuarioDto {
	private String nomeUsuario;
	private String emailUsario;

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

	List<QsaItemDto> quadroSociosAdmins = new ArrayList<>();
	List<AtividadeDto> atividadePrincipal = new ArrayList<>();
	List<AtividadeDto> atividadeSecundaria = new ArrayList<>();

	EnderecoDto endereco;
	ContatoDto contato;

	public static DetalhesDoUsuarioDto from(Usuario usuario, Empresa empresa) {
		DetalhesDoUsuarioDto detalhesDoUsuarioDto = MapperUtils.map(empresa, DetalhesDoUsuarioDto.class);
		detalhesDoUsuarioDto.setNomeUsuario(usuario.getNome());
		detalhesDoUsuarioDto.setEmailUsario(usuario.getEmail());

		return detalhesDoUsuarioDto;
	}
//
//		EmpresaDto empresaDto = MapperUtils.map(empresa, EmpresaDto.class);
//
//		List<AtividadeDto> atividadePrincipal = MapperUtils.mapList(empresa.getAtividadePrincipal(), AtividadeDto.class);
//		List<AtividadeDto> atividadeSecundaria = MapperUtils.mapList(empresa.getAtividadeSecundaria(), AtividadeDto.class);
//		List<QsaItemDto> qsaItemDtos = MapperUtils.mapList(empresa.getQuadroSociosAdmins(), QsaItemDto.class);
//
//		empresaDto.setAtividadePrincipal(atividadePrincipal);
//		empresaDto.setAtividadesSecundarias(atividadeSecundaria);
//		empresaDto.setQsa(qsaItemDtos);
//
//		Endereco endereco = empresa.getEndereco();
//		Contato contato = empresa.getContato();
//
//		empresaDto.setNumero(String.valueOf(endereco.getNumero()));
//		empresaDto.setBairro(endereco.getBairro());
//		empresaDto.setMunicipio(endereco.getMunicipio());
//		empresaDto.setCep(endereco.getCep());
//		empresaDto.setComplemento(endereco.getComplemento());
//		empresaDto.setLogradouro(endereco.getLogradouto());
//
//		empresaDto.
//
//
//		return empresaDto;
//	}
}
