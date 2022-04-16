package br.com.broadfactor.cadempresas.controller.dto.response;

import br.com.broadfactor.cadempresas.dto.utils.MapperUtils;
import br.com.broadfactor.cadempresas.model.QuadroSociosAdmin;
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
public class QuadroSociosAdminDto {
    private String nome;
    private String qualificacao;
    private String paisOrigem;
    private String nomeRepresentanteLegal;
    private String qualificacaoRepresentanteLegal;

    public static List<QuadroSociosAdmin> toEntity(List<QuadroSociosAdminDto> quadroSociosAdminsDto) {
        return MapperUtils.mapList(quadroSociosAdminsDto, QuadroSociosAdmin.class);
    }

    public static List<QuadroSociosAdminDto> toDto(List<QuadroSociosAdmin> quadroSociosAdmins) {
        return MapperUtils.mapList(quadroSociosAdmins, QuadroSociosAdminDto.class);
    }
}
