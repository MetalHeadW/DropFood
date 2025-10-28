package com.dropfood.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "idUsuario") // E @EqualsAndHashCode focado apenas no ID.
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBUSUARIO")
public class UsuarioModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUSUARIO")
    private Integer idUsuario;

    @Column(name = "NMUSUARIO", length=100, nullable = false)
    private String nmUsuario;

    @Column(name = "EMAIL", length=100, unique = true)
    private String email;

    @Column(name = "SENHAUSUARIO", nullable = false)
    private String senhaUsuario;

    @Column(name = "TELEFONE", length =20)
    private String telefone;

    @Column(name = "TIPUSUARIO", length =1, nullable = false)
    private Integer tipUsuario; // 1-Administrador. 2-Funcionario 3-Entregador. 4-Cliente.

    @Column(name = "CPF", length =20, nullable = false, unique = true)
    private String cpf;

    @Column(name = "ENDERECO", length=200)
    private String endereco;

    @Column(name = "PREFERENCIA1", length=30)
    private String preferencia1;

    @Column(name = "PREFERENCIA2", length=30)
    private String preferencia2;

    @Column(name = "FLGATIVO", length=1)
    private String flgAtivo;

    @ManyToOne
    @JoinColumn(name = "IDEMPRESA", nullable = false)
    private EmpresaModel empresa;
}