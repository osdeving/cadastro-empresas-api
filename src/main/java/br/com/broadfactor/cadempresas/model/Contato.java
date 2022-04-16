package br.com.broadfactor.cadempresas.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contato")
@Getter
@Setter
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email", unique = true)
    private String email;
}
