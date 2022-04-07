package br.com.broadfactor.cadempresas.controller.dto.response;

import br.com.broadfactor.cadempresas.model.Usuario;
import lombok.Data;

@Data
public class DetalhesDoUsuarioDto {

	private String nome;
	private String email;

	public DetalhesDoUsuarioDto(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}
}
