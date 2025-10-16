package com.dropfood.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBPRODUTO")

public class ProdutoModel { @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "CDCLIENTE")
private int cdCliente;
    @Column(name = "NMCLIENTE")
    private String nmCliente;
    @Column(name = "DSCLIENTE")
    private String dsCliente;
    @Column(name = "VLSALARIOCLIENTE")
    private Double vlSalarioCliente;
    @Column(name = "FLNUMEROCLIENTE")
    private String flNumeroCliente;
}
}
