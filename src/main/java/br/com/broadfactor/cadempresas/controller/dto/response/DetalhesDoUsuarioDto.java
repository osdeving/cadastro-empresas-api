package br.com.broadfactor.cadempresas.controller.dto.response;

import br.com.broadfactor.cadempresas.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetalhesDoUsuarioDto {
	UsuarioDto usuario;
	EmpresaDto empresa;

	public DetalhesDoUsuarioDto(Usuario usuario) {
		this.usuario = new UsuarioDto(usuario);
		this.empresa = EmpresaDto.toDto(usuario.getEmpresa());
	}
}
