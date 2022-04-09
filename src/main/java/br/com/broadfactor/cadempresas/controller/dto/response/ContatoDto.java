package br.com.broadfactor.cadempresas.controller.dto.response;

import br.com.broadfactor.cadempresas.dto.utils.MapperUtils;
import br.com.broadfactor.cadempresas.model.Contato;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContatoDto {
    private String telefone;
    private String email;

    public static ContatoDto toDto(Contato contato) {
        return MapperUtils.map(contato,ContatoDto.class);
    }

    public static Contato toEntity(ContatoDto contatoDto) {
        return MapperUtils.map(contatoDto,Contato.class);
    }
}
