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
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "CDUSUARIO")
private int cdCliente;
    @Column(name = "NMUSUARIO")
    private String nmCliente;
    @Column(name = "DSCLIENTE")
    private String dsCliente;
    @Column(name = "VLSALARIOCLIENTE")
    private Double vlSalarioCliente;
    @Column(name = "FLNUMEROCLIENTE")
    private String flNumeroCliente;
}
}
