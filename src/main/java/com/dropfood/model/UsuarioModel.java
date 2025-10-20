package com.dropfood.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBUSUARIO")

public class UsuarioModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUSUARIO")
    private Integer id_Usuario;

    @Column(name = "NMUSUARIO", length=100, nullable = false)
    private String nm_Usuario;

    @Column(name = "EMAIL",  length=100)
    private String email;

    @Column(name = "SENHAUSUARIO", length=50, nullable = false)
    private String senha_Usuario;

    @Column(name = "TELEFONE", length =20)
    private String telefone;

    @Column(name = "TIPUSUARIO", length =1, nullable = false)
    private Integer tip_Usuario; // 1-Administrador. 2-Funcionario 3-Entregador. 4-Cliente.

    @Column(name = "CPF", length =20, nullable = false)
    private String cpf;

    @Column(name = "ENDERECO", length=200)
    private String endereco;

    @Column(name = "PEFERENCIA1", length=30)
    private String peferencia1;

    @Column(name = "PEFERENCIA2", length=30)
    private String peferencia2;
}