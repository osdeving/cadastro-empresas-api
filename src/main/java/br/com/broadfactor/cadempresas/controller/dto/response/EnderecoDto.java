package br.com.broadfactor.cadempresas.controller.dto.response;

import br.com.broadfactor.cadempresas.dto.utils.MapperUtils;
import br.com.broadfactor.cadempresas.model.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnderecoDto {
    private String cep;
    private String municipio;
    private String bairro;
    private String logradouto;
    private Integer numero;
    private String complemento;
    private String uf;

    public static EnderecoDto toDto(Endereco endereco) {
        return MapperUtils.map(endereco,EnderecoDto.class);
    }

    public static Endereco toEntity(EnderecoDto enderecoDto) {
        return MapperUtils.map(enderecoDto,Endereco.class);
    }
}
