package br.com.broadfactor.cadempresas.controller.dto.response;

import br.com.broadfactor.cadempresas.dto.utils.MapperUtils;
import br.com.broadfactor.cadempresas.model.AtividadePrincipal;
import br.com.broadfactor.cadempresas.model.AtividadeSecundaria;
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
public class AtividadeDto {
	@JsonProperty("code")
	private String code;

	@JsonProperty("text")
	private String text;

	public static List<AtividadeDto> toDtoFromAtividadePrincipal(List<AtividadePrincipal> atividade) {
		return MapperUtils.mapList(atividade, AtividadeDto.class);
	}

	public static List<AtividadeDto> toDtoFromAtividadeSecundaria(List<AtividadeSecundaria> atividade) {
		return MapperUtils.mapList(atividade, AtividadeDto.class);
	}

	public static List<AtividadePrincipal> toAtividadePrincipalFromDto(List<AtividadeDto> atividadeDto) {
		return MapperUtils.mapList(atividadeDto, AtividadePrincipal.class);
	}

	public static List<AtividadeSecundaria> toAtividadeSecundariaFromDto(List<AtividadeDto> atividadeDto) {
		return MapperUtils.mapList(atividadeDto, AtividadeSecundaria.class);
	}
}
