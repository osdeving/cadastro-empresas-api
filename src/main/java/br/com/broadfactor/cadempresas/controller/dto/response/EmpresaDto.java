package br.com.broadfactor.cadempresas.controller.dto.response;

import br.com.broadfactor.cadempresas.dto.utils.MapperUtils;
import br.com.broadfactor.cadempresas.model.Empresa;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpresaDto {
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

	EnderecoDto endereco;
	ContatoDto contato;

	List<QuadroSociosAdminDto> quadroSociosAdmins = new ArrayList<>();
	List<AtividadeDto> atividadePrincipal = new ArrayList<>();
	List<AtividadeDto> atividadeSecundaria = new ArrayList<>();

	public static EmpresaDto toDto(Empresa empresa) {
		if(empresa == null) {
			return null;
		}

		EmpresaDto empresaDto = new EmpresaDto();

		BeanUtils.copyProperties(empresa, empresaDto);

		empresaDto.setContato(ContatoDto.toDto(empresa.getContato()));
		empresaDto.setEndereco(EnderecoDto.toDto(empresa.getEndereco()));
		empresaDto.setAtividadePrincipal(AtividadeDto.toDtoFromAtividadePrincipal(empresa.getAtividadePrincipal()));
		empresaDto.setAtividadeSecundaria(AtividadeDto.toDtoFromAtividadeSecundaria(empresa.getAtividadeSecundaria()));
		empresaDto.setQuadroSociosAdmins(QuadroSociosAdminDto.toDto(empresa.getQuadroSociosAdmins()));

		return empresaDto;
	}

	public static Empresa toEntity(EmpresaDto empresaDto) {
		return MapperUtils.map(empresaDto, Empresa.class);
	}
}
