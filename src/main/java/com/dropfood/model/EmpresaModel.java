package com.dropfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_Empresa")
@Table(name = "TBEMPRESA")
public class EmpresaModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDEMPRESA")
    private Integer id_Empresa;

    @Column(name = "NMEMPRESA", length = 100,  nullable = false)
    private String nm_Empresa;

    @Column(name = "ENDERECO", length = 200,  nullable = false)
    private String endereco_Empresa;

    @Column(name = "TELEFONEEMPRESA", length = 20,  nullable = false)
    private String telefone_Empresa;

    @Column(name = "CNPJ", length = 18,  nullable = false, unique = true)
    private String cnpj_Empresa;

    @Column (name ="TPEMPRESA", length = 50)
    private String tipo_Empresa;

    @OneToMany (mappedBy = "empresa", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<UsuarioModel> usuarios;
}