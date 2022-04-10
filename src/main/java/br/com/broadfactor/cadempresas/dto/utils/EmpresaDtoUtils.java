package br.com.broadfactor.cadempresas.dto.utils;

import br.com.broadfactor.cadempresas.dto.AtividadeDto;
import br.com.broadfactor.cadempresas.dto.EmpresaDto;
import br.com.broadfactor.cadempresas.dto.QsaItemDto;
import br.com.broadfactor.cadempresas.model.*;

import java.util.ArrayList;
import java.util.List;

public class EmpresaDtoUtils {
    public static Empresa toEntity(EmpresaDto dto) {
        Empresa empresa = MapperUtils.getMapper().map(dto, Empresa.class);


        fillAtividadePrincipal(empresa, dto.getAtividadePrincipal());
        fillAtividadeSecundaria(empresa, dto.getAtividadesSecundarias());
        fillQuadroSociosAdmins(empresa,dto.getQsa());
        fillEndereco(empresa, dto);
        fillContato(empresa, dto);
        return empresa;
    }

    private static void fillEndereco(Empresa empresa, EmpresaDto dto) {
        Endereco endereco = new Endereco();

        endereco.setBairro(dto.getBairro());
        endereco.setCep(dto.getCep());
        endereco.setComplemento(dto.getComplemento());
        endereco.setLogradouto(dto.getLogradouro());
        endereco.setMunicipio(dto.getMunicipio());
        endereco.setUf(dto.getUf());
        endereco.setNumero(Integer.parseInt(dto.getNumero()));

        empresa.setEndereco(endereco);
    }

    private static void fillContato(Empresa empresa, EmpresaDto dto) {
        Contato contato = new Contato();

        contato.setEmail(dto.getEmail());
        contato.setTelefone(dto.getTelefone());

        empresa.setContato(contato);
    }

    private static void fillAtividadeSecundaria(Empresa empresa, List<AtividadeDto> atividadeSecundaria) {
        atividadeSecundaria.forEach(a -> {
            AtividadeSecundaria atividade = new AtividadeSecundaria();
            atividade.setCode(a.getCode());
            atividade.setText(a.getText());

            if(empresa.getAtividadeSecundaria() == null) {
                empresa.setAtividadeSecundaria(new ArrayList<>());
            }

            empresa.getAtividadeSecundaria().add(atividade);
        });
    }

    private static void fillAtividadePrincipal(Empresa empresa, List<AtividadeDto> atividadePrincipal) {
        atividadePrincipal.forEach(a -> {
            AtividadePrincipal atividade = new AtividadePrincipal();
            atividade.setCode(a.getCode());
            atividade.setText(a.getText());

            if(empresa.getAtividadePrincipal() == null) {
                empresa.setAtividadePrincipal(new ArrayList<>());
            }

            empresa.getAtividadePrincipal().add(atividade);
        });
    }

    private static void fillQuadroSociosAdmins(Empresa empresa, List<QsaItemDto> qsaItems) {
        qsaItems.forEach(qsa -> {
            QuadroSociosAdmin quadroSociosAdmins = new QuadroSociosAdmin();

            quadroSociosAdmins.setQualificacao(qsa.getQualificacao());
            quadroSociosAdmins.setNome(qsa.getNome());
            quadroSociosAdmins.setPaisOrigem(qsa.getPaisOrigem());
            quadroSociosAdmins.setNomeRepresentanteLegal(qsa.getNomeRepLegal());
            quadroSociosAdmins.setQualificacaoRepresentanteLegal(qsa.getQualRepLegal());

            if(empresa.getQuadroSociosAdmins() == null) {
                empresa.setQuadroSociosAdmins(new ArrayList<>());
            }

            empresa.getQuadroSociosAdmins().add(quadroSociosAdmins);
        });
    }

}
