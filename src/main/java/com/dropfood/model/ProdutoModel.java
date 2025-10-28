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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idProduto")
@Table(name = "TBPRODUTO")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPRODUTO")
    private Integer idProduto;

    @Column(name = "NMPRODUTO", length = 100, nullable = false)
    private String nmProduto;

    @Column(name = "DSPRODUTO", length = 1000)
    private String dsProduto;

    @Column(name = "PRECO", nullable = false)
    private Double preco;

    @Column(name = "CATEGORIA", length = 50, nullable = false)
    private String categoria;

    @Column(name = "FLGATIVO", length = 1, nullable = false)
    private String flgAtivo;

    @ManyToOne
    @JoinColumn(name = "IDEMPRESA", nullable = false)
    private EmpresaModel empresa;
}