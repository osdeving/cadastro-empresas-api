package br.com.broadfactor.cadempresas.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "quadro_socios_admins")
@Getter
@Setter
public class QuadroSociosAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "qualificacao")
    private String qualificacao;

    @Column(name = "paisOrigem")
    private String paisOrigem;

    @Column(name = "nome_rep_legal")
    private String nomeRepresentanteLegal;

    @Column(name = "qualificacao_rep_legal")
    private String qualificacaoRepresentanteLegal;
}
