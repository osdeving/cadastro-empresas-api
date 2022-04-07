package br.com.broadfactor.cadempresas.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empresa")
@Getter
@Setter
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "fantasia")
    private String fantasia;

    @Column(name = "cnpj", unique = true)
    private String cnpj;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "porte")
    private String porte;

    @Column(name = "abertura")
    private String abertura;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "situacao_especial")
    private String situacaoEspecial;

    @Column(name = "natureza_juridica")
    private String naturezaJuridica;

    @Column(name = "motivo_situacao")
    private String motivoSituacao;

    @Column(name = "data_situacao")
    private String dataSituacao;

    @Column(name = "efr")
    private String efr;

    @Column(name = "data_situacao_especial")
    private String dataSituacaoEspecial;

    @Column(name = "capital_social")
    private String capitalSocial;

    @Column(name = "status")
    private String status;

    @Column(name = "ultima_atualizacao")
    private String ultimaAtualizacao;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    List<QuadroSociosAdmin> quadroSociosAdmins = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    List<AtividadePrincipal> atividadePrincipal = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    List<AtividadeSecundaria> atividadeSecundaria = new ArrayList<>();

    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id")
    Endereco endereco;

    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    Contato contato;
}
