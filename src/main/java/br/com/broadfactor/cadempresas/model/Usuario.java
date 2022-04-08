package br.com.broadfactor.cadempresas.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "senha")
    private String senha;

    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Empresa empresa;
}
