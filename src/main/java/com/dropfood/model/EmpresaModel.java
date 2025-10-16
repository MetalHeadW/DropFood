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
private int idEmpresa;
    @Column(name = "NMEMPRESA")
    private String nmEmpresa;
    @Column(name = "DSEMPRESA")
    private String dsEmpresa;
    @Column(name = "TELEFONEEMPRESA")
    private String telefoneEmpresa;
    @Column(name = "CNPJ")
    private String cnpjEmpresa;
    @Column (name ="TPEMPRESA")
    private String tipoEmpresa;
}

