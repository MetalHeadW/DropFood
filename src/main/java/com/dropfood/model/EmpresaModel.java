package com.dropfood.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBEMPRESA")



public class EmpresaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDEMPRESA")
    private Integer id_Empresa;

    @Column(name = "NMEMPRESA", length = 100,  nullable = false)
    private String nm_Empresa;

    @Column(name = "ENDERECO", length = 200,  nullable = false)
    private String endereco_Empresa;

    @Column(name = "TELEFONEEMPRESA", length = 20,  nullable = false)
    private String telefone_Empresa;

    @Column(name = "CNPJ", length = 18,  nullable = false)
    private String cnpj_Empresa;

    @Column (name ="TPEMPRESA")
    private String tipo_Empresa;
}