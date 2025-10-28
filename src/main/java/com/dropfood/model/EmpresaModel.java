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
@EqualsAndHashCode(of = "idEmpresa")
@Table(name = "TBEMPRESA")
public class EmpresaModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDEMPRESA")
    private Integer idEmpresa;

    @Column(name = "NMEMPRESA", length = 100,  nullable = false)
    private String nmEmpresa;

    @Column(name = "ENDERECO", length = 200,  nullable = false)
    private String enderecoEmpresa;

    @Column(name = "TELEFONEEMPRESA", length = 20,  nullable = false)
    private String telefoneEmpresa;

    @Column(name = "CNPJ", length = 18,  nullable = false, unique = true)
    private String cnpjEmpresa;

    @Column (name ="TPEMPRESA", length = 50)
    private String tipoEmpresa;

    @OneToMany (mappedBy = "empresa", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<UsuarioModel> usuarios;
}